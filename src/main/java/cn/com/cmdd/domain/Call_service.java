package cn.com.cmdd.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("Call_service")
public class Call_service {

	private Integer id;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long order_id;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long dining_table_id;
	private String	serial_id;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Integer shop_id;
	
	private Integer service_type;
	private String description;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public Long getDining_table_id() {
		return dining_table_id;
	}
	public void setDining_table_id(Long dining_table_id) {
		this.dining_table_id = dining_table_id;
	}
	
	public String getSerial_id() {
		return serial_id;
	}
	public void setSerial_id(String serial_id) {
		this.serial_id = serial_id;
	}
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public Integer getService_type() {
		return service_type;
	}
	public void setService_type(Integer service_type) {
		this.service_type = service_type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
