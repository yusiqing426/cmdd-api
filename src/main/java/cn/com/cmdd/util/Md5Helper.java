
package cn.com.cmdd.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class Md5Helper {
	
	public static String MD5Encode(String pwd)
	{
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String pwdencry = encoder.encodePassword(pwd,null);
		return pwdencry;
	}
	
}
