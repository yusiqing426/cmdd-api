package cn.com.cmdd.domain;

public class Password {
	
	private Integer id;
	private String old_password;
	private String new_password;
	private int sync_status;

	public Integer getSync_status() {
		return sync_status;
	}

	public void setSync_status(int sync_status) {
		this.sync_status = sync_status;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOld_password() {
		return old_password;
	}
	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}
	public String getNew_password() {
		return new_password;
	}
	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}
	
}