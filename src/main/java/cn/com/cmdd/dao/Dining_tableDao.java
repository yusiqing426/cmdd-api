package cn.com.cmdd.dao;


import org.apache.ibatis.annotations.Param;

import cn.com.cmdd.domain.Dining_table;
import cn.com.cmdd.domain.Kitchen;

import java.util.List;

public interface Dining_tableDao{
	//+
	Long insert(Dining_table dt);
	Long insertById(Dining_table dt);
	//-
	void deleteDining_table(Long id);
	//\
	void updateDining_table(Dining_table dt);
	void updateStatus(@Param("status") Integer status, @Param("id") Long id);
	///
	List<Dining_table> getDining_table(@Param("shop_id") Integer shop_id, @Param("id") Long id);
	
	Dining_table select(@Param("id") Long id);
	
	List<Dining_table> selectListByShopId(@Param("shopId") Integer shopId);
	
	//同步数据
	List<Dining_table> selectByIsUpload(Integer shop_id);
	//int updateIsUpload(Long id,Integer sysnStatus);
	
}
