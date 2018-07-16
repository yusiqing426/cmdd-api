package cn.com.cmdd.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.BannerShopDao;
import cn.com.cmdd.domain.BannerShop;

import java.util.List;

@Service
public class BannerShopService {
	private final static Logger LOGGER = LoggerFactory.getLogger(BannerShopService.class);
	
	@Autowired
	private BannerShopDao bannerShopDao;
	
	
	
	@Transactional
	public Integer addBannerShop(BannerShop bannerShop){
		
		bannerShopDao.addBannerShop(bannerShop);
		
		return bannerShop.getId();
	}	
	
	@Transactional
	public void deleteBannerShop(int id){
		
		bannerShopDao.deleteBannerShop(id);
		 
	}
	
	@Transactional
	public List<BannerShop> getBannerShopByBannerId(Long banner_id){
		
		return bannerShopDao.getBannerShopByBannerId(banner_id);
	}
	
	@Transactional
	public void updateBannerShop(BannerShop bannerShop) {
		
		bannerShopDao.updateBannerShop(bannerShop);
	}
	
	@Transactional
	public List<BannerShop> getBannerShopByShopId(Integer shop_id){
		
		return bannerShopDao.getBannerShopByShopId(shop_id);
	}
	
}
