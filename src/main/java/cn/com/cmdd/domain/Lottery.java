package cn.com.cmdd.domain;
import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("Lottery")
public class Lottery {
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long id;
	private Integer levels;
	private Double 	quantity;
	private Integer shop_id;
	private String 	prize;
	private int sync_status;

	public int getSync_status() {
		return sync_status;
	}

	public void setSync_status(int sync_status) {
		this.sync_status = sync_status;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getLevels() {
		return levels;
	}
	public void setLevels(Integer levels) {
		this.levels = levels;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public String getPrize() {
		return prize;
	}
	public void setPrize(String prize) {
		this.prize = prize;
	}
	
}
