package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.DeviceInfo;
import com.example.sys.entity.ProjectInfo;
import com.example.sys.service.IDeviceInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
@Api(tags = {"设备信息管理接口"})
@RestController
@RequestMapping("/deviceInfo")
public class DeviceInfoController {

	@Autowired
	private IDeviceInfoService deviceInfoService;

	@ApiOperation("查询全部设备信息")
	@GetMapping("/all")
	public Result<List<DeviceInfo>> getAllDeviceInfo(){
		List<DeviceInfo> list = deviceInfoService.list();
		return Result.success(list,"查询成功");
	}

	@ApiOperation("根据ID查询设备信息")
	@GetMapping("/{id}")
	public Result<DeviceInfo> getDeviceInfoById(@PathVariable("id") Integer id){
		DeviceInfo deviceInfo = deviceInfoService.getDeviceInfoById(id);
		return Result.success(deviceInfo);
	}

	@ApiOperation("新增设备")
	@PostMapping
	public Result<?> addDeviceInfo(@RequestBody DeviceInfo deviceInfo){
		deviceInfoService.addDeviceInfo(deviceInfo);
		return Result.success("新增设备成功！");
	}

	@ApiOperation("根据ID删除设备")
	@DeleteMapping("/{id}")
	public Result<DeviceInfo> deleteDeviceInfoById(@PathVariable("id") Integer id){
		deviceInfoService.deleteDeviceInfoById(id);
		return Result.success("删除设备成功！");
	}

	@ApiOperation("修改设备信息")
	@PutMapping
	public Result<?> updateDeviceInfo(@RequestBody DeviceInfo deviceInfo){
		deviceInfoService.updateDeviceInfo(deviceInfo);
		return Result.success("修改成功！");
	}
}
