package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.CleanInfo;
import com.example.sys.entity.ProjectingInfo;
import com.example.sys.service.IProjectingInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 项目和用户中间表 前端控制器
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
@Api(tags = {"在研项目管理接口"})
@RestController
@RequestMapping("/projectingInfo")
public class ProjectingInfoController {

	@Autowired
	private IProjectingInfoService projectingInfoService;

	@ApiOperation("查询全部在研项目")
	@GetMapping("/all")
	public Result<List<ProjectingInfo>> getAllProjectingInfo(){
		List<ProjectingInfo> list = projectingInfoService.list();
		return Result.success(list,"查询成功");
	}

	@ApiOperation("根据ID查询在研项目信息")
	@GetMapping("/{id}")
	public Result<ProjectingInfo> getProjectingInfoById(@PathVariable("id") Integer id){
		ProjectingInfo projectingInfo = projectingInfoService.getProjectingInfoById(id);
		return Result.success(projectingInfo);
	}

	@ApiOperation("新增在研项目")
	@PostMapping
	public Result<?> addProjectingInfo(@RequestBody ProjectingInfo projectingInfo){
		projectingInfoService.addProjectingInfo(projectingInfo);
		return Result.success("新增在研项目成功！");
	}

	@ApiOperation("根据ID删除在研项目")
	@DeleteMapping("/{id}")
	public Result<ProjectingInfo> deleteProjectingInfoById(@PathVariable("id") Integer id){
		projectingInfoService.deleteProjectingInfoById(id);
		return Result.success("删除在研项目成功！");
	}

	@ApiOperation("修改在研项目信息")
	@PutMapping
	public Result<?> updateProjectingInfo(@RequestBody ProjectingInfo projectingInfo){
		projectingInfoService.updateProjectingInfo(projectingInfo);
		return Result.success("修改在研项目信息成功！");
	}
}
