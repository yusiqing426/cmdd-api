package cn.com.cmdd.domain;

import org.apache.ibatis.type.Alias;

/**
 *商铺订单统计
 */
@Alias("OrderCount")
public class OrderCount {

	private int pay_type;
	private int payType_num;
	private double payType_money;
	private double payType_money_total;
	private double payType_residue;

	public int getPay_type() {
		return pay_type;
	}
	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}
	public int getPayType_num() {
		return payType_num;
	}
	public void setPayType_num(int payType_num) {
		this.payType_num = payType_num;
	}
	public double getPayType_money() {
		return payType_money;
	}
	public void setPayType_money(double payType_money) {
		this.payType_money = payType_money;
	}
	public double getPayType_money_total() {
		return payType_money_total;
	}
	public void setPayType_money_total(double payType_money_total) {
		this.payType_money_total = payType_money_total;
	}
	public double getPayType_residue() {
		return payType_residue;
	}
	public void setPayType_residue(double payType_residue) {
		this.payType_residue = payType_residue;
	}



}
