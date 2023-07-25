package com.example.sys.controller;

import com.example.common.vo.Result;
import com.example.sys.entity.DeptInfo;
import com.example.sys.entity.RankInfo;
import com.example.sys.service.IRankInfoService;
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
@Api(tags = {"职称管理接口"})
@RestController
@RequestMapping("/rankInfo")
public class RankInfoController {

	@Autowired
	private IRankInfoService rankInfoService;

	@ApiOperation("查询全部职称")
	@GetMapping("/all")
	public Result<List<RankInfo>> getAllRankInfo(){
		List<RankInfo> list = rankInfoService.list();
		return Result.success(list,"查询成功");
	}

	@ApiOperation("根据ID查询职称")
	@GetMapping("/{id}")
	public Result<RankInfo> getRankById(@PathVariable("id") Integer id){
		RankInfo rank = rankInfoService.getRankById(id);
		return Result.success(rank);
	}

	@ApiOperation("新增职称")
	@PostMapping
	public Result<?> addRank(@RequestBody RankInfo rankInfo){
		rankInfoService.addRankInfo(rankInfo);
		return Result.success("新增职称成功！");
	}

	@ApiOperation("根据ID删除职称")
	@DeleteMapping("/{id}")
	public Result<RankInfo> deleteRankById(@PathVariable("id") Integer id){
		rankInfoService.deleteRankById(id);
		return Result.success("删除职称成功！");
	}

	@ApiOperation("修改职称信息")
	@PutMapping
	public Result<?> updateRank(@RequestBody RankInfo rankInfo){
		rankInfoService.updateRank(rankInfo);
		return Result.success("修改职称成功！");
	}
}
