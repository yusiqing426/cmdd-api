package cn.com.cmdd.dao;

import java.util.List;

import cn.com.cmdd.domain.Kitchen;
import cn.com.cmdd.domain.MemberCard;

public interface KitchenDao {
	void addKitchen(Kitchen kitchen);
	void insertById(Kitchen kitchen);

	Kitchen getKitchenById(Long id);

	void updateKitchen(Kitchen kitchen);
	
	void deleteKitchen(Long id);

	List<Kitchen> getKitchenListByShop_id(Integer shop_id);

	//同步数据
	List<Kitchen> selectByIsUpload(Integer shop_id, Integer sysn_status);
	//sint updateIsUpload(Long id);
}
