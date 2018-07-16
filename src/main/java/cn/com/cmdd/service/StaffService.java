package cn.com.cmdd.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.ShopDao;
import cn.com.cmdd.dao.StaffDao;
import cn.com.cmdd.dao.UserDao;
import cn.com.cmdd.domain.Shop;
import cn.com.cmdd.domain.Staff;
import cn.com.cmdd.domain.User;
import cn.com.cmdd.util.Md5Helper;

import java.util.List;

@Service
public class StaffService {
	private final static Logger LOGGER = LoggerFactory.getLogger(StaffService.class);
	
	@Autowired
	private StaffDao staffDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ShopDao shopDao;
	
	@Transactional
	public void addStaff(Staff staff){


		String pwdencry = Md5Helper.MD5Encode(staff.getPassword());
		staff.setPassword(pwdencry);
		String account;
		//Integer shop_id = staff.getShop_id();
		List<Staff> staffList = staffDao.getStaffList();
		int size = staffList.size();
		if(size==0){
			account = "f100001";
		}else{
			int index = (staffList.size())-1;
			String str = staffList.get(index).getAccount();
			String[] split = str.split("f");
			Integer sss = Integer.parseInt(split[1])+1;
			account = "f"+sss;
		}
		staff.setAccount(account);
		staffDao.addStaff(staff);
		Long staffId = staff.getId();
		LOGGER.info("staffId --- "+staffId);
		User user = new User();
		user.setAccount(staff.getAccount());
		user.setUser_id(staff.getId());
		user.setUser_key(staff.getUser_key());

		//user.setSync_status(1);
		userDao.addUser(user);
		
		
	}	
	
	@Transactional
	public void deleteStaff(Long id){
		
		 staffDao.deleteStaff(id);
	}
	
	
	@Transactional
	public Staff getStaff(Long id){
		
		return staffDao.getStaffById(id);
	}
	
	@Transactional
	public List<Staff> getStaffListByShop_id(Integer shop_id){
		
		return staffDao.getStaffListByShop_id(shop_id);
	}
	
	@Transactional
	public List<Staff> getStaffList(){
		
		return staffDao.getStaffList();
	}

	
	@Transactional
	public void updateStaff(Staff staff){

		staffDao.updateStaff(staff);
	}
	
	@Transactional
	public Long updateStaffPassword(Long id,String oldPwd,String newPwd) throws Exception{
		
		Staff staff = staffDao.getStaffById(id);
		String pwdencry = Md5Helper.MD5Encode(oldPwd);
		if(pwdencry.equals(staff.getPassword())){


			shopDao.updateStaffPassword(id, pwdencry);
		}else{
			throw new Exception("原密码错误");
		}
		return id;
	}
	
	@Transactional
	public Integer updateShopPassword(Integer id,String oldPwd,String newPwd) throws Exception{
		
		Shop shop = shopDao.getShopById(id);
		String pwdencry = Md5Helper.MD5Encode(oldPwd);
		if(pwdencry.equals(shop.getPassword())){
			pwdencry = Md5Helper.MD5Encode(newPwd);
			shopDao.updateShopPassword(id, pwdencry);
		}else{
			throw new Exception("原密码错误");
		}
		return id;
	}
	
}
