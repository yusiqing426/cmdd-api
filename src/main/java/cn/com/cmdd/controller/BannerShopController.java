package cn.com.cmdd.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.com.cmdd.constant.KEYS;
import cn.com.cmdd.domain.BannerShop;
import cn.com.cmdd.service.BannerShopService;
import cn.com.cmdd.util.ResponseObject;


@Controller//@CrossOrigin(origins = "*")
public class BannerShopController {
	private final static Logger LOGGER = LoggerFactory.getLogger(BannerShopController.class);
	
	@Autowired
	private BannerShopService bannerShopService;

	@RequestMapping(value="/banner_shop",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject addBannerShop(HttpServletRequest request, HttpServletResponse response, @RequestBody BannerShop bannerShop){
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try{
			Integer id = bannerShopService.addBannerShop(bannerShop);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("banner_id", id);
			responseObject.msg = resultMap;
		}catch(Exception e){
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/banner/{id}/banner_shop/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getBannerShopList(HttpServletRequest request, HttpServletResponse response,@PathVariable("id")Long banner_id){
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			responseObject.msg = bannerShopService.getBannerShopByBannerId(banner_id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	
	@RequestMapping(value="/banner_shop/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteBannerShop(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id){
		if(!AuthCheck.UserCheck(request, response, KEYS.AGENT)){
			return null;
		}
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			bannerShopService.deleteBannerShop(id);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("banner_id", id);
			responseObject.msg = resultMap;
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	
	@RequestMapping(value="/banner_shop/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updataeBannerShop(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id, @RequestBody BannerShop bannerShop){
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			bannerShopService.updateBannerShop(bannerShop);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("banner_id", id);
			responseObject.msg = resultMap;
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
}
