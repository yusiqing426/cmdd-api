package cn.com.cmdd.service;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.com.cmdd.dao.ImageDao;
import cn.com.cmdd.domain.Image;
import cn.com.cmdd.util.LevelDBAPI;

@Service
public class ImageService {
	
	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ImageService.class);
	
	@Autowired
	private ImageDao imageDao;
	
	
	public byte[] getImage(Long id) {
		byte[] result = LevelDBAPI.get(id);
		logger.info("image size is" + result.length);
		return result;
	}
	
	public Long saveImage(Long id,byte[] file){
		LevelDBAPI.post(id, file);
		
		return id;
	}
	public Long DeleteImage(Long id) {
		if(id > 0)
			LevelDBAPI.delete(id);
		
		return id;
	}
	
	@Transactional
	public Long addImage(Long id, MultipartFile file,Integer shopId) throws IOException{
		
		if (id != null && id>0){

			Image image = imageDao.select(id);

			image.setOrigin_name(file.getOriginalFilename());
			image.setFile_size(file.getSize());
			image.setShopId(shopId);

			imageDao.update(image);
			
			saveImage(id,file.getBytes());

			return id;
		}
		
		Image image = new Image();
		image.setOrigin_name(file.getOriginalFilename());
		image.setFile_size(file.getSize());
		image.setShopId(shopId);

		imageDao.insert(image);

		Long image_id = image.getId();

		saveImage(image_id,file.getBytes());

		return image_id;
	}



	@Transactional
	public Long insertById(Image image) throws IOException{

		imageDao.insertById(image);
		Long image_id = image.getId();
		
		//local:下载云端数据
		byte[] bytes = new FileService().get(image_id);
		saveImage(image_id,bytes);
		return image_id;
	}

	@Transactional
	public List<Image> getSysn(Integer shopId){
		List<Image> images = imageDao.selectByIsUpload(shopId);
		for (Image image: images
			 ) {
			Long id = image.getId();
			byte[] bytes = getImage(id);
			String url = new FileService().saveByte(id, bytes);
			logger.info("url ---- "+url);
		}
		return images;
	}
	
}
