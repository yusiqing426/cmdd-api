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
import cn.com.cmdd.domain.Banner;
import cn.com.cmdd.service.BannerService;
import cn.com.cmdd.util.ResponseObject;


@Controller//@CrossOrigin(origins = "*")
public class BannerController {
	private final static Logger LOGGER = LoggerFactory.getLogger(BannerController.class);
	
	@Autowired
	private BannerService bannerService;

	@RequestMapping(value="/banner",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject addBanner(HttpServletRequest request, HttpServletResponse response, @RequestBody Banner banner){
		if(!AuthCheck.UserCheck(request, response, KEYS.AGENT)){
			return null;
		}
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try{
			Integer id = bannerService.addBanner(banner);
			
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
	
	@RequestMapping(value="/agent/{id}/banner/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getBanners(HttpServletRequest request, HttpServletResponse response,@PathVariable("id")int agent_id){
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			responseObject.msg = bannerService.getBannerListByAgentId(agent_id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/banner/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getBanner(HttpServletRequest request, HttpServletResponse response,@PathVariable("id")int id){
		if(!AuthCheck.UserCheck(request, response, KEYS.AGENT)){
			return null;
		}
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			responseObject.msg = bannerService.getBanner(id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/banner/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteBanner(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id){
		/*if(!AuthCheck.UserCheck(request, response, KEYS.AGENT)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			bannerService.deleteBanner(id);
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
	
	
	@RequestMapping(value="/banner/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updataeBanner(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id, @RequestBody Banner banner){
		if(!AuthCheck.UserCheck(request, response, KEYS.AGENT)){
			return null;
		}
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			bannerService.updateBanner(banner);
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
