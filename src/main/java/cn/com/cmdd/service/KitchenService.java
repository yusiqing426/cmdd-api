package cn.com.cmdd.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.ImageDao;
import cn.com.cmdd.dao.KitchenDao;
import cn.com.cmdd.domain.Kitchen;

import java.util.List;

@Service
public class KitchenService {
	private final static Logger LOGGER = LoggerFactory.getLogger(KitchenService.class);
	
	@Autowired
	private KitchenDao kitchenDao;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ImageDao imageDao;
	
	@Transactional
	public void addKitchen(Kitchen kitchen){
		//kitchen.setSync_status(1);
		kitchenDao.addKitchen(kitchen);

	
	}	
	
	
	@Transactional
	public void deleteKitchen(Long id){
		
		Kitchen kitchen = kitchenDao.getKitchenById(id);
		imageService.DeleteImage(kitchen.getLogo_id());
		imageDao.delete(kitchen.getLogo_id());
		
		kitchenDao.deleteKitchen(id);
	}
	
	
	@Transactional
	public Kitchen getKitchen(Long id){
		
		return kitchenDao.getKitchenById(id);
	}
	
	@Transactional
	public List<Kitchen> getKitchenList(Integer shop_id){
		
		return kitchenDao.getKitchenListByShop_id(shop_id);
	}

	
	@Transactional
	public void updateKitchen(Kitchen kitchen){
		//if(!kitchen.getSync_status().equals(new Integer(0)))kitchen.setSync_status(1);
		kitchenDao.updateKitchen(kitchen);
	}
	
	
	
}
