package cn.com.cmdd.dao;



import java.util.List;

import cn.com.cmdd.domain.Staff;
import cn.com.cmdd.domain.User;

public interface StaffDao {
	Long addStaff(Staff staff);
	void insertById(Staff staff);

	Staff getStaffById(Long id);

	List<Staff> getStaffListByShop_id(Integer shop_id);
	
	List<Staff> getStaffList();

	void updateStaff(Staff staff);
	
	void deleteStaff(Long id);
	
	void updateStaffPassword(Long id, String newPwd);
	
	void updateShopPassword(Long id, String newPwd);
	
	//同步数据
	List<Staff> selectByIsUpload(Integer shop_id, Integer sysn_status);
	//void updateIsUpload(Long id);


}
