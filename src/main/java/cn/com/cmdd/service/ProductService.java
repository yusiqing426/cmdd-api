package cn.com.cmdd.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ser.std.FileSerializer;

import cn.com.cmdd.dao.ProductDao;
import cn.com.cmdd.domain.Product;

import java.util.List;

@Service
@Transactional
public class ProductService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductDao productDao;

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private FileService fileService;
	
	public void saveProduct(Product product){
		
		productDao.insert(product);	
				 
	}
	
	public void deleteProduct(Long id){
		productDao.delete(id);
	}
	
	public void updateProduct(Product product){

		productDao.update(product);
	}
	
	public List<Product> getProduct(Integer shop_id, Long id, Long category_id){

		return productDao.selectListByShopIdAndIdAndCategoryId(shop_id, id, category_id);
	}
}
