    package cn.com.cmdd.service;

    import java.io.ByteArrayInputStream;
    import java.io.ByteArrayOutputStream;
    import java.io.InputStream;
    import java.net.URL;
    import java.util.Date;

    import org.springframework.stereotype.Service;
    import org.springframework.web.multipart.MultipartFile;

    import com.aliyun.oss.HttpMethod;
    import com.aliyun.oss.OSSClient;
    import com.aliyun.oss.model.GeneratePresignedUrlRequest;
    import com.aliyun.oss.model.OSSObject;
    import com.aliyun.oss.model.ObjectMetadata;
    @Service
    public class FileService
    {

        public  InputStream get(String key){
            //String key = id.toString();
            InputStream objectContent =null;
            //byte[] data = {};
            try
            {
                OSSClient ossClient = new OSSClient("oss-cn-beijing.aliyuncs.com", "LTAIcEOlTpzf5WpS", "IFiq9Hc1fp2Ak3dSGrq4a0PeFrFtTc");
                        /*TODO*/
                Date expiration = new Date(258888888 * 2000) ;
                GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest("cmdd-update", key, HttpMethod.GET);
                req.setExpiration(expiration);
                OSSObject object = ossClient.getObject("cmdd-update", key);
                // 获取ObjectMeta
                //ObjectMetadata meta = object.getObjectMetadata();

            // 获取Object的输入流
            objectContent = object.getObjectContent();
        
           /*s*/

            // 关闭流
            //objectContent.close();
            

            //ossClient.shutdown();

            return objectContent;

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return objectContent;
    }
    
    public String save(Long id,MultipartFile file)
    {
        //Date date = new Date();
        //String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(date) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
        try
        {
            ByteArrayInputStream io = new ByteArrayInputStream(file.getBytes());
            OSSClient ossClient = new OSSClient("oss-cn-beijing.aliyuncs.com", "LTAIcEOlTpzf5WpS", "IFiq9Hc1fp2Ak3dSGrq4a0PeFrFtTc");
            Date expiration = new Date(new Date().getTime() + 315360000000l) ;
            GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest("cmdd-update",id.toString(),HttpMethod.GET);
            req.setExpiration(expiration);

            ossClient.putObject("cmdd-update",id.toString(),io);
            URL signedUrl = ossClient.generatePresignedUrl(req);
            ossClient.shutdown();

            String url = signedUrl.toString();

            url =url.substring(0, url.indexOf(signedUrl.getQuery())-1);
            System.out.println(url);
            return url;

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return "";
    }
    
    public String saveByte(Long id,byte[] bs)
    {
        //Date date = new Date();
        //String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(date) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
        try
        {
            ByteArrayInputStream io = new ByteArrayInputStream(bs);
            OSSClient ossClient = new OSSClient("oss-cn-beijing.aliyuncs.com", "LTAIcEOlTpzf5WpS", "IFiq9Hc1fp2Ak3dSGrq4a0PeFrFtTc");
            Date expiration = new Date(new Date().getTime() + 315360000000l) ;
            GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest("cmdd-update",id.toString(),HttpMethod.GET);
            req.setExpiration(expiration);

            ossClient.putObject("cmdd-update",id.toString(),io);
            URL signedUrl = ossClient.generatePresignedUrl(req);
            ossClient.shutdown();

            String url = signedUrl.toString();

            url =url.substring(0, url.indexOf(signedUrl.getQuery())-1);
            System.out.println(url);
            return url;

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return "";
    }
    
    public  byte[] get(Long id){
        String key = id.toString();
        try
        {
        	 OSSClient ossClient = new OSSClient("oss-cn-beijing.aliyuncs.com", "LTAIcEOlTpzf5WpS", "IFiq9Hc1fp2Ak3dSGrq4a0PeFrFtTc");
            Date expiration = new Date(new Date().getTime() + 315360000000l * 2000) ;
            GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest("cmdd-update",id.toString(),HttpMethod.GET);            
            req.setExpiration(expiration);
            OSSObject object = ossClient.getObject("cmdd-update", key);
            // 获取ObjectMeta
            ObjectMetadata meta = object.getObjectMetadata();

            // 获取Object的输入流
            InputStream objectContent = object.getObjectContent();

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();

            int length = objectContent.available();

            byte[] data = new byte[length];

            int count = -1;

            while ((count = objectContent.read(data, 0, length)) != -1)

                outStream.write(data, 0, count);

            data = null;

            // 关闭流
            objectContent.close();

            ossClient.shutdown();

            return outStream.toByteArray();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return new byte[]{};
    }

}
