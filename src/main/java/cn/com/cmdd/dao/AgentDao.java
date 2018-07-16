package cn.com.cmdd.dao;




import java.util.List;

import cn.com.cmdd.domain.Agent;

public interface AgentDao {
	void addAgent(Agent agent);

	Agent getAgentById(int id);			//根据id获取代理商信息

	void updateAgent(Agent agent);

	List<Agent> getAgentList();

	void updatePlatformPassword(int id, String pwdencry);
}
