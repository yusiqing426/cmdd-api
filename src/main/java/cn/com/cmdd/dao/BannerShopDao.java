package cn.com.cmdd.dao;

import java.util.List;

import cn.com.cmdd.domain.BannerShop;

public interface BannerShopDao {
	
	void addBannerShop(BannerShop bannerShop);
	
	void deleteBannerShop(Integer id);
	
	void updateBannerShop(BannerShop bannerShop);
	
	List<BannerShop> getBannerShopByBannerId(Long bannerId);
	
	List<BannerShop> getBannerShopByShopId(Integer shopId);

}
