package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.DeviceInfo;
import com.example.sys.entity.LevelRequest;
import com.example.sys.service.ILevelRequestService;
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
@Api(tags = {"请假申请管理接口"})
@RestController
@RequestMapping("/levelRequest")
public class LevelRequestController {

	@Autowired
	private ILevelRequestService levelRequestService;

	@ApiOperation("查询全部请假信息")
	@GetMapping("/all")
	public Result<List<LevelRequest>> getAllLevelRequest(){
		List<LevelRequest> list = levelRequestService.list();
		return Result.success(list,"查询成功");
	}

	@ApiOperation("根据ID查询请假信息")
	@GetMapping("/{id}")
	public Result<LevelRequest> getLevelRequestById(@PathVariable("id") Integer id){
		LevelRequest levelRequest = levelRequestService.getLevelRequestById(id);
		return Result.success(levelRequest);
	}

	@ApiOperation("新增请假申请")
	@PostMapping
	public Result<?> addLevelRequest(@RequestBody LevelRequest levelRequest){
		levelRequestService.addLevelRequest(levelRequest);
		return Result.success("新增请假申请成功！");
	}

	@ApiOperation("根据ID删除请假申请")
	@DeleteMapping("/{id}")
	public Result<LevelRequest> deleteLevelRequestById(@PathVariable("id") Integer id){
		levelRequestService.deleteLevelRequestById(id);
		return Result.success("删除请假申请成功！");
	}

	@ApiOperation("修改请假申请")
	@PutMapping
	public Result<?> updateLevelRequest(@RequestBody LevelRequest levelRequest){
		levelRequestService.updateLevelRequest(levelRequest);
		return Result.success("修改成功！");
	}
}
