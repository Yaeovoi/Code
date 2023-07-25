package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.ProjectInfo;
import com.example.sys.entity.ResourceInfo;
import com.example.sys.service.IResourceInfoService;
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
@Api(tags = {"采购信息管理接口"})
@RestController
@RequestMapping("/resourceInfo")
public class ResourceInfoController {

	@Autowired
	private IResourceInfoService resourceInfoService;

	@ApiOperation("查询全部采购信息")
	@GetMapping("/all")
	public Result<List<ResourceInfo>> getAllResourceInfo(){
		List<ResourceInfo> list = resourceInfoService.list();
		return Result.success(list,"查询成功");
	}

	@ApiOperation("根据ID查询采购信息")
	@GetMapping("/{id}")
	public Result<ResourceInfo> getResourceInfoById(@PathVariable("id") Integer id){
		ResourceInfo resourceInfo = resourceInfoService.getResourceInfoById(id);
		return Result.success(resourceInfo);
	}

	@ApiOperation("新增采购信息")
	@PostMapping
	public Result<?> addResourceInfo(@RequestBody ResourceInfo resourceInfo){
		resourceInfoService.addResourceInfo(resourceInfo);
		return Result.success("新增采购信息成功！");
	}

	@ApiOperation("根据ID删除采购信息")
	@DeleteMapping("/{id}")
	public Result<ResourceInfo> deleteResourceInfoById(@PathVariable("id") Integer id){
		resourceInfoService.deleteResourceInfoById(id);
		return Result.success("删除采购信息成功！");
	}

	@ApiOperation("修改采购信息信息")
	@PutMapping
	public Result<?> updateResourceInfo(@RequestBody ResourceInfo resourceInfo){
		resourceInfoService.updateResourceInfo(resourceInfo);
		return Result.success("修改采购信息成功！");
	}
}
