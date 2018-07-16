package cn.com.cmdd.service;


import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.Call_serviceDao;
import cn.com.cmdd.dao.Dining_tableDao;
import cn.com.cmdd.dao.MemberDao;
import cn.com.cmdd.dao.MemberIntegralDao;
import cn.com.cmdd.dao.OrderItemDao;
import cn.com.cmdd.dao.OrdersDao;
import cn.com.cmdd.domain.Dining_table;
import cn.com.cmdd.domain.Member;
import cn.com.cmdd.domain.MemberIntegral;
import cn.com.cmdd.domain.MemberRechargeLog;
import cn.com.cmdd.domain.OrderCount;
import cn.com.cmdd.domain.Orders;
import cn.com.cmdd.domain.OrdersCountModel;
import cn.com.cmdd.util.DateUtil;
import cn.com.cmdd.util.DoubleUtil;


@Service
@Transactional
public class OrdersService{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(OrdersService.class);
	
	@Autowired
	private OrdersDao orderDao;
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberIntegralDao memberIntegralDao;

	@Autowired
	private MemberRechargeLogService memberRechargeLogService;
		
	@Autowired
	private Dining_tableDao diningTableDao;

	@Autowired
	private Call_serviceDao callServiceDao;
	
	@Transactional
	public void  saveOrders(Orders orders){


		String formatDate = DateUtil.getYy_MM_dd(new Date());
		LOGGER.info("formatDate --- "+formatDate);
		String orderSerialId = orders.getSerial_id();
		LOGGER.info("orderSerialId --- "+orderSerialId);		
		orders.setOrder_no(formatDate+orderSerialId);
		orderDao.saveOrders(orders);
	}
	
	public void updateOrders(Orders orders){
		//if(orders.getSync_status()!=0)orders.setSync_status(1);
		orderDao.updateOrders(orders);
		
	}
	
	public List<Orders> getOrders(Integer shop_id,
								  Integer id,
								  Integer page_size,
								  Integer page_no,
								  Long dining_table_id,
								  Date start_time,
								  Date end_time,
								  List<Integer> statusList,
								  Integer pay_type
	) throws ParseException {
		if(end_time!=null){
			String strEnd_time = DateUtil.getYyyyMMdd_(end_time);
			String strend_time2 = strEnd_time+" 23:59:59";
			end_time = new Date(strend_time2);
		}					
		Integer pageStart_index=page_no!=null&&page_size!=null?(page_no-1)*page_size:null;				
		return orderDao.selectList(shop_id,id,page_size,pageStart_index,dining_table_id,start_time,end_time,statusList,pay_type);
		
	}
	
	public List<OrderCount> getOrdersCount(Integer shop_id,
										   Date start_time,
										   Date end_time,
										   List<Integer> statusList) throws ParseException
	{
		if(end_time!=null){
			
			String strEnd_time = DateUtil.getYyyyMMdd_(end_time);
			String strend_time2 = strEnd_time+" 23:59:59";			
			end_time = new Date(strend_time2);
			
		}
		return orderDao.getOrdersCount(shop_id, start_time, end_time, statusList);
	}
	
	public OrdersCountModel getOrderPayCount(Integer shop_id,
						Date start_time,
					   Date end_time,
					   List<Integer> statusList) throws ParseException
	{
		if(end_time!=null){
			String strEnd_time = DateUtil.getYyyyMMdd_(end_time);
			String strend_time2 = strEnd_time+" 23:59:59";
			end_time = new Date(strend_time2);
		}
		return orderDao.getOrderPayCount(shop_id, start_time, end_time);	
	}
	
	public OrdersCountModel countOrder(
										Integer shop_id,
										Date start_time,
										Date end_time, 
										List<Integer> statusList,
										List<OrderCount> ordersCounts,
									    Double sumMemberRecharge,
									    Double sumMemberGiveAmount,
									    Double sumLottery,
									    List<Orders> ordersList)throws ParseException{
		
		if(end_time!=null){
			String strEnd_time = DateUtil.getYyyyMMdd_(end_time);
			String strend_time2 = strEnd_time+" 23:59:59";
			end_time = new Date(strend_time2);
		}
		OrdersCountModel ordersCountModel =orderDao.getOrderPayCount(shop_id, start_time, end_time);
		double sumTradeCash=0.00;
		double sumTradeCreditCard=0.00;
		double sumTradeWechat=0.00;
		double sumTradeAlipay=0.00;
		//double sumMemberConsume=0.00;
		double sumCombinationPayment=0.00;
		double sumFreeOrder=0.00;
		double sumBill=0.00;
		double sumCancelOrder=0.00;
		
		//OrdersCountModel ordersCountModel = new OrdersCountModel();

		//int totalNum =0;
		//实付<
		//实收 : 4种支付方式+会员充值 totalSale sumReceived
		double sumReceived=0.00;
		//应收金额 : 应收+免单+挂账 sumReceivable
		double sumReceivable =0.00;
		//抹零总和>
		//sdouble sumResidue = 0.00;
		for (OrderCount ordersCount : ordersCounts) {
			//TODO:判断分支

			Integer typePay = ordersCount.getPay_type();
			if(typePay==111){
				//现金
				sumTradeCash=ordersCount.getPayType_money();					
					
				sumReceived= DoubleUtil.sum(sumReceived, sumTradeCash);
				sumReceivable =	DoubleUtil.sum(sumReceivable,sumTradeCash);

			}else if(typePay==112){
				//银行卡
				sumTradeCreditCard=ordersCount.getPayType_money();
					
				sumReceived=DoubleUtil.sum(sumReceived,sumTradeCreditCard);
				sumReceivable =	DoubleUtil.sum(sumReceivable, sumTradeCreditCard);
				
			}else if(typePay==113){
				//微信>
				sumTradeWechat=ordersCount.getPayType_money();
					
				sumReceived=DoubleUtil.sum(sumReceived, sumTradeWechat);
				sumReceivable =	DoubleUtil.sum(sumReceivable,sumTradeWechat);
				
			}else if(typePay==114){
				//支付宝
				sumTradeAlipay=ordersCount.getPayType_money();
					
				sumReceived=DoubleUtil.sum(sumReceived, sumTradeAlipay);
				sumReceivable =	DoubleUtil.sum(sumReceivable, sumTradeAlipay);
				
			}else if(typePay==115){
				//会员消费
				//sumMemberConsume=ordersCount.getPayType_money();
							
			}else if(typePay==116){
				//组合付款
				sumCombinationPayment=ordersCount.getPayType_money();
					
				sumReceived = DoubleUtil.sum(sumReceived, sumCombinationPayment);
				sumReceivable =	DoubleUtil.sum(sumReceivable,sumCombinationPayment);
				
			}else if(typePay==120){
				//免单 --- 应付金额
				sumFreeOrder=ordersCount.getPayType_money_total();
					
				sumReceivable = DoubleUtil.sum(sumReceivable,sumFreeOrder);
				
			}else if(typePay==130){
				//挂账 --- 应付金额
				sumBill=ordersCount.getPayType_money_total();
									
				sumReceivable = DoubleUtil.sum(sumReceivable,sumBill);

			}else if(typePay==210){
				//取消订单 ---应付金额
				sumCancelOrder=ordersCount.getPayType_money_total();			
			}
			
		}
		
		//折扣优惠金额			
		//double sumDiscountPreferential = orderDao.selectDiscoutPreferential(shop_id, start_time, end_time);
		Double sumDiscountPreferential = ordersCountModel.getSumDiscountPreferential();
		//会员充值收入
		sumReceived = DoubleUtil.sum(sumReceived, sumMemberRecharge);	
		sumReceivable = DoubleUtil.sum(sumReceivable, sumMemberRecharge);
		//抹零
		Double sumResidue = ordersCountModel.getSumResidue();
		sumReceivable = DoubleUtil.sum(sumReceivable, sumResidue);
		
		sumReceivable = DoubleUtil.sum(sumReceivable, sumDiscountPreferential);
		
		ordersCountModel.setSumFreeOrder(sumFreeOrder);
		ordersCountModel.setSumBill(sumBill);
		ordersCountModel.setSumCombinationPayment(sumCombinationPayment);
		//ordersCountModel.setSumMemberConsume(sumMemberConsume);
		ordersCountModel.setSumCancelOrder(sumCancelOrder);
		
		
		ordersCountModel.setSumMemberRecharge(sumMemberRecharge);
		ordersCountModel.setSumReceived(sumReceived);
		ordersCountModel.setSumReceivable(sumReceivable);
		ordersCountModel.setSumLottery(sumLottery);
		ordersCountModel.setSumMemberGiveMoney(sumMemberGiveAmount);
		//20171126-yusiqing-LBCT+
		ordersCountModel.setSumDiscountPreferential(sumDiscountPreferential);
		ordersCountModel.setOrderList(ordersList);

		return ordersCountModel;
		
	}

	/**
	 * 正常结算
	 * 171103\:111-现金,112-银行卡,113-微信,114-支付宝,115-会员卡,116-组合付款,120-免单,130-挂账,210-退单
	 */
		
	public void balanceBook(Orders orders) throws Exception {		
		//TODO:规范
		//结账默认为主订单
		//TODO:新建订单设置默认值

		orders.setTypeHypotaxis(0);
			
		Double tradeCash = orders.getTradeCash();
		Double tradeCreditCard = orders.getTradeCreditCard();
		Double tradeWechat = orders.getTradeWechat();
		Double tradeAlipay = orders.getTradeAlipay();
		Double tradeMemberIntegral = orders.getTradeMemberIntegral();
		Double tradeMemberMoney = orders.getTradeMemberMoney();		
		
		Integer typePay = orders.getPay_type();
		//定义订单的支付类型pay_type
		if(typePay==0){		
			int tradeCashCode=tradeCash==null?0:(tradeCash==0?0:111);
			int tradeCreditCardCode=tradeCreditCard==null?0:(tradeCreditCard==0?0:112);
			int tradeWechatCode=tradeWechat==null?0:(tradeWechat==0?0:113);
			int tradeAlipayCode=tradeAlipay==null?0:(tradeAlipay==0?0:114);
						
			int tradeMemberCode;
			if((tradeMemberIntegral==null||tradeMemberIntegral==0)&&(tradeMemberMoney==null||tradeMemberMoney==0)){
				tradeMemberCode= 0;
			}else{
				tradeMemberCode= 115;
				Long memberId= orders.getMemberId();
				Member member =memberDao.getMemberById(memberId);

				MemberRechargeLog memberRechargeLog = new MemberRechargeLog();

				if(tradeMemberIntegral!=null&&tradeMemberIntegral!=0){				
					//TODO:不会为空,新建默认0
					Double integral = member.getIntegral();

					Double actualIntegral = DoubleUtil.sub(integral,tradeMemberIntegral);

					if(actualIntegral<0){
						throw new Exception("会员积分不足");
					}
					member.setIntegral(actualIntegral);
					memberDao.updateMember(member);
					//新建会员积分消费记录
					memberRechargeLog.setMember_id(orders.getMemberId());
					memberRechargeLog.setShop_id(orders.getShop_id());
					memberRechargeLog.setTradeIntegral(tradeMemberIntegral);
					memberRechargeLog.setIntegral(actualIntegral);
						//2:积分交易
					memberRechargeLog.setCategory(2);
						//22:消费
					memberRechargeLog.setType(22);
					//TODO:
					//memberRechargeLog.setDescription("消费订单编号为:"+orders.getOrder_no());
						//交易类型
					memberRechargeLog.setPay_type(22);
					memberRechargeLogService.addMemberRechargeLog(memberRechargeLog);
				}
				if(tradeMemberMoney!=null&&tradeMemberMoney!=0){
					//TODO:不会为空,新建默认0
					Double balance = member.getBalance();
					//TODO:方法里的两个值不能为空
					Double actualBalance = DoubleUtil.sub(balance, tradeMemberMoney);

					if(actualBalance<0){
						throw new Exception("会员金额不足");
					}
					member.setBalance(actualBalance);
					memberDao.updateMember(member);
					//新建会员余额消费记录
					memberRechargeLog.setMember_id(orders.getMemberId());
					memberRechargeLog.setShop_id(orders.getShop_id());
					memberRechargeLog.setAmount(tradeMemberMoney);
					memberRechargeLog.setNow_balance(actualBalance);
						//1:余额交易
					memberRechargeLog.setCategory(1);
						//12:消费
					memberRechargeLog.setType(12);
					//TODO:
					//memberRechargeLog.setDescription("消费订单编号为:"+orders.getId());
					//交易类型
					memberRechargeLog.setPay_type(12);
					memberRechargeLogService.addMemberRechargeLog(memberRechargeLog);
				}
			}
			//
			Integer typePayCode = tradeCashCode+tradeCreditCardCode+tradeWechatCode+tradeAlipayCode+tradeMemberCode;
			typePay= typePayCode>115?116:typePayCode;
			//i
			orders.setPay_type(typePay);

		}
		//累积会员积分
		if(orders.getIsUseMember()==1){

			Long memberId= orders.getMemberId();
			//TODO:规范
			//TODO:会员不要有删除接口,或者订单但是状态
			Member member = memberDao.getMemberById(memberId);
			Double integral = member.getIntegral();
			//浪费金额兑换积分规则
			MemberIntegral memberIntegral = memberIntegralDao.select(member.getShop_id());
			if(memberIntegral==null){
				throw new Exception("商铺积分兑换规则为空");
			}
			//实付金额
			//TODO:171120s
			double mul = orders.getReal_pay();
			//消费所累计的金额
			BigDecimal consumeToIntegral = new BigDecimal(memberIntegral.getConsumeIntegral()).multiply(new BigDecimal(mul).divideToIntegralValue(new BigDecimal(memberIntegral.getConsumeMoney())));

			//同步积分
			Double actualIntegral = new BigDecimal(integral).add(consumeToIntegral).doubleValue();
			member.setIntegral(actualIntegral.doubleValue());
			memberDao.updateMember(member);
			//增加积分累积记录
			MemberRechargeLog memberRechargeLog = new MemberRechargeLog();
			//新建会员积分消费记录
			memberRechargeLog.setMember_id(orders.getMemberId());
			memberRechargeLog.setShop_id(orders.getShop_id());
			memberRechargeLog.setTradeIntegral(consumeToIntegral.doubleValue());
			//TODO:并发
			memberRechargeLog.setIntegral(actualIntegral);
			//2:积分交易
			memberRechargeLog.setCategory(2);
			//21:消费
			memberRechargeLog.setType(21);
			//TODO:
			//memberRechargeLog.setDescription("累积积分订单编号为:"+orders.getId());
			memberRechargeLog.setPay_type(21);

			memberRechargeLogService.addMemberRechargeLog(memberRechargeLog);
		}
		//置位桌位
		orderDao.resetDiningTable(orders.getId());
		//删除相关呼叫服务
		orderDao.deleteCallServiceByDiningTableId(orders.getDining_table_id());
		//免单/取消订单删除对单对应奖品
		if(typePay==120||typePay==130){
			orderDao.deleteMemberLotterLogByOrderId(orders.getId());
		}
		//TODO:
		//		1.联表修改失败
		// 	    2是否查询多余数据
		orderDao.updateOrders(orders);
		List<Long> idList = orderDao.selectIdListByPId(orders.getId());
		if(idList.size()>0){
			for (Long id:idList
				 ) {
				Orders order = new Orders();
				order.setId(id);
				//TODO:其他属性
				order.setStatus_id(orders.getStatus_id());
				order.setService_charge(orders.getService_charge());
				orderDao.updateOrders(order);
			}
		}
	}

	public void unifyOrder(Long diningTablePid,List<Long> diningTableIdList){

		orderDao.unifyOrderNo(diningTablePid,diningTableIdList);
	}
	/**
	 * 开桌:	
	 * 	1.dining_tale:status 0--->1
	 * 	2.创建订单 
	 */
	 public void openTable(Long dingTableId,Orders orders){
		 
		 diningTableDao.updateStatus(1, dingTableId);
		 orders.setOrder_no(DateUtil.getYy_MM_dd(new Date())+orders.getSerial_id());
		 orderDao.saveOrders(orders);
	
	 }

	/**
	 * 清桌：
	 */
	public void clearTable(Long diningTableId) {
		
		callServiceDao.deleteByDiningTableId(diningTableId);
		
		Orders orders = orderDao.selectByDiningTableAndStatusId(diningTableId, 0);
		Long orderId = orders.getId();
		
		orderItemDao.deleteByOrderId(orderId);
		
		Dining_table diningTable = new Dining_table();
		diningTable.setId(diningTableId);
		diningTable.setStatus(0);
		diningTableDao.updateDining_table(diningTable);
		
		orderDao.deleteOrders(orderId);
	}
}
