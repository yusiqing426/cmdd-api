package cn.com.cmdd.dao;



import java.util.List;

import cn.com.cmdd.domain.MemberLotteryLog;
import cn.com.cmdd.domain.MemberRechargeLog;

public interface MemberLotteryLogDao {
	void addMemberLotteryLog(MemberLotteryLog memberLotteryLogDao);
	void insertById(MemberLotteryLog memberLotteryLogDao);

	MemberLotteryLog getMemberLotteryLogById(Long id);

	void updateMemberLotteryLog(MemberLotteryLog memberLotteryLogDao);

	List<MemberLotteryLog> getMemberLotteryLogListByPhoneAndShopId(Integer shop_id, String phone);
	
	List<MemberLotteryLog> getMemberLotteryLogListByOrderId(Long order_id);

	void deleteMemberLotteryLog(Long id);
	
	MemberLotteryLog getMemberLotteryLogByOrderId(Long Order_id);
	
	//同步数据
	List<MemberLotteryLog> selectByIsUpload(Integer shop_id, Integer sysn_status);
    int updateIsUpload(Long id);
	
}
