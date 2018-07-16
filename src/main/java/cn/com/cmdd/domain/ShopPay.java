package cn.com.cmdd.domain;



import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("ShopPay")
public class ShopPay {
	private Integer id;
	private Integer shop_id;
	private Double 	total_payable;
	private Date 	pay_date;
	private Integer is_pay;
	private Date	create_time;
	private Date	paid_date;
	
	private String  account;
	private String  name;
	private String  shop_name;
	private Date  end_time;
	private Date  start_time;
	private Integer payable;
	private Integer paid;
	private Integer unpaid;
	private Integer agent_id;
	
	
	public Integer getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(Integer agent_id) {
		this.agent_id = agent_id;
	}
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Integer getPayable() {
		return payable;
	}
	public void setPayable(Integer payable) {
		this.payable = payable;
	}
	public Integer getPaid() {
		return paid;
	}
	public void setPaid(Integer paid) {
		this.paid = paid;
	}
	public Integer getUnpaid() {
		return unpaid;
	}
	public void setUnpaid(Integer unpaid) {
		this.unpaid = unpaid;
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
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public Double getTotal_payable() {
		return total_payable;
	}
	public void setTotal_payable(Double total_payable) {
		this.total_payable = total_payable;
	}
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getPay_date() {
		return pay_date;
	}
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}
	public Integer getIs_pay() {
		return is_pay;
	}
	public void setIs_pay(Integer is_pay) {
		this.is_pay = is_pay;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getPaid_date() {
		return paid_date;
	}
	public void setPaid_date(Date paid_date) {
		this.paid_date = paid_date;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
