package cn.com.cmdd.util;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.xml.sax.InputSource;
import org.dom4j.*;   
import org.dom4j.io.*;  
import cn.com.cmdd.service.FileService;

public class XmlRead
{ 
	public String Read()
	{     
		try
		{            
			/*URL url=new URL(Url);
	        //以post方式请求         
			HttpURLConnection httpurlconnection =(HttpURLConnection)url.openConnection();         
			httpurlconnection.setDoOutput(true);        
			httpurlconnection.setRequestMethod("POST"); */                   
			// 获取响应代码         
			//int code   = httpurlconnection.getResponseCode();
	        //获取页面内容         
			BufferedReader in = null;        
			StringBuffer sb = new StringBuffer();        
			//in = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream())); 
			
			InputStream fileInpStream = new FileService().get("Ver.xml");			
			in = new BufferedReader(new InputStreamReader(fileInpStream));      
			String inputLine;        
			while ((inputLine = in.readLine()) != null)
			{             
				sb.append(inputLine);     
	        }   
			// 关闭流
			//fileInp.close();
            
			//System.out.println(sb.toString());                  
			StringReader read = new StringReader(sb.toString());         
			InputSource source = new InputSource(read);  
			SAXReader reader = new SAXReader();
			Document doc = reader.read(source);
			   
			
			Element root = doc.getRootElement(); 
			String ver = root.getName();
		
			return ver;
		}     
		catch(Exception e)
		{            
		 return null;    
		}    
	}
}