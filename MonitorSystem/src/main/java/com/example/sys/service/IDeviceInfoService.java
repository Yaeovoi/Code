package com.example.sys.service;

import com.example.sys.entity.DeviceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
public interface IDeviceInfoService extends IService<DeviceInfo> {

	//根据ID查询设备信息
	DeviceInfo getDeviceInfoById(Integer id);

	//新增设备
	void addDeviceInfo(DeviceInfo deviceInfo);

	//根据ID删除设备
	void deleteDeviceInfoById(Integer id);

	//修改设备信息
	void updateDeviceInfo(DeviceInfo deviceInfo);
}
