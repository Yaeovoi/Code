package com.example.sys.service;

import com.example.sys.entity.DeviceRequest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
public interface IDeviceRequestService extends IService<DeviceRequest> {

	//根据ID查询设备申请使用信息
	DeviceRequest getDeviceRequestById(Integer id);

	//新增设备申请使用信息
	void addDeviceRequest(DeviceRequest deviceRequest);

	//根据ID删除设备申请使用信息
	void deleteDeviceRequestById(Integer id);

	//修改设备申请使用信息及审核状态
	void updateDeviceRequest(DeviceRequest deviceRequest);
}
