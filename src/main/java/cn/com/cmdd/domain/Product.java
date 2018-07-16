package cn.com.cmdd.domain;



import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("Product")
public class Product {

	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long id;

	private Integer shop_id;

	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long category_id;
		private String category_name;

	@JsonFormat(shape=JsonFormat.Shape.STRING)	
	private Long logo_id;

	private String name;
	private Double unit_price;
	private Double promotion_price;
	private String unit;
	private Integer is_auto;
	private Integer is_in_use;
	private Integer is_promotion;
	private Integer is_discount;
	private Integer isMemberDiscount;
	private Integer isMemberIntegral;
	private Integer isUseMemberPrice;
	private Double memberPrice;
	private String description;

	public int getSync_status() {
		return sync_status;
	}

	public void setSync_status(int sync_status) {
		this.sync_status = sync_status;
	}

	private int sync_status;
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

	public Double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}

	public Double getPromotion_price() {
		return promotion_price;
	}

	public void setPromotion_price(Double promotion_price) {
		this.promotion_price = promotion_price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getIs_auto() {
		return is_auto;
	}

	public void setIs_auto(Integer is_auto) {
		this.is_auto = is_auto;
	}

	public Integer getIs_in_use() {
		return is_in_use;
	}

	public void setIs_in_use(Integer is_in_use) {
		this.is_in_use = is_in_use;
	}

	public Integer getIs_promotion() {
		return is_promotion;
	}

	public void setIs_promotion(Integer is_promotion) {
		this.is_promotion = is_promotion;
	}

	public Integer getIs_discount() {
		return is_discount;
	}

	public void setIs_discount(Integer is_discount) {
		this.is_discount = is_discount;
	}

	public Integer getIsMemberDiscount() {
		return isMemberDiscount;
	}

	public void setIsMemberDiscount(Integer isMemberDiscount) {
		this.isMemberDiscount = isMemberDiscount;
	}

	public Integer getIsMemberIntegral() {
		return isMemberIntegral;
	}

	public void setIsMemberIntegral(Integer isMemberIntegral) {
		this.isMemberIntegral = isMemberIntegral;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsUseMemberPrice() {

		return isUseMemberPrice;
	}

	public void setIsUseMemberPrice(Integer isUseMemberPrice) {

		this.isUseMemberPrice = isUseMemberPrice;
	}

	public Double getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(Double memberPrice) {
		this.memberPrice = memberPrice;
	}

	
}
