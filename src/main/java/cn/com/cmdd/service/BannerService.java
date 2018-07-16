package cn.com.cmdd.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.BannerDao;
import cn.com.cmdd.dao.BannerShopDao;
import cn.com.cmdd.dao.ImageDao;
import cn.com.cmdd.domain.Banner;
import cn.com.cmdd.domain.BannerShop;

@Service
public class BannerService {
	private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BannerService.class);
	
	@Autowired
	private BannerDao bannerDao;
	
	@Autowired
	private BannerShopDao bannerShopDao;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ImageDao imageDao;
	
	@Transactional
	public Integer addBanner(Banner banner){
		//TODO:
		//bannerDao.add(banner);
		
		return banner.getId();
	}	
	
	@Transactional
	public Long addImage(Integer id, byte[] file) throws IOException {
		Banner banner = new Banner();
		banner = bannerDao.getBanner(id);
		Long image_id = banner.getBanner_id();
		if(image_id != 0){
			image_id = imageService.saveImage(image_id,file);
		}else{
			//Image image = new Image();
			//image_id = imageService.addImage(file,image);
			
			banner.setBanner_id(image_id);
			
		}
		return image_id;
	}
	
	
	@Transactional
	public void deleteBanner(int id){
		
		Banner banner = bannerDao.getBanner(id);
		 Long banner_id = banner.getBanner_id();
		 imageDao.delete(banner_id);
		 bannerDao.deleteBanner(id);
		 List<BannerShop> list = bannerShopDao.getBannerShopByBannerId(banner_id);
		 for (BannerShop bannerShop:list){
			 bannerShopDao.deleteBannerShop(bannerShop.getId());
		 }
		 
		
	}
	
	
	@Transactional
	public Banner getBanner(int id){
		
		return bannerDao.getBanner(id);
	}

	
	@Transactional
	public List<Banner> getBannerListByAgentId(int agent_id){
		
		return bannerDao.getBannerListByAgentId(agent_id);
	}
	
	@Transactional
	public void updateBanner(Banner banner) {
		
		bannerDao.updateBanner(banner);
	}
	
	
}
