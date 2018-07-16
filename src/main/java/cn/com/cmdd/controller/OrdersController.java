package cn.com.cmdd.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.cmdd.dao.Dining_tableDao;
import cn.com.cmdd.dao.OrderItemDao;
import cn.com.cmdd.dao.OrdersDao;
import cn.com.cmdd.dao.ProductDao;
import cn.com.cmdd.domain.Dining_table;
import cn.com.cmdd.domain.OrderCount;
import cn.com.cmdd.domain.OrderItem;
import cn.com.cmdd.domain.Orders;
import cn.com.cmdd.domain.OrdersCountModel;
import cn.com.cmdd.domain.Product;
import cn.com.cmdd.domain.UnifyOrder;
import cn.com.cmdd.service.ExportExcelService;
import cn.com.cmdd.service.MemberRechargeLogService;
import cn.com.cmdd.service.OrderItemService;
import cn.com.cmdd.service.OrdersService;
import cn.com.cmdd.util.ResponseObject;

/**
 * 
 * @typeName OrdersController
 * @description 
		Summary : TODO 
		Member Property :TODO
		Member Method:TODO
 * @author yusiqing
 * @date 2017年6月22日 下午12:44:45
 */
@Controller//@CrossOrigin(origins = "*")
public class OrdersController{
	
	private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OrdersController.class);
	
	@Autowired
	private OrdersService ordersService;

	@Autowired
	private OrdersDao ordersDao;
	
	@Autowired
	private OrderItemService orderItemService;
		
	@Autowired
	private ExportExcelService exportExcelService;
	
	@Autowired
	private MemberRechargeLogService memberRechargeLogService;
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Autowired
	Dining_tableDao diningTableDao;
	
	@Autowired
	private ProductDao productDao;
	@RequestMapping(value="/shop/{id}/orders/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getOrders(HttpServletRequest request,
									HttpServletResponse response,
									@PathVariable("id")	Integer shop_id,
									Integer page_no,
									Integer page_size,
									Long dining_id,
									String type,
									@DateTimeFormat(pattern="yyyy-MM-dd")Date start_time,
									@DateTimeFormat(pattern="yyyy-MM-dd")Date end_time,
									Integer pay_type)
	{

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {
			
			/*订单状态*/
			List<Integer> statusList = new ArrayList<Integer>();
			statusList.add(1);
			statusList.add(2);
			
			HashMap<String, Object> resultMap = new HashMap<String,Object>();
			//订单列表
				//-分页之后的订单集合
			List<Orders> ordersListByPage = ordersService.getOrders(shop_id, null, page_size,page_no,dining_id, start_time, end_time, statusList,pay_type);
				//-订单集合
			List<Orders> ordersList = ordersService.getOrders(shop_id, null, null,null,dining_id, start_time, end_time, statusList,pay_type);
			//订单支付类型统计
			List<OrderCount> ordersCounts = ordersService.getOrdersCount(shop_id, start_time, end_time, statusList);
			//会员充值金额
			Double sumMerberRecharge = memberRechargeLogService.getMemberAmount(shop_id, start_time, end_time);
			sumMerberRecharge = sumMerberRecharge == null?0.00:sumMerberRecharge;
			//会员充值赠送金额
			Double sumMemberGiveAmount = memberRechargeLogService.getMemberGiveAmount(shop_id, start_time, end_time);
			sumMemberGiveAmount = sumMemberGiveAmount==null?0.00:sumMemberGiveAmount;
			//增送金额
			Double sumLottery = orderItemService.getOrderItemCount_is_lottery(null, shop_id, null, null, start_time, end_time);
			sumLottery =  sumLottery==null?0.00:sumLottery;

			OrdersCountModel ordersCountModels = ordersService.countOrder(shop_id, start_time, end_time, statusList,ordersCounts, sumMerberRecharge,sumMemberGiveAmount, sumLottery, ordersListByPage);

			if(type.equals("query")){

				responseObject.msg = ordersCountModels;
								
			}else if(type.equals("export_excel")){
				
				exportExcelService.downLoadExcel(null, null, ordersList,resultMap, null, request, response,ordersCountModels);
			}
			
		} catch (Exception e) {

			responseObject.code = ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

		}
		return responseObject;					
	}
	
	@RequestMapping(value="/orders/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getOrders(HttpServletRequest request,
									HttpServletResponse response,
									@PathVariable("id")Long id)
	{
											
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
	
		try {
			//组合付款订单
			List<Orders> orderList= ordersDao.selectListById(id);
			for (Orders orders:orderList) {
				List<OrderItem> orderItemList = orderItemDao.selectListByOrderId(orders.getId());
				if(orderItemList.size()>0){
					for (OrderItem orderItem : orderItemList){
						Long productId = orderItem.getProduct_id();
						Product product = productDao.select(productId);
						orderItem.setP(product);
					}
				}
				orders.setLoi(orderItemList);
			}
			responseObject.msg = orderList;
			
		}catch(Exception e){
			
			responseObject.code = ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			
		}
		return responseObject;
	}
	
	/***\(3\) 根据桌位id获取该桌未完成订单详细信息**
	GET -H { X-Auth-Token: "xxxxxx" } /dining-table/:id/order*/
	
	
	@RequestMapping(value="/dining-table/{id}/orders",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getDining_tableStatus_idIs0Orders(
															HttpServletRequest request,
															HttpServletResponse response,
															@PathVariable("id")Long dining_table_id)
	{
														
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);		

		try {

			Orders orders = ordersDao.selectByDiningTableAndStatusId(dining_table_id,0);
			List<OrderItem> orderItemList= orderItemDao.selectListByOrderId(orders.getId());
			if(orderItemList.size()>0){
				for (OrderItem orderItem : orderItemList) {
					
					orderItem.setP(productDao.select(orderItem.getProduct_id()));
				}
			}
			orders.setLoi(orderItemList);

			responseObject.msg = orders;

		} catch (Exception e) {

			responseObject.code = ResponseObject.ok;
			responseObject.msg = e.getLocalizedMessage();

		}
		return responseObject;						
	}
	
	
	/***\(4\) 创建订单**
	 POST -H { X-Auth-Token: "xxxxxx" } /order -d */

	@RequestMapping(value="/orders",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject saveOrders(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Orders orders)
	{

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			ordersService.saveOrders(orders);

			HashMap<String, Object> resultMap = new HashMap<String,Object>();
			resultMap.put("orders_id", orders.getId().toString());

			responseObject.msg = resultMap;

		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
		}
		return responseObject;
	}



	@RequestMapping(value="/order/id",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject insertById(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Orders orders)
	{

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			ordersDao.insertById(orders);

		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
		}
		return responseObject;
	}
	
	/**
		修改订单基本信息:不涉及事务
		PUT -H { X-Auth-Token: "xxxxxx" } /order/:id -d
	 */
	
	@RequestMapping(value="/orders/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateOrders(
	
										HttpServletRequest request,
										HttpServletResponse response,
										@PathVariable("id")	Long id,
										@RequestBody Orders orders
										){
									
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
	    		
		try {
			
			ordersService.updateOrders(orders);

			HashMap<String, Object> resultMap = new HashMap<String,Object>();
			resultMap.put("orders_id", id.toString());
			responseObject.msg	= resultMap;
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
		}
		return responseObject;
	}

	@RequestMapping(value="/order/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateSync_status(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")	Long id,
			@RequestBody Orders orders
	)
	{

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			ordersDao.updateSyn_status(orders);
			HashMap<String, Object> resultMap = new HashMap<String,Object>();
			resultMap.put("orders_id", id.toString());
			responseObject.msg	= resultMap;

		} catch (Exception e) {

			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/orders/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteOrder_product(	
												HttpServletRequest request,
												HttpServletResponse response,
												@PathVariable("id")Long orderId)
	{

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			
			ordersDao.deleteOrders(orderId);
			orderItemDao.deleteByOrderId(orderId);
			
			responseObject.msg = "操作成功";
			
		} catch (Exception e) {
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		
		return responseObject;
	}

	@RequestMapping(value="/merge_order",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject unifyOrderNo(@RequestBody UnifyOrder unifyOrder){

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			ordersService.unifyOrder(unifyOrder.getDiningTablePid(),unifyOrder.getDiningTableIdList());

			responseObject.msg = "操作成功";

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

		}
		return responseObject;
	}

	/**
	 * 结账
	 */
	 @RequestMapping(value="/orders/balance_book",method=RequestMethod.PUT)
	 @ResponseBody
	 public ResponseObject balanceBook(@RequestBody(required = false) Orders orders){
		 System.out.println("111111111111111");
		 ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);

		 try {

			 ordersService.balanceBook(orders);

			 responseObject.msg = "操作成功";

		 } catch (Exception e) {

		 	responseObject.code = ResponseObject.serverError;
		 	responseObject.msg = e.getLocalizedMessage();

		 }

		 return responseObject;
	 }

	/**
	 * 重置订单合并状态
	 */
	@RequestMapping(value="/order/resetOrderPno/shop/{shop_id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject resetOrderPno(@PathVariable("shop_id") Integer shopId){

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);

		try{

			ordersDao.resetOrderPno(shopId);
			responseObject.msg = "操作成功";

		}catch (Exception e){

			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
		}

		return responseObject;

	}
	
	/**
	 * 清桌
	 */
	@RequestMapping(value="/clearDiningTable/diningTable/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject clearDiningTable(@PathVariable("id") Long diningTableId){

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);

		try{

			ordersService.clearTable(diningTableId);
			responseObject.msg = "操作成功";

		}catch (Exception e){

			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
		}

		return responseObject;

	}
	
	/**开桌*/
	private static Map<Long,Long> map= new ConcurrentHashMap<Long,Long>();
	private boolean isCache(Long diningTableId) {
		synchronized (map) {
			if(map.containsKey(diningTableId)) {
				return true;
			}else{
				map.put(diningTableId, diningTableId);
			}
		}
		return false;
	}
	@RequestMapping(value="/openTable/diningTable/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject openTable(@PathVariable("id")Long diningTableId,@RequestBody Orders orders){
			
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try{
			if(!isCache(diningTableId)){
				
				try {
					
					 Dining_table diningTable = diningTableDao.select(diningTableId);
					 Integer status = diningTable.getStatus();
					 
					 if(status!=1) {
						 ordersService.openTable(diningTableId, orders);									
						 synchronized (map){
							 map.remove(diningTableId);
						 } 
						 responseObject.msg = "操作成功";
					}else{
						throw new Exception("操作失败:已开桌,请勿重复开桌");
					}
				
				}catch(Exception e) {	
					responseObject.code = ResponseObject.serverError;
					responseObject.msg = e.getLocalizedMessage();
				}finally {
					synchronized (map){
						 map.remove(diningTableId);
					 } 
				}
					 
			 }else{
				 throw new Exception("操作失败:正在处理开桌请求,请勿重复开桌");
			 }	
			
		}catch(Exception e){
			
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			
		}
		
		return responseObject;
		
	}

	@RequestMapping(value="/order/shop/{id}/isUpload/{sync_status}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getIsUpload(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("sync_status")int sync_status,
			@PathVariable("id")Integer shop_id)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			responseObject.msg=ordersDao.selectByIsUpload(shop_id,sync_status);

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

			e.printStackTrace();
		}
		return responseObject;
	}
}
