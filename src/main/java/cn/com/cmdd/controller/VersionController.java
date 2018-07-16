package cn.com.cmdd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.cmdd.domain.Version;
import cn.com.cmdd.service.VersionService;
import cn.com.cmdd.util.ResponseObject;
import cn.com.cmdd.util.XmlRead;
import cn.com.cmdd.util.ZipUpdate;

@Controller
public class VersionController {
	
	private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(VersionController.class);
	
	@Autowired
	private	VersionService versionService;
			
	@RequestMapping(value="/updateVersion",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject get(HttpServletRequest request,HttpServletResponse response,@RequestParam("id")String newVer){ 
		
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/
		String saveDir="C:\\Users\\Administrator\\Desktop\\cmdd\\apache-tomcat-7.0.88\\webapps";
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try {
			
			ZipUpdate zipUpdate=new ZipUpdate();

			if(zipUpdate.DownAndReadFile(saveDir,"webapps.zip"))
			{	
								
				Version version = new Version();
				version.setId(1);
				version.setVersion(newVer);
				versionService.update(version);
				
			}
			else
			{
				
				responseObject.code=ResponseObject.serverError;
				responseObject.msg="更新失败";
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
	@RequestMapping(value="/checkUpdate",method=RequestMethod.GET)
	@ResponseBody
	public ResponseObject checkUpdate(HttpServletRequest request,HttpServletResponse response){
		
		/*if(!AuthCheck.UserCheck(request, response, KEYS.SHOP)){
			return null;
		}*/
		
		
		ResponseObject responseObject = new ResponseObject(ResponseObject.ok,null);
		
		try{
			
			XmlRead xmlRead=new XmlRead();
			String remoteVer=xmlRead.Read();
			String localVer = versionService.get().getVersion();

			String newVer =  localVer.equals(remoteVer)?"":remoteVer;
			
			responseObject.msg = newVer;
			
			
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			responseObject.code=ResponseObject.serverError;
			responseObject.msg=e.getLocalizedMessage();
			e.printStackTrace();
		}
		return responseObject;
	}
	
							
}
