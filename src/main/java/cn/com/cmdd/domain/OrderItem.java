package cn.com.cmdd.domain;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("OrderItem")
public class OrderItem {
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long id;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long order_id;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long product_id;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long category_id;
	private Integer status_pay;
	private String category_name;
	private Double quantity;
	private Integer status_id;
	private String description;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")	
	private Date create_time;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date pay_time;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date back_time;
	private Integer is_lottery;
	
			private Product p;
	
	//XXX
	private Integer shopId;
	private Double moneyPreferentialDiscount;		
			
	//+ sumField id 无实际意义
			private Double sumField;
	private int sync_status;

	public Integer getSync_status() {
		return sync_status;
	}

	public void setSync_status(int sync_status) {
		this.sync_status = sync_status;
	}

	public Integer getStatus_pay() {
		return status_pay;
	}

	public void setStatus_pay(Integer status_pay) {
		this.status_pay = status_pay;
	}

	public Double getSumField() {
		
		return sumField;
	}

	public void setSumField(Double sumField) {
		this.sumField = sumField;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Integer getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
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

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getBack_time() {
		return back_time;
	}

	public void setBack_time(Date back_time) {
		this.back_time = back_time;
	}

	public Integer getIs_lottery() {
		return is_lottery;
	}

	public void setIs_lottery(Integer is_lottery) {
		this.is_lottery = is_lottery;
	}

	public Product getP() {
		return p;
	}

	public void setP(Product p) {
		this.p = p;
		//FIXME
		if(p!=null){
			this.category_name=p.getCategory_name();
		}
	}

	public Double getMoneyPreferentialDiscount() {
		return moneyPreferentialDiscount;
	}

	public void setMoneyPreferentialDiscount(Double moneyPreferentialDiscount) {
		this.moneyPreferentialDiscount = moneyPreferentialDiscount;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	
	
	
}
