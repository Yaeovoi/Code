package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.ResourceInfo;
import com.example.sys.entity.ResourceRequest;
import com.example.sys.service.IResourceRequestService;
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
@Api(tags = {"耗材申请管理接口"})
@RestController
@RequestMapping("/resourceRequest")
public class ResourceRequestController {

	@Autowired
	private IResourceRequestService resourceRequestService;

	@ApiOperation("查询全部耗材申请信息")
	@GetMapping("/all")
	public Result<List<ResourceRequest>> getAllResourceRequest(){
		List<ResourceRequest> list = resourceRequestService.list();
		return Result.success(list,"查询成功");
	}

	@ApiOperation("根据ID查询耗材申请信息")
	@GetMapping("/{id}")
	public Result<ResourceRequest> getResourceRequestById(@PathVariable("id") Integer id){
		ResourceRequest resourceRequest = resourceRequestService.getResourceRequestById(id);
		return Result.success(resourceRequest);
	}

	@ApiOperation("新增耗材申请信息")
	@PostMapping
	public Result<?> addResourceRequest(@RequestBody ResourceRequest resourceRequest){
		resourceRequestService.addResourceRequest(resourceRequest);
		return Result.success("新增耗材申请信息成功！");
	}

	@ApiOperation("根据ID删除耗材申请信息")
	@DeleteMapping("/{id}")
	public Result<ResourceRequest> deleteResourceRequestById(@PathVariable("id") Integer id){
		resourceRequestService.deleteResourceRequestById(id);
		return Result.success("删除耗材申请信息成功！");
	}

	@ApiOperation("修改耗材申请信息及审核状态")
	@PutMapping
	public Result<?> updateResourceRequest(@RequestBody ResourceRequest resourceRequest){
		resourceRequestService.updateResourceRequest(resourceRequest);
		return Result.success("修改耗材申请信息成功！");
	}
}
