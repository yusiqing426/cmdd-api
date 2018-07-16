package cn.com.cmdd.domain;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
@Alias("Orders")
public class Orders {
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long id;
	private String order_no;
	private Integer shop_id;
	private String serial_id;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long dining_table_id;
	private String table_runner;
	private Double discount;
	private Double residue;
	private Double income;
	private Double odd;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date create_time;
	private Double total_free;
	private Double real_pay;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date pay_time;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date back_time;
	private String pay_person;
	private Integer pay_type;
		private String pay_type_name;
	private Integer status_id;
		private String status_name;
	private Integer isUseMember;
	private List<OrderItem> loi;
	private Double service_charge;
	//17/11/3+
	private Long memberId;
	private String memberIdentifier;
	private String memberCardName;
	private Double memberCardDiscount;
	private Double tradeMemberIntegral;
	private Double tradeMemberMoney;
	private Double tradeCash;
	private Double tradeWechat;
	private Double tradeAlipay;
	private Double tradeCreditCard;
	private String orderPno;
	private Integer typeHypotaxis;
	private String description;

	private int sync_status;

	public Integer getSync_status() {
		return sync_status;
	}

	public void setSync_status(int sync_status) {
		this.sync_status = sync_status;
	}

	//20180308++:
	private Double discountPreferentialMoney;
	public String getOrderPno() {
		return orderPno;
	}
	public void setOrderPno(String orderPno) {
		this.orderPno = orderPno;
	}
	public Double getService_charge() {
		return service_charge;
	}
	public void setService_charge(Double service_charge) {
		this.service_charge = service_charge;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public String getSerial_id() {
		return serial_id;
	}
	public void setSerial_id(String serial_id) {
		this.serial_id = serial_id;
	}
	public Long getDining_table_id() {
		return dining_table_id;
	}
	public void setDining_table_id(Long dining_table_id) {
		this.dining_table_id = dining_table_id;
	}
	public String getTable_runner() {
		return table_runner;
	}
	public void setTable_runner(String table_runner) {
		this.table_runner = table_runner;
	}
	public void setTradeCash(Double tradeCash) {
		this.tradeCash = tradeCash;
	}
	public Double getTradeCash() {
		return tradeCash;
	}
	public Double getDiscount() {
		return discount;
	}
   
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getResidue() {
		return residue;
	}

	public void setResidue(Double residue) {
		this.residue = residue;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getOdd() {
		return odd;
	}

	public void setOdd(Double odd) {
		this.odd = odd;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Double getTotal_free() {
		return total_free;
	}
	public void setTotal_free(Double total_free) {
		this.total_free = total_free;
	}
	public Double getReal_pay() {
		return real_pay;
	}
	public void setReal_pay(Double real_pay) {
		this.real_pay = real_pay;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getPay_time() {
		return pay_time;
	}
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getBack_time() {
		return back_time;
	}
	public void setBack_time(Date back_time) {
		this.back_time = back_time;
	}
	public String getPay_person() {
		return pay_person;
	}
	public void setPay_person(String pay_person) {
		this.pay_person = pay_person;
	}
	public Integer getStatus_id() {
		return status_id;
	}			
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
		if(status_id==0){
			this.status_name = "未结算";
		}else if(status_id==1){
			this.status_name = "已结算";			
		}else if(status_id==2){
			this.status_name = "退单";
			
		}
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public Integer getPay_type() {
		return pay_type;
	}
	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
		if(pay_type!=null){
			if(pay_type.equals(111)){
				this.pay_type_name = "现金";
			}else if(pay_type==112){
				this.pay_type_name = "银行卡";
			}else if(pay_type==113){
				this.pay_type_name = "微信";
			}else if(pay_type==114){
				this.pay_type_name = "支付宝";
			}else if(pay_type==115){
				this.pay_type_name = "会员卡";
			}else if(pay_type==116){
				this.pay_type_name = "组合付款";
			}else if(pay_type==120){
				this.pay_type_name = "免单";
			}else if(pay_type==130){
				this.pay_type_name = "挂账";
			}else if(pay_type==210){
				this.pay_type_name = "退单";
				this.back_time=this.pay_time;
				this.pay_time=null;
			}
		}
	}
	public String getPay_type_name() {
		return pay_type_name;
	}
	public void setPay_type_name(String pay_type_name) {
		this.pay_type_name = pay_type_name;
	}
	public List<OrderItem> getLoi() {
		return loi;
	}
	public void setLoi(List<OrderItem> loi) {
		this.loi = loi;
	}

	public Double getMemberCardDiscount() {
		return memberCardDiscount;
	}

	public void setMemberCardDiscount(Double memberCardDiscount) {
		this.memberCardDiscount = memberCardDiscount;
	}

	public String getMemberIdentifier() {
		return memberIdentifier;
	}
	public void setMemberIdentifier(String memberIdentifier) {
		this.memberIdentifier = memberIdentifier;
	}
	public String getMemberCardName() {
		return memberCardName;
	}
	public void setMemberCardName(String memberCardName) {
		this.memberCardName = memberCardName;
	}

	public Double getTradeMemberIntegral() {
		return tradeMemberIntegral;
	}

	public void setTradeMemberIntegral(Double tradeMemberIntegral) {
		this.tradeMemberIntegral = tradeMemberIntegral;
	}

	public Double getTradeMemberMoney() {
		return tradeMemberMoney;
	}

	public void setTradeMemberMoney(Double tradeMemberMoney) {
		this.tradeMemberMoney = tradeMemberMoney;
	}

	public Double getTradeWechat() {
		return tradeWechat;
	}

	public void setTradeWechat(Double tradeWechat) {
		this.tradeWechat = tradeWechat;
	}

	public Double getTradeAlipay() {
		return tradeAlipay;
	}

	public void setTradeAlipay(Double tradeAlipay) {
		this.tradeAlipay = tradeAlipay;
	}

	public Double getTradeCreditCard() {
		return tradeCreditCard;
	}

	public void setTradeCreditCard(Double tradeCreditCard) {
		this.tradeCreditCard = tradeCreditCard;
	}

	public Integer getTypeHypotaxis() {
		return typeHypotaxis;
	}
	public void setTypeHypotaxis(Integer typeHypotaxis) {
		this.typeHypotaxis = typeHypotaxis;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Integer getIsUseMember() {
		return isUseMember;
	}
	public void setIsUseMember(Integer isUseMember) {
		this.isUseMember = isUseMember;
	}
	public Double getDiscountPreferentialMoney() {
		return discountPreferentialMoney;
	}
	public void setDiscountPreferentialMoney(Double discountPreferentialMoney) {
		this.discountPreferentialMoney = discountPreferentialMoney;
	}
	
	
	
}
