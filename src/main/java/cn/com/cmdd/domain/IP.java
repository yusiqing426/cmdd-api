package cn.com.cmdd.domain;

import org.apache.ibatis.type.Alias;

@Alias("IP")
public class IP {
	private Integer shopId;
	private String localAreaNetwork;
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getLocalAreaNetwork() {
		return localAreaNetwork;
	}

	public void setLocalAreaNetwork(String localAreaNetwork) {
		this.localAreaNetwork = localAreaNetwork;
	}
}
