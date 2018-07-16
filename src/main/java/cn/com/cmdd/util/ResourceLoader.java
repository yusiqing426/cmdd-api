
package cn.com.cmdd.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ResourceLoader {
	
	public static int getSmstmplateId() throws IOException{
		Resource resource = new ClassPathResource("system.properties");
		Properties prop = PropertiesLoaderUtils.loadProperties(resource);
		int result = Integer.parseInt(prop.getProperty("smstemplateid"));
		return result;
	}
}
