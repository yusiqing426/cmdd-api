package cn.com.cmdd.domain;



import java.util.Date;
import org.apache.ibatis.type.Alias;
import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("Staff")
public class Staff {
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long id;
	private Integer shop_id;
	private String 	account;
	private String 	name;
	private Integer sex;
	private String 	phone;
	private String 	password;
	private Integer is_in_use;
	private Date	create_time;
	private Date	last_login_time;
	
	private String 	user_key;
	private int sync_status;

	public Integer getSync_status() {
		return sync_status;
	}

	public void setSync_status(int sync_status) {
		this.sync_status = sync_status;
	}


	public String getUser_key() {
		return user_key;
	}
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getIs_in_use() {
		return is_in_use;
	}
	public void setIs_in_use(Integer is_in_use) {
		this.is_in_use = is_in_use;
	}
	@Override
	public String toString() {
		return "Staff [id=" + id + ", shop_id=" + shop_id + ", account=" + account + ", name=" + name + ", sex=" + sex
				+ ", phone=" + phone + ", password=" + password + ", is_in_use=" + is_in_use + ", create_time="
				+ create_time + ", last_login_time=" + last_login_time + ", user_key=" + user_key + "]";
	}	
	
}

















