package cn.com.cmdd.domain;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("Dining_table")
public class Dining_table {
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long id;
	private Integer shop_id;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long logo_id;
	private String serial_id;
	private String name;
	private String address;
	private String seating_number;
	private Integer is_out;
	private Integer status;
	private Integer is_enable;
	private String description;

	public int getSync_status() {
		return sync_status;
	}

	public void setSync_status(int sync_status) {
		this.sync_status = sync_status;
	}

	private int sync_status;

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

	public String getSerial_id() {
		return serial_id;
	}

	public void setSerial_id(String serial_id) {
		this.serial_id = serial_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSeating_number() {
		return seating_number;
	}

	public void setSeating_number(String seating_number) {
		this.seating_number = seating_number;
	}

	public Integer getIs_out() {
		return is_out;
	}

	public void setIs_out(Integer is_out) {
		this.is_out = is_out;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIs_enable() {
		return is_enable;
	}

	public void setIs_enable(Integer is_enable) {
		this.is_enable = is_enable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
	
