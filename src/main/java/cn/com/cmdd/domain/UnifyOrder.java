package cn.com.cmdd.domain;



import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UnifyOrder {
    private Integer shopId;
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private Long diningTablePid;
    private ArrayList<Long> diningTableIdList;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Long getDiningTablePid() {
        return diningTablePid;
    }

    public void setDiningTablePid(Long diningTablePid) {
        this.diningTablePid = diningTablePid;
    }

    public ArrayList<Long> getDiningTableIdList() {
        return diningTableIdList;
    }

    public void setDiningTableIdList(ArrayList<Long> diningTableIdList) {
        this.diningTableIdList = diningTableIdList;
    }
}
