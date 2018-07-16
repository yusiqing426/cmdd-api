package cn.com.cmdd.dao;



import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.cmdd.domain.ShopPay;

public interface ShopPayDao {
	

	void addShopPay(ShopPay shopPay);
	
	ShopPay getShopPayById(int id);
	
	List<ShopPay> getShopPayListByAgentId(int agent_id);
	
	List<ShopPay> getShopPayListByShopId(Integer shop_id);
	
	void updateShopPay(ShopPay shop);
	
	List<ShopPay> getShopPayByTime(@Param("start_time") Date start_time,
                                   @Param("end_time") Date end_time,
                                   @Param("agent_id") Integer agent_id,
                                   @Param("shop_id") Integer shop_id);
}
