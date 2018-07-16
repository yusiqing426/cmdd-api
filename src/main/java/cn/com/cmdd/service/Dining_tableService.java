package cn.com.cmdd.service;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;

import cn.com.cmdd.dao.Dining_tableDao;
import cn.com.cmdd.dao.IPDao;
import cn.com.cmdd.dao.ImageDao;
import cn.com.cmdd.domain.Dining_table;
import cn.com.cmdd.domain.IP;
import cn.com.cmdd.domain.Image;
import cn.com.cmdd.util.EncodeImgZxing;

@Service
@Transactional
public class Dining_tableService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(Dining_tableService.class);
	
	@Autowired
	private Dining_tableDao diningTableDao;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ImageDao imageDao;
	
	@Autowired
	private IPDao ipDao;


	public Long saveDining_table(Dining_table dt){
		 try {

			 diningTableDao.insert(dt);
			 Long diningTableId = dt.getId();
			
			//正式服
			//sBufferedImage encodeImg = EncodeImgZxing.encodeImg("http://admin.chanmaodd.com/www/#/table/"+diningTableId);
			
			//测试服
			
			IP ip = ipDao.select(dt.getShop_id());		
			
			BufferedImage encodeImg = EncodeImgZxing.encodeImg("http://192.168.0.143:8082/admin/app/www/#/table/"+diningTableId);
			
			
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();			
			boolean booWrite = ImageIO.write(encodeImg,"png",byteArrayOutputStream);
			byte[] byteArray = byteArrayOutputStream.toByteArray();	
			Image image = new Image();
			image.setFile_size((long)byteArray.length);
			image.setOrigin_name("桌位id"+dt.getId()+"的二维码图片");
			imageDao.insert(image);
			
			Long saveImageId = imageService.saveImage(image.getId(), byteArray);
			
			Dining_table dining_table = new Dining_table();		
			dining_table.setId(diningTableId);
			dining_table.setLogo_id(saveImageId);
			diningTableDao.updateDining_table(dining_table);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return dt.getId();
	}
	
	public void deleteDining_table(Long id){

		diningTableDao.deleteDining_table(id);
	}
	
	public void updateDining_table(Dining_table dt){

        diningTableDao.updateDining_table(dt);
	}
	
	public List<Dining_table> selectByShopId(Integer shop_id){

		return diningTableDao.getDining_table(shop_id,null);
	}
	
	public Dining_table select(Long id){

		return diningTableDao.select(id);
	}
	
	public Long insertById(Dining_table diningTable){
		//diningTableDao.insertById(diningTable);
		
		 /*diningTableDao.insert(dt);
		 Long diningTableId = dt.getId();*/
		
		//正式服
		//sBufferedImage encodeImg = EncodeImgZxing.encodeImg("http://admin.chanmaodd.com/www/#/table/"+diningTableId);
		
		//测试服
		IP ip = ipDao.select(diningTable.getShop_id());
		 
		BufferedImage encodeImg = EncodeImgZxing.encodeImg("http://192.168.0.143:8082/admin/app/www/#/table/"+diningTable.getId());
		
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();			
		try {
			boolean booWrite = ImageIO.write(encodeImg,"png",byteArrayOutputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] byteArray = byteArrayOutputStream.toByteArray();	
		Image image = new Image();
		image.setFile_size((long)byteArray.length);
		image.setOrigin_name("桌位id"+diningTable.getId()+"的二维码图片");
		imageDao.insert(image);		
		Long saveImageId = imageService.saveImage(image.getId(), byteArray);
		LOGGER.info("saveImageId ---- "+saveImageId);
		//Dining_table dining_table = new Dining_table();		
	
		diningTable.setLogo_id(saveImageId);
		diningTableDao.insertById(diningTable);
		
		return diningTable.getId();
	}
}
