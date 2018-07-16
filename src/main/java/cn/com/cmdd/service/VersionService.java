package cn.com.cmdd.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.VersionDao;
import cn.com.cmdd.domain.Version;

@Service
@Transactional
public class VersionService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(VersionService.class);
	
	@Autowired
	private VersionDao versionDao;
	
	public Version get(){
		return versionDao.select(1);
	}
	
	public void update(Version version) {
		versionDao.update(version);
	}
	
	
	
}
