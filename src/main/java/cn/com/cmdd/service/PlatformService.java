package cn.com.cmdd.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.PlatformDao;
import cn.com.cmdd.domain.Platform;
import cn.com.cmdd.util.Md5Helper;

@Service
public class PlatformService {
	private final static Logger LOGGER = LoggerFactory.getLogger(PlatformService.class);
	
	@Autowired
	private PlatformDao platformDao;
	
	//得到平台管理者的信息
	public Platform getPlatformById(int id){
		Platform platform = platformDao.getPlatformById(id);
		return platform;
	}
	
	
	//修改平台管理者(platform)的密码
	@Transactional
	public int updatePlatformPassword(int id, String old_password, String new_password) throws Exception {
		
		Platform platform = platformDao.getPlatformById(id);
		System.out.println(platform);
		String pwdencry = Md5Helper.MD5Encode(old_password);
		if(pwdencry.equals(platform.getPassword())){
			pwdencry = Md5Helper.MD5Encode(new_password);
			platformDao.updatePlatformPassword(id, pwdencry);
		}else{
			throw new Exception("原密码错误");
		}
		return id;
		
	}

}




















