package cn.com.cmdd.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.cmdd.domain.Banner;

public interface BannerDao {
	void addBanner(Banner banner);
	
	List<Banner> getBannerListByAgentId(@Param("agent_id") int agent_id);
	
	void deleteBanner(int id);
	
	Banner getBanner(int id);
	
	void updateBanner(Banner banner);
}
