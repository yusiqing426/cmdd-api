
package cn.com.cmdd.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public final class LevelDBAPI
{
	public static String url = "http://127.0.0.1:6789";
    public static byte[] get(Long id){
    	String path = url+"/get?id="+id.toString();
    	@SuppressWarnings("resource")
		HttpClient httpClient = new DefaultHttpClient();  
        HttpGet methodGet = new HttpGet(path);
        byte[] returnBytes = null;
        try {  
            HttpResponse response = httpClient.execute(methodGet);  
            if (response.getStatusLine().getStatusCode() == 200) {  
                HttpEntity entity = response.getEntity();  
                returnBytes = EntityUtils.toByteArray(entity);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally{
        	methodGet.releaseConnection();
        }
        return returnBytes;  
    }
    
    public static Long delete(Long id){
    	String path = url+"/delete?id="+id.toString();
    	@SuppressWarnings("resource")
		HttpClient httpClient = new DefaultHttpClient();  
        HttpDelete methodDelete = new HttpDelete(path);  
        Long retId = 0l;
        try {  
            HttpResponse response = httpClient.execute(methodDelete);  
            if (response.getStatusLine().getStatusCode() == 200) {  
                retId = id;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  finally{
        	methodDelete.releaseConnection();
        }
    	
		return retId;
    }
    
    public static Long post(Long id, byte[] data){
    	String path = url+"/put?id="+id.toString();
    	@SuppressWarnings("resource")
		HttpClient httpClient = new DefaultHttpClient();  
        HttpPost methodPost = new HttpPost(path);  
        Long retId = 0L;
        methodPost.setEntity((HttpEntity) new ByteArrayEntity(data)); 
        try {  
        	
            HttpResponse response = httpClient.execute(methodPost);  
            if (response.getStatusLine().getStatusCode() == 200) {  
            	retId = id;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  finally{
        	methodPost.releaseConnection();
        }
    	
		return retId;
    }
    
}
