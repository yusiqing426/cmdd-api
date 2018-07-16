package cn.com.cmdd.dao;

import java.util.List;

import cn.com.cmdd.domain.Dining_table;
import cn.com.cmdd.domain.Image;

public interface ImageDao {

	Image select(Long id);

	void update(Image image);
	void updateSync_status(Long image_id);

	void insert(Image image);
	void insertById(Image image);

	void delete(Long logo_id);
	
	//同步数据
	List<Image> selectByIsUpload(Integer shop_id);
	//int updateIsUpload(Long id);

}
