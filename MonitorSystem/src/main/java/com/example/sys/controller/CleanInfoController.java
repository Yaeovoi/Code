package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.CleanInfo;
import com.example.sys.entity.DeptInfo;
import com.example.sys.service.ICleanInfoService;
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
@Api(tags = {"值日管理接口"})
@RestController
@RequestMapping("/cleanInfo")
public class CleanInfoController {

	@Autowired
	private ICleanInfoService cleanInfoService;

	@ApiOperation("查询全部值日信息")
	@GetMapping("/all")
	public Result<List<CleanInfo>> getAllCleanInfo(){
		List<CleanInfo> list = cleanInfoService.list();
		return Result.success(list,"查询成功");
	}

	@ApiOperation("根据ID查询值日信息")
	@GetMapping("/{id}")
	public Result<CleanInfo> getCleanInfoById(@PathVariable("id") Integer id){
		CleanInfo cleanInfo = cleanInfoService.getCleanInfoById(id);
		return Result.success(cleanInfo);
	}

	@ApiOperation("新增值日信息")
	@PostMapping
	public Result<?> addCleanInfo(@RequestBody CleanInfo cleanInfo){
		cleanInfoService.addCleanInfo(cleanInfo);
		return Result.success("新增值日信息成功！");
	}

	@ApiOperation("根据ID删除值日信息")
	@DeleteMapping("/{id}")
	public Result<CleanInfo> deleteCleanInfoById(@PathVariable("id") Integer id){
		cleanInfoService.deleteCleanInfoById(id);
		return Result.success("删除值日信息成功！");
	}

	@ApiOperation("修改值日信息")
	@PutMapping
	public Result<?> updateCleanInfo(@RequestBody CleanInfo cleanInfo){
		cleanInfoService.updateCleanInfo(cleanInfo);
		return Result.success("修改值日信息成功！");
	}
}
