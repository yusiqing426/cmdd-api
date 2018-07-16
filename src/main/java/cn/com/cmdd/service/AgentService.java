package cn.com.cmdd.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.AgentDao;
import cn.com.cmdd.dao.UserDao;
import cn.com.cmdd.domain.Agent;
import cn.com.cmdd.domain.User;
import cn.com.cmdd.util.Md5Helper;

import java.util.List;

@Service
public class AgentService {

	private final static Logger LOGGER = LoggerFactory.getLogger(AgentService.class);
	
	@Autowired
	private AgentDao agentDao;
	
	@Autowired
	private UserDao userDao;
	
	//获取代理商信息列表
	@Transactional
	public List<Agent> getAgentList() {
		return agentDao.getAgentList();
	}	
	
	//增加代理商
	@Transactional
	public Integer addAgent(Agent agent){
		String pwdencry = agent.getPassword();
		//给密码加密
		String password = Md5Helper.MD5Encode(pwdencry);
		agent.setPassword(password);
		
		String account;
		List<Agent> agentList = agentDao.getAgentList();
		int size = agentList.size();
		
		//设置代理商的账户名
		if(size==0){
			account = "a100001";
		}else{
			int index = (agentList.size())-1;
			String str = agentList.get(index).getAccount();
			String[] split = str.split("a");
			int number = Integer.parseInt(split[1])+1;
			account = "a"+number;
		}
		agent.setAccount(account);
		agent.setPayable_date(1);
		agent.setPayable_expenses(100.0);
		agentDao.addAgent(agent);
		User user = new User();
		user.setAccount(agent.getAccount());
		user.setUser_id(agent.getId().longValue());
		user.setUser_key(agent.getUser_key());
		userDao.addUser(user);
		return agent.getId();
	}
	
	//根据id查询代理商信息
	@Transactional
	public Agent getAgent(int id){
		return agentDao.getAgentById(id);
	}

	//修改代理商信息
	@Transactional
	public void updateAgent(Agent agent) {
		
		String password = agent.getPassword();
		
		if(password.equals("000000")){
			String pwdencry = Md5Helper.MD5Encode(password);
			agent.setPassword(pwdencry);
		}
		
		agentDao.updateAgent(agent);	
	}
}




















