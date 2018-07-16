package cn.com.cmdd.domain;

import java.util.Date;
import org.apache.ibatis.type.Alias;
import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("MemberLotteryLog")
public class MemberLotteryLog {
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long id;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long order_id;
	private String  lottery_detail;
	private Integer shop_id;
	private Integer is_cash;
	private String 	phone;
	private Date	create_time;
	private Date	exchange_time;
	private int sync_status;

	public Integer getSync_status() {
		return sync_status;
	}

	public void setSync_status(int sync_status) {
		this.sync_status = sync_status;
	}


	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getExchange_time() {
		return exchange_time;
	}
	public void setExchange_time(Date exchange_time) {
		this.exchange_time = exchange_time;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getLottery_detail() {
		return lottery_detail;
	}
	public void setLottery_detail(String lottery_detail) {
		this.lottery_detail = lottery_detail;
	}
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public Integer getIs_cash() {
		return is_cash;
	}
	public void setIs_cash(Integer is_cash) {
		this.is_cash = is_cash;
	}
	
}

















