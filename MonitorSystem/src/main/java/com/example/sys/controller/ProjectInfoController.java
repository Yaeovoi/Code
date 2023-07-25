package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.DeptInfo;
import com.example.sys.entity.ProjectInfo;
import com.example.sys.service.IProjectInfoService;
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
@Api(tags = {"项目管理接口"})
@RestController
@RequestMapping("/projectInfo")
public class ProjectInfoController {

	@Autowired
	private IProjectInfoService projectInfoService;

	@ApiOperation("查询全部项目")
	@GetMapping("/all")
	public Result<List<ProjectInfo>> getAllProjectInfo(){
		List<ProjectInfo> list = projectInfoService.list();
		return Result.success(list,"查询成功");
	}

	@ApiOperation("根据ID查询项目")
	@GetMapping("/{id}")
	public Result<ProjectInfo> getProjectInfoById(@PathVariable("id") Integer id){
		ProjectInfo projectInfo = projectInfoService.getProjectInfoById(id);
		return Result.success(projectInfo);
	}

	@ApiOperation("新增项目")
	@PostMapping
	public Result<?> addProjectInfo(@RequestBody ProjectInfo projectInfo){
		projectInfoService.addProjectInfo(projectInfo);
		return Result.success("新增项目成功！");
	}

	@ApiOperation("根据ID删除项目")
	@DeleteMapping("/{id}")
	public Result<ProjectInfo> deleteProjectInfoById(@PathVariable("id") Integer id){
		projectInfoService.deleteProjectInfoById(id);
		return Result.success("删除项目成功！");
	}

	@ApiOperation("修改项目信息")
	@PutMapping
	public Result<?> updateProjectInfo(@RequestBody ProjectInfo projectInfo){
		projectInfoService.updateProjectInfo(projectInfo);
		return Result.success("修改成功！");
	}
}
