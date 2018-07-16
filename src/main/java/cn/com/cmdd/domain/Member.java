package cn.com.cmdd.domain;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("Member")
public class Member {
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long id;
	private Integer shop_id;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long memberCardId;
    	private String memberCardName;
		private Double 	memberCardDiscount;
	private String 	phone;
	private String  name;
	private Integer sex;
	private String 	birthday;
	private Integer is_in_use;
	private String 	pay_password;
	private Double 	balance;

	private Double integral;//20171108+可能是小数

	public Integer getSync_status() {
		return sync_status;
	}
	public void setSync_status(int sync_status) {
		this.sync_status = sync_status;
	}
	private int sync_status;
		private Integer dayToBirth;
	public Integer getDayToBirth() {
		return dayToBirth;
	}
	public void setDayToBirth(Integer dayToBirth) {
		this.dayToBirth = dayToBirth;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Integer getIs_in_use() {
		return is_in_use;
	}
	public void setIs_in_use(Integer is_in_use) {
		this.is_in_use = is_in_use;
	}
	public String getPay_password() {
		return pay_password;
	}
	public void setPay_password(String pay_password) {
		this.pay_password = pay_password;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}

	public Double getIntegral() {
		return integral;
	}

	public void setIntegral(Double integral) {
		this.integral = integral;
	}

	public Long getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(Long memberCardId) {
		this.memberCardId = memberCardId;
	}

	public String getMemberCardName() {
		return memberCardName;
	}

	public void setMemberCardName(String memberCardName) {
		this.memberCardName = memberCardName;
	}

	public Double getMemberCardDiscount() {
		return memberCardDiscount;
	}

	public void setMemberCardDiscount(Double memberCardDiscount) {
		this.memberCardDiscount = memberCardDiscount;
	}
}


