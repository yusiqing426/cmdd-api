package cn.com.cmdd.dao;

import java.util.List;

import cn.com.cmdd.domain.Banner;
import cn.com.cmdd.domain.Shop;

public interface ShopDao {
	void addShop(Shop shop);
	void insertById(Shop shop);

	void deleteShop(Integer id);
	
	Shop getShopById(Integer id);
	
	Shop getShopByAccount(String account);
	
	List<Shop> getShopList();
	
	List<Shop> getShopListByAgent_id(Integer agent_id);
	
	void updateShop(Shop shop);
	
	void updateShopPayCodeId(Shop shop);
	
	void updateAgentPassword(Integer id, String newPwd);
	
	void updateShopPassword(Integer id, String newPwd);
	
	void updateStaffPassword(Long id, String newPwd);
	
	List<Banner> getBannerListByAgentId(Integer agent_id);

	List<Shop> selectBySyncStatusAndShopId(Integer shop_id);
	
	//public abstract List<Banner> getBannerListByShopId(Integer shop_id);
}
