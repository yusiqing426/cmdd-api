package cn.com.cmdd.domain;

import org.apache.ibatis.type.Alias;

@Alias("Version")
public class Version {
	
	private Integer id;
	private String Version;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	
	
	
	
	
	
}

















