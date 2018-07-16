package cn.com.cmdd.controller;

import java.util.HashMap;
import java.util.Map;

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

import cn.com.cmdd.dao.StaffDao;
import cn.com.cmdd.domain.Password;
import cn.com.cmdd.domain.Staff;
import cn.com.cmdd.service.StaffService;
import cn.com.cmdd.util.ResponseObject;

@Controller//@CrossOrigin(origins = "*")
public class StaffController {
	private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(StaffController.class);

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private StaffDao staffDao;



	@RequestMapping(value="/staff",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject addStaff(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Staff staff)
	{
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try{
			staffService.addStaff(staff);
			
		}catch(Exception e){
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}

	@RequestMapping(value="/staff/id",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject insertById(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Staff staff)
	{
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try{
			staffDao.insertById(staff);

		}catch(Exception e){
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}

	
	@RequestMapping(value="/staff/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getStaff(
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable("id")Long id){
		/*if(!AuthCheck.UserCheck(request, response, KEYS.STAFF)){
			return null;
		}*/
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			responseObject.msg = staffService.getStaff(id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/shop/{id}/staff/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getStaffList(
			@PathVariable("id")Integer shop_id
	)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			responseObject.msg = staffService.getStaffListByShop_id(shop_id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/staff/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateStaff(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id") Long id,
			@RequestBody Staff staff
	)
	{
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			staff.setId(id);
			staffService.updateStaff(staff);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("staff_id", id.toString());
			responseObject.msg = resultMap;
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/staff/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteStaff(
			HttpServletRequest request, 
			HttpServletResponse response, 
			@PathVariable("id")Long id
	)
	{
		/*if(!AuthCheck.UserCheck(request, response,KEYS.SHOP)){
			return null;
		}*/
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			staffService.deleteStaff(id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/staff/{id}/password",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateStaffPassword(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Password passwordFrom,
            @PathVariable("id")Long id
	)
	{
		/*if(!AuthCheck.UserCheck(request, response,KEYS.SHOP)){
			return null;
		}*/
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		
		try{
			staffService.updateStaffPassword(id,passwordFrom.getOld_password(),passwordFrom.getNew_password());
			
		}catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/shop/{id}/password",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateShopPassword(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Password passwordFrom,
			@PathVariable("id")int id
	)
	{
		/*if(!AuthCheck.UserCheck(request, response,KEYS.SHOP)){
			return null;
		}*/
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			
			int sid = staffService.updateShopPassword(id,passwordFrom.getOld_password(),passwordFrom.getNew_password());
			map.put("user_id", sid);
			responseObject.msg = map;
		}catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
		}
		return responseObject;
	}
	


	@RequestMapping(value="/staff/shop/{id}/isUpload/{sync_status}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getIsUpload(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("sync_status")int sync_status,
			@PathVariable("id")Integer shop_id
	)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			responseObject.msg=staffDao.selectByIsUpload(shop_id,sync_status);

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

			e.printStackTrace();
		}
		return responseObject;
	}
	
}
