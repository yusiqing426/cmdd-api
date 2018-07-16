package cn.com.cmdd.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.MemberLotteryLogDao;
import cn.com.cmdd.domain.MemberLotteryLog;

import java.util.List;

@Service
public class MemberLotteryLogService {
	private final static Logger LOGGER = LoggerFactory.getLogger(MemberLotteryLogService.class);
	
	@Autowired
	private MemberLotteryLogDao memberLotteryLogDao;

	@Transactional
	public void addMemberLotteryLog(MemberLotteryLog memberLotteryLog)throws Exception{

		Long order_id = memberLotteryLog.getOrder_id();
		List<MemberLotteryLog> memberLotteryLogListByOrderId = memberLotteryLogDao.getMemberLotteryLogListByOrderId(order_id);
		int num = memberLotteryLogListByOrderId.size();
		
		if(num>0){
			throw new Exception("您已抽过奖品，请下次再试");
		}
		
		memberLotteryLogDao.addMemberLotteryLog(memberLotteryLog);
		
	}	
	
	
	@Transactional
	public void deleteMemberLotteryLog(Long id){
		
		 memberLotteryLogDao.deleteMemberLotteryLog(id);
	}
	
	
	@Transactional
	public MemberLotteryLog getMemberLotteryLog(Long id){
		
		return memberLotteryLogDao.getMemberLotteryLogById(id);
	}
	
	@Transactional
	public List<MemberLotteryLog> getMemberLotteryLogListByPhoneAndShopId(Integer shop_id ,String phone){
		
		return memberLotteryLogDao.getMemberLotteryLogListByPhoneAndShopId(shop_id,phone);
	}

	
	@Transactional
	public void updateMemberLotteryLog(MemberLotteryLog memberLotteryLog){
		//if(!memberLotteryLog.getSync_status().equals(new Integer(0)))memberLotteryLog.setSync_status(1);
		memberLotteryLogDao.updateMemberLotteryLog(memberLotteryLog);
	}
	
	@Transactional
	public MemberLotteryLog getMemberLotteryLogByOrderId(Long order_id){
	
		return memberLotteryLogDao.getMemberLotteryLogByOrderId(order_id);
	}
	
	
	
}
