package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.DeptInfo;
import com.example.sys.service.IDeptInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CXK
 * @since 2023-07-23
 */
@Api(tags = {"部门管理接口"})
@RestController
@RequestMapping("/deptInfo")
public class DeptInfoController {

	@Autowired
	private IDeptInfoService deptInfoService;

	@ApiOperation("查询全部部门")
	@GetMapping("/all")
	public Result<List<DeptInfo>> getAllDept(){
		List<DeptInfo> list = deptInfoService.list();
		return Result.success(list,"查询成功");
	}

	@ApiOperation("根据ID查询部门")
	@GetMapping("/{id}")
	public Result<DeptInfo> getDeptById(@PathVariable("id") Integer id){
		DeptInfo dept = deptInfoService.getDeptById(id);
		return Result.success(dept);
	}

	@ApiOperation("新增部门")
	@PostMapping
	public Result<?> addDept(@RequestBody DeptInfo deptInfo){
		deptInfoService.addDeptInfo(deptInfo);
		return Result.success("新增部门成功！");
	}

	@ApiOperation("根据ID删除部门")
	@DeleteMapping("/{id}")
	public Result<DeptInfo> deleteDeptById(@PathVariable("id") Integer id){
		deptInfoService.deleteDeptById(id);
		return Result.success("删除成功！");
	}

	@ApiOperation("修改部门信息")
	@PutMapping
	public Result<?> updateDept(@RequestBody DeptInfo deptInfo){
		deptInfoService.updateDept(deptInfo);
		return Result.success("修改成功！");
	}
}
