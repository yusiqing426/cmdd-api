package cn.com.cmdd.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.cmdd.dao.CategoryDao;
import cn.com.cmdd.domain.Category;
import cn.com.cmdd.service.CategoryService;
import cn.com.cmdd.util.ResponseObject;

@Controller//@CrossOrigin(origins = "*")
public class CategoryController {
	
	private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryDao categoryDao;

	@RequestMapping(value="/category/id",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject insertById(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Category category)
	{

		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {
			
			categoryDao.insertById(category);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}

	@RequestMapping(value="/category",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject saveCategory(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Category category)
	{
		
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {
			HashMap<String,Object> resultMap = new HashMap<String,Object>();
			categoryService.saveCategory(category);
			resultMap.put("category_id", category.getId());
			responseObject.msg = resultMap;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/shop/{id}/category/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getCategory(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")Integer shop_id)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			responseObject.msg=categoryService.getCategory(shop_id, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/category/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateCategory(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")Long id,
			@RequestBody Category category
	)
	{

		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			categoryService.updateCategory(category);

			HashMap<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("category_id", id.toString());

			responseObject.msg=resultMap;

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

		}
		return responseObject;
	}

	
	@RequestMapping(value="/category/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getCategoryById(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")Long id
	)
	{
				
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {

			responseObject.msg = categoryDao.select(id);

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

		}
		return responseObject;
	}
	
	@RequestMapping(value="/category/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteCategory(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")Long id)
	{
		
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {

			categoryService.deleteCategory(id);

			HashMap<String, Object> resultMap = new HashMap<String,Object>();
			resultMap.put("Category_id", id.toString());

			responseObject.msg=resultMap;

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

		}
		return responseObject;
	}	
	
	
	@RequestMapping(value="/category/shop/{id}/isUpload/{syncStatus}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject isUpload(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("syncStatus")Integer sysnStatus,
			@PathVariable("id")Integer shipId)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			
			responseObject.msg=categoryDao.selectByIsUpload(shipId,sysnStatus);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
}
	
	