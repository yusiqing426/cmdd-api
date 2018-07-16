package cn.com.cmdd.dao;



import org.apache.ibatis.annotations.Param;

import cn.com.cmdd.domain.OrderCount;
import cn.com.cmdd.domain.Orders;
import cn.com.cmdd.domain.OrdersCountModel;

import java.util.Date;
import java.util.List;

public interface OrdersDao {
	//:+
	void saveOrders(Orders orders);
	void insert(Orders orders);
	void insertById(Orders orders);
	//-
	void deleteOrders(@Param("id") Long id);
	void deleteCallService(@Param("orderId") Long orderId);
	void deleteOrderItemByDiningTableId(@Param("diningTableId") Long diningTableId);
	void deleteCallServiceByDiningTableId(@Param("diningTableId") Long diningTableId);
    void deleteMemberLotterLogByOrderId(@Param("orderId") Long orderId);
    //:\
    void updateIsUpload(@Param("id") Long id);
    
	void updateOrders(Orders Order);
	void updateSyn_status(Orders Order);
	void resetOrderPno(@Param("shopId") Integer shopId);
	void unifyOrderNo(
            @Param("diningTablePid") Long pId,
            @Param("diningTableIdList") List<Long> idList);
	void resetDiningTable(@Param("id") Long id);
	//:/
	List<Orders> selectByIsUpload(Integer shop_id, Integer sysn_status);
	Orders selectByDiningTableAndStatusId(
            @Param("diningTableId") Long diningTableId,
            @Param("statusId") Integer statusId);
	List<Long> selectIdListByPId(@Param("PId") Long PId);
	List<Orders> selectListById(@Param("id") Long id);
	List<Orders> selectList(
            @Param("shop_id") Integer shop_id,
            @Param("id") Integer id,
            @Param("page_size") Integer page_size,
            @Param("pageStart_index") Integer pageStart_index,
            @Param("dining_table_id") Long dining_table_id,
            @Param("start_time") Date start_time,
            @Param("end_time") Date end_time,
            @Param("statusList") List<Integer> statusList,
            @Param("typePay") Integer typePay);
	List<OrderCount> getOrdersCount(
            @Param("shop_id") Integer shop_id,
            @Param("start_time") Date start_time,
            @Param("end_time") Date end_time,
            @Param("statusList") List<Integer> statusList);
	OrdersCountModel getOrderPayCount(
            @Param("shop_id") Integer shop_id,
            @Param("start_time") Date start_time,
            @Param("end_time") Date end_time);
	double selectDiscoutPreferential(
            @Param("shopId") Integer shopId,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);




}
