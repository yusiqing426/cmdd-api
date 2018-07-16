package cn.com.cmdd.domain;

import java.util.Date;
import org.apache.ibatis.type.Alias;
import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("AgentPay")
public class AgentPay {		//代理商缴费信息
	
	private Integer id;
	private Integer agent_id;
	private Double 	payable_expenses;	//应缴费用
	private Date 	pay_date;			//缴费日期
	private Date	create_time;		
	private Date	paid_date;			//实际缴费日期
	private Integer is_pay;
	
	private String  company_name;
	private String  name;	
	private String  account;
	
	
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPayable_expenses() {
		return payable_expenses;
	}
	public void setPayable_expenses(Double payable_expenses) {
		this.payable_expenses = payable_expenses;
	}
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getPay_date() {
		return pay_date;
	}
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getPaid_date() {
		return paid_date;
	}
	public void setPaid_date(Date paid_date) {
		this.paid_date = paid_date;
	}
	public Integer getIs_pay() {
		return is_pay;
	}
	public void setIs_pay(Integer is_pay) {
		this.is_pay = is_pay;
	}
	
	@Override
	public String toString() {
		return "AgentPay [id=" + id + ", agent_id=" + agent_id + ", company_name=" + company_name + ", name=" + name
				+ ", payable_expenses=" + payable_expenses + ", pay_date=" + pay_date + ", paid_date=" + paid_date
				+ ", is_pay=" + is_pay + "]";
	}
}
