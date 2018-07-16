package cn.com.cmdd.dao;

import cn.com.cmdd.domain.Platform;

public interface PlatformDao {

	Platform getPlatformById(int id);	//得到平台管理者

	void updatePlatformPassword(int id, String pwdencry);

}
