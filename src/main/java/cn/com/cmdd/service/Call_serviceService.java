package cn.com.cmdd.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.Call_serviceDao;
import cn.com.cmdd.domain.Call_service;

import java.util.List;

@Service
@Transactional
public class Call_serviceService {
	private final static Logger LOGGER = LoggerFactory.getLogger(Call_serviceService.class); 
	
	@Autowired
	private Call_serviceDao cSDao;
	
	public Integer saveCall_service(Call_service call_service){
		cSDao.saveCall_service(call_service);
		return call_service.getId();
	}
	
	public void deleteCall_service(Integer id){
		cSDao.deleteCall_service(id);
	}
	
	public List<Call_service> getCall_service(Integer shop_id){
		return cSDao.getCall_service(shop_id);
	}
	
	public void updatePrinter(Call_service call_service){
		cSDao.updateCall_service(call_service);
	}
}
