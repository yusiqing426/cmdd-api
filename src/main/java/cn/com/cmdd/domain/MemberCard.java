package cn.com.cmdd.domain;


import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

@Alias("MemberCard")
public class MemberCard {
	@JsonFormat(shape=JsonFormat.Shape.STRING)
    private Long id;
    private Integer shopId;
    private String name;
    private Double discount;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
