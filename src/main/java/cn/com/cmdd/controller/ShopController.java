package cn.com.cmdd.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.cmdd.dao.ShopDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.com.cmdd.domain.Password;
import cn.com.cmdd.domain.Shop;
import cn.com.cmdd.service.BannerShopService;
import cn.com.cmdd.service.ShopService;
import cn.com.cmdd.util.ResponseObject;

@Controller//@CrossOrigin(origins = "*")
public class ShopController {
	private final static Logger LOGGER = LoggerFactory.getLogger(ShopController.class);

	@Autowired
	private ShopService shopService;


	@Autowired
	private ShopDao shopDao;

	@Autowired
	private BannerShopService bannerShopService;
	
	@RequestMapping(value="/shop",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject addShop(HttpServletRequest request,
								  HttpServletResponse response,
								  @RequestBody Shop shop){
		/*if(!AuthCheck.UserCheck(request, response, KEYS.AGENT)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try{
			Integer id = shopService.addShop(shop);
			
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("shop_id", id.toString());
			responseObject.msg = resultMap;
		}catch(Exception e){
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/shop/id",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject insertById(HttpServletRequest request,
								  HttpServletResponse response,
								  @RequestBody Shop shop){
		/*if(!AuthCheck.UserCheck(request, response, KEYS.AGENT)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try{
			
			shopDao.insertById(shop);	
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("shop_id", shop.getId().toString());
			responseObject.msg = resultMap;
		}catch(Exception e){
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}

	
	
	
	@RequestMapping(value="/agent/{id}/shop/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getShops(@PathVariable("id")int agent_id){
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			responseObject.msg = shopService.getShopListByAgent_id(agent_id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/shop/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getShop(HttpServletRequest request, HttpServletResponse response,@PathVariable("id")Integer id){

		/*
		 * if(!AuthCheck.UserCheck(request, response, KEYS.AGENT)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			responseObject.msg = shopService.getShop(id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/shop/{id}/banner/list")
	@ResponseBody
	public ResponseObject getBannerListByShopId(@PathVariable("id")int id){
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			responseObject.msg = bannerShopService.getBannerShopByShopId(id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/shop/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateShop(HttpServletRequest request, HttpServletResponse response, @PathVariable("id")Integer id, @RequestBody Shop shop){
		/*if(!AuthCheck.UserCheck(request, response,KEYS.AGENT)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			shop.setId(id);
			shopService.updateShop(shop);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("shop_id", id.toString());
			responseObject.msg = resultMap;
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	
	@RequestMapping(value="/shop/pay_code_id/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateShopPayCodeId(
			HttpServletRequest request, 
			HttpServletResponse response, 
			@PathVariable("id")Integer id, 
			@RequestBody Shop shop)
	{
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			shop.setId(id);
			shopService.updateShopPayCodeId(shop);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("shop_id", id.toString());
			responseObject.msg = resultMap;
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	
	@RequestMapping(value="/shop/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteShop(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id){
		/*if(!AuthCheck.UserCheck(request, response, KEYS.AGENT)){
			return null;
		}*/
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			shopService.deleteShop(id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/agent/{id}/password",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateAgentPassword(HttpServletRequest request,
											  HttpServletResponse response,
											  @RequestBody Password passwordFrom, @PathVariable("id")int id){
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Integer sid = shopService.updateAgentPassword(id, passwordFrom.getOld_password(), passwordFrom.getNew_password());
			map.put("user_id", sid.toString());
			responseObject.msg = map;
		}catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
		}
		return responseObject;
	}

	@RequestMapping(value="/shop/{id}/sync",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getIsUpload(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")Integer shop_id)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			responseObject.msg=shopDao.selectBySyncStatusAndShopId(shop_id);

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

			e.printStackTrace();
		}
		return responseObject;
	}
	
}
