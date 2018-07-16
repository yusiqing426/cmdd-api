package cn.com.cmdd.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.cmdd.dao.Dining_tableDao;
import cn.com.cmdd.domain.Dining_table;
import cn.com.cmdd.service.Dining_tableService;
import cn.com.cmdd.util.ResponseObject;

@Controller
public class Dining_tableController {
	
	private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Dining_tableController.class);
	
	@Autowired
	private Dining_tableService dts;
	
	@Autowired
	private Dining_tableDao diningTableDao;
		
	@RequestMapping(value="/dining-table",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject saveDining_table(
			HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestBody Dining_table dt)
	{
		
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/
	
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
						
		try {
			dts.saveDining_table(dt);
			HashMap<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("dining_table_id", dt.getId().toString());
			responseObject.msg=resultMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}

	@RequestMapping(value="/dining_table/id",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject insertById(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Dining_table dt)
	{

		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {
			
			dts.insertById(dt);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}

	@RequestMapping(value="/shop/{id}/dining-table/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getDining_table(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")Integer shop_id)
	{
		
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			
			responseObject.msg = dts.selectByShopId(shop_id);			
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/dining-table/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateDining_table(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")Long id,
			@RequestBody Dining_table dt)
	{
		
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			dts.updateDining_table(dt);
			HashMap<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("dining_table_id", id.toString());
			responseObject.msg=resultMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/dining-table/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject get(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")Long id)
	{
		
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {	
			
			responseObject.msg = dts.select(id);	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/dining-table/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteDining_table(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")Long id)
	{
		
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			dts.deleteDining_table(id);
			HashMap<String, Object> resultmap = new HashMap<String,Object>();
			resultmap.put("dining_table_id", id.toString());
			responseObject.msg=resultmap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}	
	

	@RequestMapping(value="/dining_table/shop/{id}/isUpload/{syncStatus}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getIsUpload(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("syncStatus")Integer sysnStatus,
			@PathVariable("id")Integer shopId)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			
			responseObject.msg=diningTableDao.selectByIsUpload(shopId);
			
		} catch (Exception e) {
			
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			
			e.printStackTrace();
		}
		return responseObject;
	}
}

