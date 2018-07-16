package cn.com.cmdd.domain;

import java.util.Date;
import org.apache.ibatis.type.Alias;
import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("Agent")
public class Agent {
	private Integer id;
	private String  name;
	private String 	company_name;
	private Integer sex;
	private String 	account;
	private String 	phone;
	private String 	id_card;
	private String 	company_address;
	private String 	password;
	private Integer is_in_use;
	private Double  payable_expenses;
	private Integer payable_date;
	private Date	create_time;
	private Date	last_login_time;
	
	private Double	service_charge;	
	
	private String 	user_key;
		
	public Double getService_charge() {
		return service_charge;
	}
	public void setService_charge(Double service_charge) {
		this.service_charge = service_charge;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUser_key() {
		return user_key;
	}
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public Double getPayable_expenses() {
		return payable_expenses;
	}
	public void setPayable_expenses(Double payable_expenses) {
		this.payable_expenses = payable_expenses;
	}
	
	public Integer getPayable_date() {
		return payable_date;
	}
	public void setPayable_date(Integer payable_date) {
		this.payable_date = payable_date;
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
	
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getIs_in_use() {
		return is_in_use;
	}
	public void setIs_in_use(Integer is_in_use) {
		this.is_in_use = is_in_use;
	}
	
	
	
}

















