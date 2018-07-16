package cn.com.cmdd.dao;

import java.util.List;

import cn.com.cmdd.domain.AgentPay;

public interface AgentPayDao {

	void addAgentPay(AgentPay agentPay);

	List<AgentPay> getAgentPayList();

	AgentPay getAgentPayById(int id);
	
	void updateAgentPay(AgentPay agentPay);

	List<AgentPay> getAgentPayListByAgentId(int agent_id);
	
}
