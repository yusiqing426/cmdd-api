package cn.com.cmdd.dao;


import org.apache.ibatis.annotations.Param;

import cn.com.cmdd.domain.Call_service;

import java.util.List;

public interface Call_serviceDao {
	void saveCall_service(Call_service call_service);
	
	void deleteCall_service(@Param("id") Integer id);
	void deleteByOrderId(@Param("order_id") Long orderId);
	void deleteByDiningTableId(@Param("dining_table_id") Long diningTableId);
	
	List<Call_service> getCall_service(@Param("shop_id") Integer shop_id);
	void updateCall_service(Call_service call_service);
	
}
