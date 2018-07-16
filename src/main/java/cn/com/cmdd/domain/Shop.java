package cn.com.cmdd.domain;


import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("Shop")
public class Shop {
	private Integer id;
	private Integer agent_id;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long pay_code_id;
	private String 	name;
	private String 	account;
	private String 	id_card;
	private Integer sex;
	private String 	phone;
	private String 	shop_name;
	private String 	shop_address;
	private String 	password;
	private Integer payable_date;
	private Integer is_in_use;
	private Integer is_unified_print;
	private Integer is_banner;
	private Date	create_time;
	private Date	last_login_time;
	private String contactPerson;
	
	private Double service_charge;
	private Integer is_use_service;
	private int sync_status;
	private Integer is_remind;

	public Integer getIs_remind() {
		return is_remind;
	}

	public void setIs_remind(Integer is_remind) {
		this.is_remind = is_remind;
	}

	public Integer getSync_status() {
		return sync_status;
	}

	public void setSync_status(int sync_status) {
		this.sync_status = sync_status;
	}



	public Double getService_charge() {
		return service_charge;
	}
	public void setService_charge(Double service_charge) {
		this.service_charge = service_charge;
	}
	public Integer getIs_use_service() {
		return is_use_service;
	}
	public void setIs_use_service(Integer is_use_service) {
		this.is_use_service = is_use_service;
	}
	
	
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	private String 	user_key;
	
	
	public Integer getIs_unified_print() {
		return is_unified_print;
	}
	public void setIs_unified_print(Integer is_unified_print) {
		this.is_unified_print = is_unified_print;
	}
	public Integer getPayable_date() {
		return payable_date;
	}
	public void setPayable_date(Integer payable_date) {
		this.payable_date = payable_date;
	}
	public String getUser_key() {
		return user_key;
	}
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}	
	
	public Long getPay_code_id() {
		return pay_code_id;
	}
	public void setPay_code_id(Long pay_code_id) {
		this.pay_code_id = pay_code_id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShop_address() {
		return shop_address;
	}
	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}
	public Integer getIs_in_use() {
		return is_in_use;
	}
	public void setIs_in_use(Integer is_in_use) {
		this.is_in_use = is_in_use;
	}
	public Integer getIs_banner() {
		return is_banner;
	}
	public void setIs_banner(Integer is_banner) {
		this.is_banner = is_banner;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	
	
	
}
