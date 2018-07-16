package cn.com.cmdd.domain;

import java.util.Date;
import org.apache.ibatis.type.Alias;
import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("MemberRechargeLog")
public class MemberRechargeLog {
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long id;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long member_id;
	private Integer shop_id;
	private Double  amount;
	private Integer pay_type;
	private Date	create_time;	
		private Double balance;
	private String name;
	private Integer sex;
	private String phone;
	private Double now_balance;
	private Double tradeIntegral;
	private Double integral;
	private Integer category;
	private Integer type;
	private String description;
	private Double giveAmount;
	private int sync_status;

	public Integer getSync_status() {
		return sync_status;
	}

	public void setSync_status(int sync_status) {
		this.sync_status = sync_status;
	}


	public Double getNow_balance() {
		return now_balance;
	}
	public void setNow_balance(Double now_balance) {
		this.now_balance = now_balance;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
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
	public Long getMember_id() {
		return member_id;
	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getPay_type() {
		return pay_type;
	}

	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}

	public void setTradeIntegral(Double tradeIntegral) {
		this.tradeIntegral = tradeIntegral;
	}

	public Double getIntegral() {
		return integral;
	}

	public void setIntegral(Double integral) {
		this.integral = integral;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTradeIntegral() {
		return tradeIntegral;
	}
	public Double getGiveAmount() {
		return giveAmount;
	}
	public void setGiveAmount(Double giveAmount) {
		this.giveAmount = giveAmount;
	}
	
	
	
}

















