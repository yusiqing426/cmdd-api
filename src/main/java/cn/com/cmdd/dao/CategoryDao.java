package cn.com.cmdd.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.cmdd.domain.Category;
import cn.com.cmdd.domain.Dining_table;

public interface CategoryDao {
	
	//:+
	Long saveCategory(Category category);
	Long insertById(Category category);

	//:-
	void deleteCategory(@Param("id") Long id);
	
	//:\
	void updateCategory(Category category);
	/*void updateSync_status(Long id);*/

	//:/
	List<Category> getCategory(@Param("shop_id") Integer shop_id, @Param("id") Long id);
	Category select(Long id);
	//同步数据
	List<Category> selectByIsUpload(Integer shopId, Integer sysnStatus);
	//int updateIsUpload(Long id,Integer sysnStatus);
}
