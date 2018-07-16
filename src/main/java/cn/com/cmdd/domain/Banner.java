package cn.com.cmdd.domain;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("Banner")
public class Banner {
	private Integer  id;
	private Integer agent_id;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long banner_id;
	private Integer serial;
	private String  name;
	
	private Integer is_banner;
	
	public Integer getSerial() {
		return serial;
	}
	public void setSerial(Integer serial) {
		this.serial = serial;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIs_banner() {
		return is_banner;
	}
	public void setIs_banner(Integer is_banner) {
		this.is_banner = is_banner;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(Integer agent_id) {
		this.agent_id = agent_id;
	}
	public Long getBanner_id() {
		return banner_id;
	}
	public void setBanner_id(Long banner_id) {
		this.banner_id = banner_id;
	}
	
	
	
}
