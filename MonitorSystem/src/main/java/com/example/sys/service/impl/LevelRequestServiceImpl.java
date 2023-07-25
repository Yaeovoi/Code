package com.example.sys.service.impl;

import com.example.sys.entity.DeviceRequest;
import com.example.sys.entity.LevelRequest;
import com.example.sys.mapper.LevelRequestMapper;
import com.example.sys.service.ILevelRequestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import static java.lang.Integer.valueOf;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
@Service
public class LevelRequestServiceImpl extends ServiceImpl<LevelRequestMapper, LevelRequest> implements ILevelRequestService {

	//根据ID查询请假信息
	@Override
	public LevelRequest getLevelRequestById(Integer id) {
		LevelRequest levelRequest = this.baseMapper.selectById(id);
		return levelRequest;
	}

	//新增请假申请
	@Override
	public void addLevelRequest(LevelRequest levelRequest) {
		LocalDate beginDate = LocalDate.now();//请假开始时间
		levelRequest.setBeginDate(beginDate);//设置请假开始时间
		//请假时间 -> 结束 - 开始
		Period period = Period.between(beginDate, levelRequest.getEndDate());
		int days = period.getDays();
		levelRequest.setLevelTime(days);
		this.baseMapper.insert(levelRequest);
	}

	//根据ID删除请假申请
	@Override
	public void deleteLevelRequestById(Integer id) {
		this.baseMapper.deleteById(id);
	}

	//修改请假申请
	@Override
	public void updateLevelRequest(LevelRequest levelRequest) {
		LocalDateTime approvalTime = LocalDateTime.now();
		Integer managerId = levelRequest.getManagerId();
		if( managerId != null ){
			levelRequest.setApprovalTime(approvalTime);
		}
		this.baseMapper.updateById(levelRequest);
	}

}
