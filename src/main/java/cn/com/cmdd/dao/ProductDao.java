package cn.com.cmdd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.cmdd.domain.Product;
import cn.com.cmdd.domain.User;

/**
 * 
 * @typeName ProductDao
 * @description 
		Summary : TODO 
		Member Property :TODO
		Member Method:TODO
 * @author yusiqing
 * @date 2017年6月14日 上午7:55:04
 */
public interface ProductDao{
	//:+ 
	void insert(Product dt);
	void insertById(Product dt);

	//:-
	void delete(Long id);
	
	//:\
	void update(Product dt);
	
	//:/
	Product select(@Param("id") Long id);
	List<Product> selectListByShopIdAndIdAndCategoryId(
            @Param("shop_id") Integer shop_id,
            @Param("id") Long id,
            @Param("category_id") Long category_id
    );
	
	//同步数据
	List<Product> selectByIsUpload(Integer shopId, Integer sysnStatus);
	//int updateIsUpload(Long id,Integer sysnStatus);
}
