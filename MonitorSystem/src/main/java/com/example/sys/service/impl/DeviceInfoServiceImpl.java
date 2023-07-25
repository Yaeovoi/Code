package com.example.sys.service.impl;

import com.example.sys.entity.DeptInfo;
import com.example.sys.entity.DeviceInfo;
import com.example.sys.mapper.DeviceInfoMapper;
import com.example.sys.service.IDeviceInfoService;
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
public class DeviceInfoServiceImpl extends ServiceImpl<DeviceInfoMapper, DeviceInfo> implements IDeviceInfoService {

	//根据ID查询设备信息
	@Override
	public DeviceInfo getDeviceInfoById(Integer id) {
		DeviceInfo deviceInfo = this.baseMapper.selectById(id);
		return deviceInfo;
	}

	//新增设备
	@Override
	public void addDeviceInfo(DeviceInfo deviceInfo) {
		LocalDateTime createTime = LocalDateTime.now();
		deviceInfo.setCreateTime(createTime);
		this.baseMapper.insert(deviceInfo);
	}

	//根据ID删除设备
	@Override
	public void deleteDeviceInfoById(Integer id) {
		this.baseMapper.deleteById(id);
	}

	//修改设备信息
	@Override
	public void updateDeviceInfo(DeviceInfo deviceInfo) {
		LocalDateTime updateTime = LocalDateTime.now();
		deviceInfo.setUpdateTime(updateTime);
		this.baseMapper.updateById(deviceInfo);
	}
}
