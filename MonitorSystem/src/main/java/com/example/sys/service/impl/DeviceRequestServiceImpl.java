package com.example.sys.service.impl;

import com.example.sys.entity.DeviceRequest;
import com.example.sys.entity.ResourceRequest;
import com.example.sys.mapper.DeviceRequestMapper;
import com.example.sys.service.IDeviceRequestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class DeviceRequestServiceImpl extends ServiceImpl<DeviceRequestMapper, DeviceRequest> implements IDeviceRequestService {

	//根据ID查询设备申请使用信息
	@Override
	public DeviceRequest getDeviceRequestById(Integer id) {
		DeviceRequest deviceRequest = this.baseMapper.selectById(id);
		return deviceRequest;
	}

	//新增设备申请使用信息
	@Override
	public void addDeviceRequest(DeviceRequest deviceRequest) {
		LocalDateTime requestTime = LocalDateTime.now();
		deviceRequest.setRequestTime(requestTime);
		this.baseMapper.insert(deviceRequest);
	}

	//根据ID删除设备申请使用信息
	@Override
	public void deleteDeviceRequestById(Integer id) {
		this.baseMapper.deleteById(id);
	}

	//修改设备申请使用信息及审核状态
	@Override
	public void updateDeviceRequest(DeviceRequest deviceRequest) {
		LocalDateTime approvalTime = LocalDateTime.now();
		Integer managerId = deviceRequest.getManagerId();
		if( managerId != null ){
			deviceRequest.setApprovalTime(approvalTime);
		}
		this.baseMapper.updateById(deviceRequest);
	}
}
