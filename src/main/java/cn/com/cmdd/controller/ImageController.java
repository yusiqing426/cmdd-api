package cn.com.cmdd.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.cmdd.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.mail.handlers.image_gif;

import cn.com.cmdd.dao.ImageDao;
import cn.com.cmdd.service.ImageService;
import cn.com.cmdd.util.ResponseObject;

@Controller//@CrossOrigin(origins = "*")
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ImageDao imageDao;
	
	@RequestMapping(value="/image/{id}",method=RequestMethod.GET)
	public void getImage(
			HttpServletResponse response, 
			@PathVariable("id") Long id)
	{
		try{
			byte[] buf = imageService.getImage(id);
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("image/png");
			response.setContentLength(buf.length);
			OutputStream out = response.getOutputStream();
			try{
				FileCopyUtils.copy(buf, out);
				response.flushBuffer();
			}catch (Exception e) {
				return;
			}finally{
				out.close();
			}
		}
		catch (Exception e) {
			return;
		}
	}
	
	@RequestMapping(value="/image/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteImage(HttpServletRequest request,
									  HttpServletResponse response,
									  @PathVariable("id")Long id)
	{
		/*if(!AuthCheck.UserCheck(request, response, KEYS.AGENT)){
			if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){	
				return null;
			}
		}*/
		
		ResponseObject responseObject  = new ResponseObject(ResponseObject.ok,null);
		try{
			Long image_id = imageService.DeleteImage(id);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("image_id", image_id.toString());
			responseObject.msg = resultMap;
		}catch(Exception e){
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/shop/{id}/image",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject addImage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="id" ,required =false)Long id, 
			@RequestParam(value = "file", required = false) MultipartFile file,
			@PathVariable("id")Integer shop_id) throws IOException{
		
		
		ResponseObject responseObject  = new ResponseObject(ResponseObject.ok,null);
		try{
			
			Long image_id = imageService.addImage(id, file,shop_id);
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("image_id", image_id.toString());
			responseObject.msg = resultMap;
		}catch(Exception e){
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	


	@RequestMapping(value="/image/shop/{id}/sync_status",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject sysn(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("id")Integer shopId)
	{
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);

		try {

			responseObject.msg=imageService.getSysn(shopId);

		} catch (Exception e) {

			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();

			e.printStackTrace();
		}
		return responseObject;
	}


	@RequestMapping(value="/image/id",method=RequestMethod.POST)
	@ResponseBody
	public ResponseObject save(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Image image)
			throws IOException
	{
		ResponseObject responseObject  = new ResponseObject(ResponseObject.ok,null);
		try{

			Long image_id = imageService.insertById(image);
			
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("image_id", image_id.toString());
			responseObject.msg = resultMap;
			
		}catch(Exception e){
			
			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}


	@RequestMapping(value="/image/{id}/sync_status",method=RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateSync_status(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Image image,
			@PathVariable("id")Long image_id
	)
			throws IOException
	{
		ResponseObject responseObject  = new ResponseObject(ResponseObject.ok,null);
		try{

			imageDao.updateSync_status(image_id);

			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("image_id", image_id.toString());
			responseObject.msg = resultMap;

		}catch(Exception e){

			responseObject.code = ResponseObject.serverError;
			responseObject.msg = e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
}

	

