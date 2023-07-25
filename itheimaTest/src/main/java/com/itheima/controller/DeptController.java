package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RestController
@CrossOrigin
public class DeptController {

	@Autowired
	private DeptService deptService;

	/**
	 * 查询部门数据
	 *
	 */
	@GetMapping("/depts")
	public Result list() {
		log.info("查询全部部门数据：");

		//调用service查询部门数据
		List<Dept> deptList = deptService.list();
		return Result.success(deptList);
	}

	/**
	 * 删除部门
	 *
	 */
	@DeleteMapping("/depts/{id}")
	public Result delete(@PathVariable Integer id) {
		log.info("根据部门id删除：{}", id);

		//调用service删除部门
		deptService.delete(id);
		return Result.success();
	}

	/**
	 * 新增部门
	 *
	 */
	@PostMapping("/depts")
	public Result add(@RequestBody Dept dept) {
		log.info("新增部门：{}", dept);

		//调用service新增部门
		deptService.add(dept);
		return Result.success();
	}

	/**
	 * 修改部门信息
	 *
	 */
	@GetMapping("/depts/{id}")
	public Result getByID(@PathVariable Integer id) {
		log.info("获取部门ID:{}", id);
		Dept dept = deptService.getByID(id);
		return Result.success(dept);
	}

	@PutMapping("/depts")
	public Result update(@RequestBody Dept dept) {
		log.info("修改部门:{}", dept);
		deptService.update(dept);

		return Result.success();
	}
}