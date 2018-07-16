package cn.com.cmdd.service;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.CategoryDao;
import cn.com.cmdd.domain.Category;

import java.util.List;

@Service
@Transactional
public class CategoryService {
	private final static Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);
	
	@Autowired
	private CategoryDao categoryDao;
	
	public Long saveCategory(Category category){

       categoryDao.saveCategory(category);
		 
        return category.getId();
		
	}
	
	public void deleteCategory(Long id){

		categoryDao.deleteCategory(id);
		
	}
	
	public void updateCategory(Category category){

        categoryDao.updateCategory(category);
	}
	
	public List<Category> getCategory(Integer shop_id, Long id){

		return categoryDao.getCategory(shop_id,id);
	}
}
