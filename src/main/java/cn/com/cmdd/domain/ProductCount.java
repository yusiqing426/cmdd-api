package cn.com.cmdd.domain;



import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
@Alias("ProductCount")
public class ProductCount {
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long id;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long logo_id;
	private String name;
	private String category_name ;
	private double sumCount;
	private int is_promotion;
	private double unit_price;
	private double promotion_price;
	private double quantity;	
	private double sumIsLottery;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLogo_id() {
		return logo_id;
	}

	public void setLogo_id(Long logo_id) {
		this.logo_id = logo_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public double getSumCount() {
		return sumCount;
	}

	public void setSumCount(double sumCount) {
		this.sumCount = sumCount;
	}

	public int getIs_promotion() {
		return is_promotion;
	}

	public void setIs_promotion(int is_promotion) {
		this.is_promotion = is_promotion;	
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public double getPromotion_price() {
		return promotion_price;
	}

	public void setPromotion_price(double promotion_price) {
		this.promotion_price = promotion_price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getSumIsLottery() {
		return sumIsLottery;
	}

	public void setSumIsLottery(double sumIsLottery) {
		this.sumIsLottery = sumIsLottery;
	}

	@Override
	public String toString() {
		return "ProductCount [id=" + id + ", logo_id=" + logo_id + ", name=" + name + ", category_name=" + category_name
				+ ", sumCount=" + sumCount + ", is_promotion=" + is_promotion + ", unit_price=" + unit_price
				+ ", promotion_price=" + promotion_price + ", quantity=" + quantity + ", sumIsLottery=" + sumIsLottery
				+ "]";
	}
		
}
