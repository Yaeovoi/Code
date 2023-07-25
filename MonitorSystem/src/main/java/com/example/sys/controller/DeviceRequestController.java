package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.DeviceRequest;
import com.example.sys.entity.ResourceRequest;
import com.example.sys.service.IDeviceRequestService;
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
@Api(tags = {"设备申请使用管理接口"})
@RestController
@RequestMapping("/deviceRequest")
public class DeviceRequestController {

	@Autowired
	private IDeviceRequestService deviceRequestService;

	@ApiOperation("查询全部设备申请使用信息")
	@GetMapping("/all")
	public Result<List<DeviceRequest>> getAllDeviceRequest(){
		List<DeviceRequest> list = deviceRequestService.list();
		return Result.success(list,"查询成功");
	}

	@ApiOperation("根据ID查询设备申请使用信息")
	@GetMapping("/{id}")
	public Result<DeviceRequest> getDeviceRequestById(@PathVariable("id") Integer id){
		DeviceRequest deviceRequest = deviceRequestService.getDeviceRequestById(id);
		return Result.success(deviceRequest);
	}

	@ApiOperation("新增设备申请使用信息")
	@PostMapping
	public Result<?> addDeviceRequest(@RequestBody DeviceRequest deviceRequest){
		deviceRequestService.addDeviceRequest(deviceRequest);
		return Result.success("新增设备申请使用信息成功！");
	}

	@ApiOperation("根据ID删除设备申请使用信息")
	@DeleteMapping("/{id}")
	public Result<DeviceRequest> deleteDeviceRequestById(@PathVariable("id") Integer id){
		deviceRequestService.deleteDeviceRequestById(id);
		return Result.success("删除设备申请使用信息成功！");
	}

	@ApiOperation("修改设备申请使用信息及审核状态")
	@PutMapping
	public Result<?> updateDeviceRequest(@RequestBody DeviceRequest deviceRequest){
		deviceRequestService.updateDeviceRequest(deviceRequest);
		return Result.success("修改设备申请使用信息成功！");
	}
}
