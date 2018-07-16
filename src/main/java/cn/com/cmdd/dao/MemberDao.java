package cn.com.cmdd.dao;



import java.util.List;

import cn.com.cmdd.domain.Member;
import cn.com.cmdd.domain.MemberLotteryLog;

public interface MemberDao {
	
	Long addMember(Member member);

	void insertById(Member member);
	
	void deleteMember(Long id);

	Member getMemberById(Long id);

	void updateMember(Member member);
	
	List<Member> getMemberListByShop_id(Integer shop_id);
	List<Member> birth(Integer shopId, Integer day);
	
	void updateMemberPassword(Long id, String pwdencry);
	
	//同步数据
	List<Member> selectByIsUpload(Integer shop_id, Integer sysn_status);
	//int updateIsUpload(Long id);
}
