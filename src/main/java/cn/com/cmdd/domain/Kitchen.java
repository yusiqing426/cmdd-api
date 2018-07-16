package cn.com.cmdd.domain;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("Kitchen")
public class Kitchen {
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long id;
	private Integer shop_id;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long logo_id;	
	private String 	description;
	private String 	title;
	private int sync_status;
	private Integer is_enable;

	public Integer getIs_enable() {
		return is_enable;
	}

	public void setIs_enable(Integer is_enable) {
		this.is_enable = is_enable;
	}

	public int getSync_status() {
		return sync_status;
	}

	public void setSync_status(int sync_status) {
		this.sync_status = sync_status;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public Long getLogo_id() {
		return logo_id;
	}
	public void setLogo_id(Long logo_id) {
		this.logo_id = logo_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
		
}

















