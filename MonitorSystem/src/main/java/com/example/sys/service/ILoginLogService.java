package com.example.sys.service;

import com.example.sys.entity.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sys.entity.UserInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
public interface ILoginLogService extends IService<LoginLog> {

	//新增登录记录
	void addLoginLog(LoginLog loginLog);

	//新增注销记录
	void addLogout(LoginLog loginLog);
}
