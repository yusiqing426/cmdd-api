package cn.com.cmdd.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import cn.com.cmdd.domain.MemberRechargeLog;
import cn.com.cmdd.domain.OrderItem;

import java.util.Date;
import java.util.List;

public interface MemberRechargeLogDao {
	
	 void addMemberRechargeLog(MemberRechargeLog memberRechargeLog);
	 void insertById(MemberRechargeLog memberRechargeLog);

	
	 void updateMemberRechargeLog(MemberRechargeLog memberRechargeLog);
	
	 List<MemberRechargeLog> getMemberRechargeLogListByMember(@Param("member_id") Long member_id,
                                                              @Param("category") Integer category,
                                                              @Param("type") Integer type);
	
	 List<MemberRechargeLog> getMemberRechargeLogListByShop_id(Integer shop_id);
	
	/**
	 * @author ysq19930526
	 * @param shop_id
	 * @param start_time
	 * @param end_time
	 * @return
	 */
	Double getMemberAmount(
            @Param("shop_id") Integer shop_id,
            @Param("start_time") Date start_time,
            @Param("end_time") Date end_time
    );
	
	Double getMemberGiveAmount(
            @Param("shop_id") Integer shop_id,
            @Param("start_time") Date start_time,
            @Param("end_time") Date end_time
    );
	//同步数据
		List<MemberRechargeLog> selectByIsUpload(Integer shop_id, Integer sysn_status);
		int updateIsUpload(Long id);
}
