package cn.com.cmdd.domain;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Alias("Image")
public class Image {
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long id;
	private String origin_name;
	private Long file_size;
	private int sync_status;

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	private Date create_time;
	private Integer shopId;
	
	
	public Integer getSync_status() {
		return sync_status;
	}

	public void setSync_status(int sync_status) {
		this.sync_status = sync_status;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrigin_name() {
		return origin_name;
	}
	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}
	public Long getFile_size() {
		return file_size;
	}
	public void setFile_size(Long file_size) {
		this.file_size = file_size;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

}
