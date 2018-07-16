package cn.com.cmdd.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.ShopDao;
import cn.com.cmdd.dao.ShopPayDao;
import cn.com.cmdd.domain.ShopPay;
import cn.com.cmdd.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShopPayService {
	private final static Logger LOGGER = LoggerFactory.getLogger(ShopPayService.class);
	
	@Autowired
	private ShopPayDao shopPayDao;
	
	@Autowired
	private ShopDao shopDao;
	
	@Transactional
	public Integer addShopPay(ShopPay shop_pay){
		
		shopPayDao.addShopPay(shop_pay);
		
		return shop_pay.getId();
	}	
	
	@Transactional
	public ShopPay getShopPayById(int id){
		
		return shopPayDao.getShopPayById(id);
	}

	@Transactional
	public List<ShopPay> getShopPayListByAgentId(int agent_id){
				
		/*		Date date = new Date();
	
			Integer pay_on = date.getDate();
				List<Shop> shopList = shopDao.getShopList();
				for(Shop shop : shopList){
				if(pay_on == shop.getPay_no()){
					return shopPayDao.getShopPayListByAgentId(agent_id);
				}else if(pay_on == shop.getPayable_date()){
					ShopPay sp = new ShopPay();
					sp.setIs_pay(0);
					sp.setPay_date(pay_on);
					sp.setShop_id(shop.getId());
					sp.setTotal_payable(100.0);
					shopPayDao.addShopPay(sp);
					
					shop.setPay_no(pay_on);
					shopDao.updateShop(shop);
					
				}
				
			}*/
		return shopPayDao.getShopPayListByAgentId(agent_id);
		
	}
	
	@Transactional
	public List<ShopPay> getShopPayListByShopId(Integer shop_id){
		
		return shopPayDao.getShopPayListByShopId(shop_id);
	}
	
	@Transactional
	public void updateShopPay(ShopPay shop_pay){
		
		shopPayDao.updateShopPay(shop_pay);
	}
	
	@Transactional
	public ShopPay getShopPayByTime(Date start_time, Date endTime, Integer agent_id, Integer shop_id)throws Exception{
		List<ShopPay> shopPayByTimeList = new ArrayList<ShopPay>();
		if(null==agent_id){
			if(null!=start_time&&null!=endTime){
				String time1 = DateUtil.getYyyyMMdd_(endTime);
				String time2 = time1+" 23:59:59";
				//TODO:171113
				Date end_time = new Date(time2);
				//Date date = DateUtil.yYYYYIMMIDD_ToDate(time2);
				//当前shopPayByTimeList根据条件（时间范围内没记录）查询总能查出一个[null]对象，
				//正常情况时间范围内没记录查询应该是size（）为0，所以目前注释掉以前判断，添加如下判断
				shopPayByTimeList = shopPayDao.getShopPayByTime(start_time,end_time,agent_id,shop_id);
				if(1==shopPayByTimeList.size()&&null==(shopPayByTimeList.get(0))){
					throw new Exception("该时间段内没有缴费记录");
				}
				
				/*if(0==shopPayByTimeList.size()||null==shopPayByTimeList){
					throw new Exception("该时间段内没有缴费记录");
				}*/
			}else{
				shopPayByTimeList = shopPayDao.getShopPayByTime(start_time, endTime,agent_id,null);
				if(0==shopPayByTimeList.size()||null==shopPayByTimeList){
					throw new Exception("该时间段内没有缴费记录");
				}
			}
		}else{
			List<ShopPay> shopPayListByAgentId = shopPayDao.getShopPayListByAgentId(agent_id);
			if(null==shopPayListByAgentId||shopPayListByAgentId.size()==0){
				 throw new Exception("该代理商下商户暂无缴费记录");
			}else if(null!=shop_id){
				ShopPay shopPayById = shopPayDao.getShopPayById(shop_id);
				if(null == shopPayById){
					throw new Exception("该商户暂无缴费记录");
				}
			}
			if(null!=start_time&&null!=endTime){
				String time1 = DateUtil.getYyyyMMdd_(endTime);
				String time2 = time1+" 23:59:59";
				//TODO:171113
				//Date end_time = new Date(time2);
				Date date = DateUtil.yYYYYIMMIDD_ToDate(time2);
				shopPayByTimeList = shopPayDao.getShopPayByTime(start_time,date,agent_id,shop_id);
				if(0==shopPayByTimeList.size()||null==shopPayByTimeList){
					throw new Exception("该时间段内没有缴费记录");
				}
			}else{
				shopPayByTimeList = shopPayDao.getShopPayByTime(start_time, endTime,agent_id,shop_id);
				if(0==shopPayByTimeList.size()||null==shopPayByTimeList){
					throw new Exception("该时间段内没有缴费记录");
				}
			}
		}
		/*
		List<ShopPay> shopPayByTimeList = new  ArrayList<ShopPay>();
		if(null!=endTime){
			String time1 = DateUtil.getYyyyMMdd_(endTime);
			String time2 = time1+" 23:59:59";
			Date end_time = new Date(time2);
			shopPayByTimeList = shopPayDao.getShopPayByTime(start_time, end_time,agent_id,shop_id);
		}else{
			shopPayByTimeList = shopPayDao.getShopPayByTime(start_time, null,agent_id,shop_id);
		}*/
		ShopPay shopPay = new ShopPay();
		if(shopPayByTimeList.size()==1){
			 shopPayByTimeList.get(0).getPayable();
			 if(shopPayByTimeList.get(0).getIs_pay()==0){
				 shopPay.setUnpaid(shopPayByTimeList.get(0).getPayable());
				 shopPay.setPaid(0);
			 }
			 if(shopPayByTimeList.get(0).getIs_pay()==1){
				 shopPay.setPaid(shopPayByTimeList.get(0).getPayable());
					shopPay.setUnpaid(0);
			 }
		 }else{
			 for(ShopPay shopPays : shopPayByTimeList){
					if(0 == shopPays.getIs_pay()){
						shopPay.setUnpaid(shopPays.getPayable());
					}else if(1 == shopPays.getIs_pay()){
						shopPay.setPaid(shopPays.getPayable());
					}	
				}
		 }
		shopPay.setPayable(shopPay.getUnpaid() + shopPay.getPaid());
		return shopPay;
	}
	
}
