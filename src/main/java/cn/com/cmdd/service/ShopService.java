package cn.com.cmdd.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.util.LongField;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.AgentDao;
import cn.com.cmdd.dao.ImageDao;
import cn.com.cmdd.dao.ShopDao;
import cn.com.cmdd.dao.UserDao;
import cn.com.cmdd.domain.Agent;
import cn.com.cmdd.domain.Banner;
import cn.com.cmdd.domain.Shop;
import cn.com.cmdd.domain.User;
import cn.com.cmdd.util.Md5Helper;

@Service
public class ShopService {
	private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ShopService.class);
	
	@Autowired
	private ShopDao shopDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AgentDao agentDao;
	
	@Autowired
	private ImageDao imagedDao;
		
	@Transactional
	public Integer addShop(Shop shop){

		String pwdencry = Md5Helper.MD5Encode(shop.getPassword());
        LOGGER.info("pwdencry --- "+ pwdencry);
		shop.setPassword(pwdencry);
			
		String account;
		List<Shop> shopList = shopDao.getShopList();
		int size = shopList.size();
		
		if(size==0){
			account = "s100001";
		}else{
			int index = (shopList.size())-1;
			String str = shopList.get(index).getAccount();
			String[] split = str.split("s");
			Integer sss = Integer.parseInt(split[1])+1;
			account = "s"+sss;
			
		}
		shop.setAccount(account);
		shop.setIs_banner(0);
		shop.setIs_unified_print(1);
		
		shop.setPayable_date(1);
		shopDao.addShop(shop);
		LOGGER.info("shop_id ---- "+shop.getId());
		User user = new User();
		user.setAccount(shop.getAccount());
		user.setUser_id(shop.getId().longValue());
		user.setUser_key(shop.getUser_key());
		userDao.addUser(user);
		
		
		return shop.getId();
	}	
	
	
	@Transactional
	public void deleteShop(Integer id){
		
		 shopDao.deleteShop(id);
	}
	
	
	@Transactional
	public Shop getShop(Integer id){
		
		return shopDao.getShopById(id);
	}
	
	@Transactional
	public List<Shop> getShopList(){
		
		return shopDao.getShopList();
	}

	
	@Transactional
	public List<Shop> getShopListByAgent_id(Integer agent_id){
		
		return shopDao.getShopListByAgent_id(agent_id);
	}
	
	@Transactional
	public List<Banner> getBannerListByAgentId(Integer agent_id) throws Exception{
		
		return shopDao.getBannerListByAgentId(agent_id);
	}
	
	/*@Transactional
	public List<Banner> getBannerListByShopId(Integer shop_id) throws Exception{
		
		return shopDao.getBannerListByShopId(shop_id);
	}*/
	
	@Transactional
	public void updateShop(Shop shop){
	
		String password = shop.getPassword();
		if(password!=null&&password.equals("000000")){
			String pwdencry = Md5Helper.MD5Encode(password);
			shop.setPassword(pwdencry);
		}
		shopDao.updateShop(shop);
		
	}

	@Transactional
	public void updateShopPayCodeId(Shop shop){
		Long payCodeId = shop.getPay_code_id();
		if(payCodeId!=null) {
			imagedDao.delete(payCodeId);
			shop.setPay_code_id(null);
			shopDao.updateShopPayCodeId(shop);
		}else {
			return;
		}
		
		
	}
	
	@Transactional
	public Integer updateAgentPassword(int id,String oldPwd,String newPwd) throws Exception{
		
		Agent agent = agentDao.getAgentById(id);
		String pwdencry = Md5Helper.MD5Encode(oldPwd);
		if(pwdencry.equals(agent.getPassword())){
			pwdencry = Md5Helper.MD5Encode(newPwd);
			shopDao.updateAgentPassword(id, pwdencry);
		}else{
			throw new Exception("原密码错误");
		}
		return id;
	}
	
}
