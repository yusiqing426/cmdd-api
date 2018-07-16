package cn.com.cmdd.dao;

import org.apache.ibatis.annotations.Param;

import cn.com.cmdd.domain.Version;

;

public interface VersionDao {
	
	//：/- id:1
	Version select(@Param("id") Integer id);
	
	//：\- id:1
	void update(Version version);
	
	
}
