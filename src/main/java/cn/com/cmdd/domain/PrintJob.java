package cn.com.cmdd.domain;



import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ibatis.type.Alias;

@Alias("PrintJob")
public class PrintJob {
	
	private Integer id;
	private Integer shop_id;
	private String order_no;
	private String ding_table_name;
	private String product_name;
	private Long category_id;
	private String category_name;
	private Double quantity;
	private String unit;
	private String description;
	private String printer_name;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long orderItem_id;
	private String table_runner;
	private Integer status_id;
	private Integer page_width;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getDing_table_name() {
		return ding_table_name;
	}
	public void setDing_table_name(String ding_table_name) {
		this.ding_table_name = ding_table_name;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrinter_name() {
		return printer_name;
	}
	public void setPrinter_name(String printer_name) {
		this.printer_name = printer_name;
	}
	public Long getOrderItem_id() {
		return orderItem_id;
	}
	public void setOrderItem_id(Long orderItem_id) {
		this.orderItem_id = orderItem_id;
	}
	public String getTable_runner() {
		return table_runner;
	}
	public void setTable_runner(String table_runner) {
		this.table_runner = table_runner;
	}
	public Integer getStatus_id() {
		return status_id;
	}
	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}
	public Integer getPage_width() {
		return page_width;
	}
	public void setPage_width(Integer page_width) {
		this.page_width = page_width;
	}	
}
