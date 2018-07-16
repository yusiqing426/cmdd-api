package cn.com.cmdd.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.com.cmdd.dao.UserDao;
import cn.com.cmdd.domain.User;
import cn.com.cmdd.service.UserService;
import cn.com.cmdd.util.ResponseObject;
import cn.com.cmdd.util.SessionHelper;
import cn.com.cmdd.util.SessionItem;
import cn.com.cmdd.util.SessionStore;

@Controller//@CrossOrigin(origins = "*")
public class UserController {
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;

	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject login(@RequestBody User user){
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try{

			Map<String,String> resultMap =  userService.login(user.getAccount(),user.getPassword());

			SessionItem sessionItem = new SessionItem(resultMap.get("user_id"),resultMap.get("user_keys"));
			String token = SessionHelper.getUUID();
			SessionStore.Add(token, sessionItem);
			
			resultMap.put("token", token);
			responseObject.msg = resultMap;
		}catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/user/logout",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject logout(
			@RequestHeader(value="X-Auth-Token",required=false)String sessionId)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			responseObject.msg = "success";
			
			if(sessionId != null){
				SessionStore.Delete(sessionId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.msg = e.getLocalizedMessage();
		}
		return responseObject;
	}

	@RequestMapping(value="/user/shop/{id}/isUpload/{sync_status}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getIsUpload(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("sync_status")int sync_status,
			@PathVariable("id")Integer shop_id)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			responseObject.msg=userDao.selectByIsUpload(shop_id,sync_status);

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

			e.printStackTrace();
		}
		return responseObject;
	}

	@RequestMapping(value="/user/id",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject insertByid(@RequestBody User user)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			userDao.insertById(user);
		} catch (Exception e) {
			responseObject.code= ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();

		}
		return responseObject;
	}

	@RequestMapping(value="/user/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject update(
			@PathVariable("id")Long id,
			@RequestBody User user
	)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);

		try {

			userDao.update(user);

		} catch (Exception e) {

			responseObject.code= ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();

		}
		return responseObject;
	}
	
}
