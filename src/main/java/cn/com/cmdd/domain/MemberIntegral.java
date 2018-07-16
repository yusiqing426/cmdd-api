package cn.com.cmdd.domain;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("MemberIntegral")
public class MemberIntegral {
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long id;
    private Integer shopId;
    private Double consumeMoney;
    private Double consumeIntegral;
    private Double convertMoney;
    private Double convertIntegral;
    private int sync_status;

    public Integer getSync_status() {
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

	public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Double getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(Double consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    public Double getConsumeIntegral() {
        return consumeIntegral;
    }

    public void setConsumeIntegral(Double consumeIntegral) {
        this.consumeIntegral = consumeIntegral;
    }

    public Double getConvertMoney() {
        return convertMoney;
    }

    public void setConvertMoney(Double convertMoney) {
        this.convertMoney = convertMoney;
    }

    public Double getConvertIntegral() {
        return convertIntegral;
    }

    public void setConvertIntegral(Double convertIntegral) {
        this.convertIntegral = convertIntegral;
    }
}
