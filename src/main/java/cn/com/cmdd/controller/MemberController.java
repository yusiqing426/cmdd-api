package cn.com.cmdd.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.LongField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.com.cmdd.dao.MemberDao;
import cn.com.cmdd.domain.Member;
import cn.com.cmdd.domain.Password;
import cn.com.cmdd.service.MemberService;
import cn.com.cmdd.util.ResponseObject;

@Controller//@CrossOrigin(origins = "*")
public class MemberController {
	private final static Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;
	

	@Autowired
	private MemberDao memberDao;


	@RequestMapping(value="/member/shop/{id}/isUpload/{sync_status}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getIsUpload(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("sync_status")int sync_status,
			@PathVariable("id")Integer shop_id)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			responseObject.msg=memberDao.selectByIsUpload(shop_id,sync_status);

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

			e.printStackTrace();
		}
		return responseObject;
	}
	@RequestMapping(value="/member",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject addMember(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Member member)
	{
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try{

			memberService.addMember(member);
			
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("member_id", member.getId().toString());
			responseObject.msg = resultMap;
		}catch(Exception e){
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}

	@RequestMapping(value="/member/id",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject insertById(@RequestBody Member member)
	{

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);

		try{

			memberDao.insertById(member);

		}catch(Exception e){

			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}

	
	@RequestMapping(value="/member/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getMember(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")Long id)
	{
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			responseObject.msg = memberService.getMember(id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/shop/{id}/member/list",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject getMemberList(@PathVariable("id")Integer shop_id){
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			responseObject.msg = memberService.getMemberListByShop_id(shop_id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/member/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateMember(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")Long id,
			 @RequestBody Member member)
	{
	
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		try {
			member.setId(id);
			memberService.updateMember(member);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("member_id", id.toString());
			responseObject.msg = resultMap;
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/member/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteMember(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id") Long id)
	{
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		try {
			memberService.deleteMember(id);
		} catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	
	@RequestMapping(value="/member/{id}/password",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateMemberPassword(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Password passwordFrom,
			@PathVariable("id")Long id)
	{

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			Long mid = memberService.updateMemberPassword(id,passwordFrom.getNew_password());
			map.put("member_id", mid.toString());
			responseObject.msg = map;
		}catch (Exception e) {
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
		}
		return responseObject;
	}

	@RequestMapping(value="/member/shop/{id}/birth",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject birth(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")Integer shop_id)
	{

		ResponseObject responseObject = new ResponseObject(ResponseObject.ok, null);

		Map<String,Object> map = new HashMap<String,Object>();

		try{

			responseObject.msg = memberService.birth(shop_id);

		}catch (Exception e) {

			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();

		}
		return responseObject;
	}
	
}
