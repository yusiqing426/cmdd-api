package cn.com.cmdd.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.AgentPayDao;
import cn.com.cmdd.domain.AgentPay;

import java.util.List;

@Service
public class AgentPayService {

	@Autowired
	private AgentPayDao agentPayDao;
	
	@Transactional
	public Integer addAgentPay(AgentPay agentPay) {

		agentPayDao.addAgentPay(agentPay);
		return agentPay.getId();
	}

	@Transactional
	public List<AgentPay> getAgentPayList() {
		return agentPayDao.getAgentPayList();
	}

	@Transactional
	public AgentPay getAgentPayById(int id) {
		return agentPayDao.getAgentPayById(id);
	}

	@Transactional
	public List<AgentPay> getAgentPayListByAgentId(int agent_id) {
		return agentPayDao.getAgentPayListByAgentId(agent_id);
	}

	
	@Transactional
	public void updateAgentPay(AgentPay agent_pay){
		
		agentPayDao.updateAgentPay(agent_pay);
	}
	
	
}
