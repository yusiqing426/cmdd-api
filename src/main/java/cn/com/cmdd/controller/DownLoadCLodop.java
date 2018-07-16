package cn.com.cmdd.controller;


import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller//@CrossOrigin(origins = "*")
public class DownLoadCLodop {

	@RequestMapping(value="/downloadLodop")
    public ResponseEntity<byte[]> download(
    										HttpServletRequest request
    										                            )throws Exception {
    	//下载文件路径
        String projectName = request.getContextPath().substring(1);
        String path = request.getSession().getServletContext().getRealPath("/").replaceAll(projectName, "");
        File file = new File(path+"Lodop/CLodop_Setup_for_Win32NT.exe");
       
        //System.out.println("--------------------------->"+file.getPath());
        HttpHeaders headers = new HttpHeaders();  
        //下载显示的文件名，解决中文名称乱码问题  
        //String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）
        headers.setContentDispositionFormData("attachment","install_lodop.exe"); 
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);  
    }
}
