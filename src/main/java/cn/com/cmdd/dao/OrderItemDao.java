package cn.com.cmdd.dao;


import org.apache.ibatis.annotations.Param;

import cn.com.cmdd.domain.OrderItem;
import cn.com.cmdd.domain.Product;
import cn.com.cmdd.domain.ProductCount;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderItemDao {
	//+
	void insert(OrderItem orderItem);
	void insertById(OrderItem orderItem);
	
	//-
	void delete(@Param("id") Long id);
	void deleteByOrderId(@Param("orderId") Long orderId);
		
	//\
	void update(OrderItem orderItem);
	
	///
	OrderItem select(@Param("id") Long id);
	List<ProductCount> getOrderItemCount(
            @Param("sumField") String sumField,
            @Param("shop_id") Integer shopId,
            @Param("category_id") Long category_id,
            @Param("product_id") Long product_id,
            @Param("start_time") Date start_time,
            @Param("end_time") Date end_time,
            @Param("is_lottery") Integer is_lottery,
            @Param("status_pay") Integer status_pay
    );
	List<OrderItem> getOrderItem(
            @Param("id") Long id,
            @Param("order_id") Long order_id,
            @Param("category_id") Long category_id,
            @Param("product_id") Long product_id,
            @Param("start_time") Date start_time,
            @Param("end_time") Date end_time
    );
	List<OrderItem> selectListByOrderId(@Param("orderId") Long orderId);
	
	//FIXME:菜品状态筛选
	Double selectSUMmoneyPreferentialDiscount(
            @Param("shopId") Integer shopId,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime
    );
	
	//同步数据
	List<OrderItem> selectByIsUpload(Integer shop_id, Integer sysn_status);
	int updateIsUpload(Long id);
}
