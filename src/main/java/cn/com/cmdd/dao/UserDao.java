package cn.com.cmdd.dao;

import java.util.List;

import cn.com.cmdd.domain.User;

public interface UserDao {
	void addUser(User user);
	void insertById(User user);
	
	User getUserByAccount(String account);
	
	User getUserPlatformByAccount(String account);
	
	User getUserAgentByAccount(String account);
	
	User getUserShopByAccount(String account);
	
	User getUserStaffByAccount(String account);
	//:\
	void update(User user);

	//同步数据
	List<User> selectByIsUpload(Integer shop_id, Integer sysn_status);
	//void updateIsUpload(Long id);
}
