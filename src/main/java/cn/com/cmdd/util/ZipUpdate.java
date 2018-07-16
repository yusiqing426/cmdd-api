package cn.com.cmdd.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import cn.com.cmdd.service.FileService;



public class ZipUpdate {
	
	public Boolean DownAndReadFile(String saveDir,String ZipName)
	{
		//String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		//String dir = SaveDir +"/"+ date;
		//File savePath = new File(dir);//创建新文件
		File savePath = new File(saveDir);
		if (!savePath.exists())
		{
		   savePath.mkdir();
		}
		try
		{
			File file = new File(savePath+"/"+ZipName);//创建新文件
			if(file!=null && !file.exists())
			{
			  file.createNewFile();
			}
			OutputStream oputstream = new FileOutputStream(file);
			/*URL url = new URL(DownUrl+"/"+ZipName);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setDoInput(true);//设置是否要从 URL 连接读取数据,默认为true
			uc.connect();
			InputStream iputstream = uc.getInputStream();*/
			InputStream iputstream = new FileService().get(ZipName);
			//System.out.println("file size is:"+uc.getContentLength());//打印文件长度
			
			byte[] buffer = new byte[4*1024];
			int byteRead = -1;
			while((byteRead=(iputstream.read(buffer)))!= -1)
			{
			  oputstream.write(buffer, 0, byteRead);
			}
			oputstream.flush();
			iputstream.close();
			oputstream.close();
			
			//byte[] fileBuffer  =  new FileService().get(ZipName);
			
			//读取文件
			StringBuffer strb = new StringBuffer();
			FileInputStream fs = new FileInputStream(new File(savePath+"//"+ZipName));
			InputStreamReader isr = new InputStreamReader(fs,"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String data = "";
			while((data = br.readLine()) != null)
			{
			  strb.append(data + "\n");
			}
			br.close();
			fs.close();
			isr.close();
			
			//解压
			UnZipFiles(saveDir + "/" + ZipName,saveDir);
			
			//删除文件
			File Zipfile = new File(saveDir+"/"+ZipName);
			Zipfile.delete();
			File Datefile = new File(saveDir);
			Datefile.delete();
			
			return true;
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	public static void UnZipFiles(String ZipFile, String saveDir) throws IOException 
	{ 
		ZipFile zip = new ZipFile(new File(ZipFile),Charset.forName("GBK"));
		//解决中文文件夹乱码 
//		String name = zip.getName().substring(zip.getName().lastIndexOf('\\')+1, zip.getName().lastIndexOf('.')); 
//		File pathFile = new File(DescFile+ "/"+name); 
//		if (!pathFile.exists())
//		{
//			pathFile.mkdirs(); 
//		} 
		for (Enumeration<? extends ZipEntry> entries = zip.entries(); 
				entries.hasMoreElements();
		) 
		{ 
			ZipEntry entry = (ZipEntry) entries.nextElement(); 
			String zipEntryName = entry.getName();
			
			String zipEntryPath = saveDir+ "/"+ zipEntryName;
						
			String outPath = zipEntryPath.replaceAll("\\*", "/"); 
			InputStream in = zip.getInputStream(entry); 
			// 判断路径是否存在,不存在则创建文件路径 
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/'))); 
			if (!file.exists()) {
				file.mkdirs(); 
			} 
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压 
			if (new File(outPath).isDirectory())
			{ continue; } 
			// 输出文件路径信息
			// System.out.println(outPath); 
			FileOutputStream out = new FileOutputStream(outPath); 
			byte[] buf1 = new byte[1024]; 
			int len; 
			while ((len = in.read(buf1)) > 0) 
			{ 
				out.write(buf1, 0, len); 
			}
			in.close();
			out.close(); 
		} 

		zip.close();
	}
}
