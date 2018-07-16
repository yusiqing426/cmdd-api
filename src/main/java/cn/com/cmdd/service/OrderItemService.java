package cn.com.cmdd.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.OrderItemDao;
import cn.com.cmdd.dao.ProductDao;
import cn.com.cmdd.domain.OrderItem;
import cn.com.cmdd.domain.Product;
import cn.com.cmdd.domain.ProductCount;
import cn.com.cmdd.util.DateUtil;
import cn.com.cmdd.util.DoubleUtil;


@Service
@Transactional
public class OrderItemService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(OrderItemService.class);
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Autowired
	private ProductDao productDao;

	public Long saveAdditionOrderItem(OrderItem orderItem){
		//XXX:-\
		Long orderId = orderItem.getOrder_id();
		List<OrderItem> orderItemList = orderItemDao.selectListByOrderId(orderId);

		if(orderItemList.size()>0){	
			for (OrderItem orderItem2 : orderItemList) {
				if(orderItem2.getProduct_id().equals(orderItem.getProduct_id())&&orderItem2.getStatus_id().equals(0)){
					orderItem2.setQuantity(DoubleUtil.sum(orderItem2.getQuantity(), orderItem.getQuantity()));
					//XXX:-\
					orderItem2.setStatus_pay(0);

                    //if(!orderItem2.getSync_status().equals(new Integer(0)))orderItem2.setSync_status(1);

					orderItemDao.update(orderItem2);
					
					LOGGER.info("222222");
					LOGGER.info(orderItem2.getId().toString());
					return orderItem2.getId();
				}
			}
		}
		//FIXME:默认值增加代码耦合性
		//XXX:-\
		orderItem.setStatus_id(0);
		orderItem.setStatus_pay(0);

		//orderItem.setSync_status(1);
		orderItemDao.insert(orderItem);

		return orderItem.getId();
	}
	

	public List<OrderItem> getOrderItem(Long id,Long order_id,Long category_id,Long product_id,Date start_time,Date end_time) throws ParseException {
		
		
		if(end_time!=null){
			String strEnd_time = DateUtil.getYyyyMMdd_(end_time);
			String strend_time2 = strEnd_time+" 23:59:59";
			end_time = new Date(strend_time2);
			//end_time = DateUtil.yYYYYIMMIDD_ToDate(strend_time2);
		}
		
		List<OrderItem> orderItems = orderItemDao.getOrderItem(id,order_id,category_id,product_id,start_time,end_time);
		//XXX
		if(orderItems.size()>0){
			for (OrderItem orderItem : orderItems) {
				Product product = productDao.select(orderItem.getProduct_id());
				orderItem.setP(product);
			} 
		}
		return orderItems;
	}
	
	public List<ProductCount> getOrderItemCount(String sumField,Integer shop_id,Long category_id,Long product_id,Date start_time,Date end_time) throws ParseException {
		
		if(end_time!=null){
			String strEnd_time = DateUtil.getYyyyMMdd_(end_time);
			String strend_time2 = strEnd_time+" 23:59:59";
			//TODO:17/11/13
			end_time = new Date(strend_time2);
			//Date date = DateUtil.yYYYYIMMIDD_ToDate(strend_time2);
		}
		//FIXME:
		sumField = "quantity";
		List<ProductCount> orderItemCounts = orderItemDao.getOrderItemCount(sumField,shop_id,category_id,product_id,start_time,end_time,null,1);
		
		return orderItemCounts.size()==0?null:orderItemCounts;
	}
	
	public double getOrderItemCount_is_lottery(String sumField,Integer shop_id,Long category_id,Long product_id,Date start_time,Date end_time) throws ParseException {
		
		if(end_time!=null){
			String strEnd_time = DateUtil.getYyyyMMdd_(end_time);
			String strend_time2 = strEnd_time+" 23:59:59";
			//TODO:171113
			end_time = new Date(strend_time2);
			//end_time = DateUtil.yYYYYIMMIDD_ToDate(strend_time2);
		}
		
		List<ProductCount> orderItemCounts_is_lottery = orderItemDao.getOrderItemCount(null,shop_id,category_id,product_id,start_time,end_time,1,1);
		double sumIsLottery = 0.00;
		if(orderItemCounts_is_lottery.size()==0) {
			return 0.00;
		}else {
			for (ProductCount productCount : orderItemCounts_is_lottery) {
				if(productCount.getIs_promotion()==0) {
					sumIsLottery+=DoubleUtil.mul(productCount.getUnit_price(), productCount.getQuantity());
				}else if(productCount.getIs_promotion()==1){
					sumIsLottery+=DoubleUtil.mul(productCount.getPromotion_price(), productCount.getQuantity());
				}
			}
		}
		return sumIsLottery;
	}
}
