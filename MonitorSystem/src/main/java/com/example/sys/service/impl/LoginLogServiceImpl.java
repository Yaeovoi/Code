package com.example.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.vo.Result;
import com.example.sys.entity.LoginLog;
import com.example.sys.entity.UserInfo;
import com.example.sys.mapper.LoginLogMapper;
import com.example.sys.service.ILoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {


	//新增登录记录
	@Override
	public void addLoginLog(LoginLog loginLog) {
		this.baseMapper.insert(loginLog);
	}

	//新增注销记录
	@Override
	public void addLogout(LoginLog loginLog) {
		LambdaQueryWrapper<LoginLog> wrapper = new LambdaQueryWrapper<>();

		//构建查询条件 -> userId相同和id最大且唯一
		wrapper.eq(LoginLog::getUserId, loginLog.getUserId());
		wrapper.orderByDesc(LoginLog::getId);
		wrapper.last("LIMIT 1");

		LoginLog updateLog = this.baseMapper.selectOne(wrapper);
		//定位到同userId最新登录的登录记录
		loginLog.setId(updateLog.getId());

		this.baseMapper.updateById(loginLog);
	}

}
