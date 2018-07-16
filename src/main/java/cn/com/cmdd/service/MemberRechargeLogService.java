package cn.com.cmdd.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.MemberRechargeLogDao;
import cn.com.cmdd.domain.MemberRechargeLog;
import cn.com.cmdd.util.DateUtil;

@Service
public class MemberRechargeLogService {
	private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MemberRechargeLogService.class);
	
	@Autowired
	private MemberRechargeLogDao memberRechargeLogDao;

	@Transactional
	public void addMemberRechargeLog(MemberRechargeLog memberRechargeLog){

		memberRechargeLogDao.addMemberRechargeLog(memberRechargeLog);
		
	}	
	
	
	@Transactional
	public List<MemberRechargeLog> getMemberRechargeLogListByMember(Long member_id){
		
		return memberRechargeLogDao.getMemberRechargeLogListByMember(member_id,null,null);
	}

	
	@Transactional
	public List<MemberRechargeLog> getMemberRechargeLogListByShop_id(Integer shop_id){
		
		return memberRechargeLogDao.getMemberRechargeLogListByShop_id(shop_id);
	}

	@Transactional
	public Double getMemberAmount(Integer shop_id,Date start_time,Date end_time) throws ParseException {

		if(end_time!=null){

			String endTime = DateUtil.getYyyyMMdd_(end_time);
			String endTime2 = endTime+" 23:59:59";
			end_time = new Date(endTime2);
			//end_time = DateUtil.yYYYYIMMIDD_ToDate(endTime2);

		}
		return memberRechargeLogDao.getMemberAmount(shop_id, start_time, end_time);
	}
	
	@Transactional
	public Double getMemberGiveAmount(Integer shop_id,Date start_time,Date end_time) throws ParseException {

		if(end_time!=null){

			String endTime = DateUtil.getYyyyMMdd_(end_time);
			String endTime2 = endTime+" 23:59:59";
			end_time = new Date(endTime2);
			//end_time = DateUtil.yYYYYIMMIDD_ToDate(endTime2);

		}
		return memberRechargeLogDao.getMemberGiveAmount(shop_id, start_time, end_time);
	}
	
}
