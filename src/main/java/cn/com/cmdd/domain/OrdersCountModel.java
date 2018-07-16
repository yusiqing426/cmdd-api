package cn.com.cmdd.domain;


import java.util.List;

import org.apache.ibatis.type.Alias;
@Alias("OrdersCountModel")
public class OrdersCountModel {
    //数据条数
	private Integer totalNum;
	//现金
	private Double sumTradeCash;
	//银行卡
	private Double sumTradeCreditCard;
	//微信
	private Double sumTradeWechat;
	//支付宝
	private Double sumTradeAlipay;
    //抹零总计
    private Double sumResidue;
    //会员充值收入 sumMemberRecharge
    private Double sumMemberRecharge;
    //会员卡充值赠送
    private Double sumMemberGiveMoney;
    //赠送
    private Double sumLottery;
	//实付
	//实收 : 4种支付方式+会员充值 totalSale sumReceived
	private Double sumReceived;
	//应收金额 : 应收+免单+挂账 sumTotal_free sumReceivable
	private Double sumReceivable;
	//会员消费
	private Double sumMemberConsume;
	//免单
	private Double sumFreeOrder;
	//取消订单
	private Double sumCancelOrder;
	//组合付款
	private Double sumCombinationPayment;
	//挂账
	private Double sumBill;
		//折扣优惠金额
	private Double sumDiscountPreferential;

	List<Orders> orderList;

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Double getSumTradeCash() {
		return sumTradeCash;
	}

	public void setSumTradeCash(Double sumTradeCash) {
		this.sumTradeCash = sumTradeCash;
	}

	public Double getSumTradeCreditCard() {
		return sumTradeCreditCard;
	}

	public void setSumTradeCreditCard(Double sumTradeCreditCard) {
		this.sumTradeCreditCard = sumTradeCreditCard;
	}

	public Double getSumTradeWechat() {
		return sumTradeWechat;
	}

	public void setSumTradeWechat(Double sumTradeWechat) {
		this.sumTradeWechat = sumTradeWechat;
	}

	public Double getSumTradeAlipay() {
		return sumTradeAlipay;
	}

	public void setSumTradeAlipay(Double sumTradeAlipay) {
		this.sumTradeAlipay = sumTradeAlipay;
	}

	public Double getSumResidue() {
		return sumResidue;
	}

	public void setSumResidue(Double sumResidue) {
		this.sumResidue = sumResidue;
	}

	public Double getSumMemberRecharge() {
		return sumMemberRecharge;
	}

	public void setSumMemberRecharge(Double sumMemberRecharge) {
		this.sumMemberRecharge = sumMemberRecharge;
	}

	public Double getSumMemberGiveMoney() {
		return sumMemberGiveMoney;
	}

	public void setSumMemberGiveMoney(Double sumMemberGiveMoney) {
		this.sumMemberGiveMoney = sumMemberGiveMoney;
	}

	public Double getSumLottery() {
		return sumLottery;
	}

	public void setSumLottery(Double sumLottery) {
		this.sumLottery = sumLottery;
	}

	public Double getSumReceived() {
		return sumReceived;
	}

	public void setSumReceived(Double sumReceived) {
		this.sumReceived = sumReceived;
	}

	public Double getSumReceivable() {
		return sumReceivable;
	}

	public void setSumReceivable(Double sumReceivable) {
		this.sumReceivable = sumReceivable;
	}

	public Double getSumMemberConsume() {
		return sumMemberConsume;
	}

	public void setSumMemberConsume(Double sumMemberConsume) {
		this.sumMemberConsume = sumMemberConsume;
	}

	public Double getSumFreeOrder() {
		return sumFreeOrder;
	}

	public void setSumFreeOrder(Double sumFreeOrder) {
		this.sumFreeOrder = sumFreeOrder;
	}

	public Double getSumCancelOrder() {
		return sumCancelOrder;
	}

	public void setSumCancelOrder(Double sumCancelOrder) {
		this.sumCancelOrder = sumCancelOrder;
	}

	public Double getSumCombinationPayment() {
		return sumCombinationPayment;
	}

	public void setSumCombinationPayment(Double sumCombinationPayment) {
		this.sumCombinationPayment = sumCombinationPayment;
	}

	public Double getSumBill() {
		return sumBill;
	}

	public void setSumBill(Double sumBill) {
		this.sumBill = sumBill;
	}
	
	
	public Double getSumDiscountPreferential() {
		return sumDiscountPreferential;
	}

	public void setSumDiscountPreferential(Double sumDiscountPreferential) {
		this.sumDiscountPreferential = sumDiscountPreferential;
	}

	public List<Orders> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}

    
    
    
}
