package cn.com.cmdd.domain;




import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ibatis.type.Alias;


@Alias("User")
public class User {
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long user_id;
	private String  account;
	private String 	password;
	private String 	user_key;
	private Integer is_in_use;
	private int sync_status;

	public Integer getSync_status() {
		return sync_status;
	}

	public void setSync_status(int sync_status) {
		this.sync_status = sync_status;
	}


	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_key() {
		return user_key;
	}

	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}

	public Integer getIs_in_use() {
		return is_in_use;
	}

	public void setIs_in_use(Integer is_in_use) {
		this.is_in_use = is_in_use;
	}

	
	
	
	
	
	
	
	/*@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
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
	}*/
	
	
}
