package com.example.controller;

import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.SaleInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;


@Slf4j
@RestController
@CrossOrigin
public class SaleInfoController {

	@Autowired
	private SaleInfoService saleInfoService;

	//统计销售情况
	@GetMapping("/sales")
	public Result check(@RequestParam(defaultValue = "1") Integer page,
	                    @RequestParam(defaultValue = "8") Integer pageSize,
						String isbn,
						@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate updateTime) {
		log.info("统计销售情况，参数：{},{},{},{}",page,pageSize,isbn,updateTime);
		//调用service分页查询
		PageBean pageBean = saleInfoService.page(page,pageSize,isbn,updateTime);
		return Result.success(pageBean);
//		List<SaleInfo> saleInfoList = saleInfoService.check(isbn,updateTime);
//		return Result.success(saleInfoList);
	}

	//新增销售信息
	@PostMapping ("/sales/{isbn},{number}")
	public Result addByIsbn(@PathVariable String isbn, @PathVariable Integer number){
		log.info("新增销售信息，isbn，number:{},{}", isbn, number);
		saleInfoService.addByIsbn(isbn, number);
		return Result.success();
	}

	//	//分页条件查询
//	@GetMapping("/sales/total")
//	public Result check(@RequestParam(defaultValue = "1") Integer page,
//	                    @RequestParam(defaultValue = "5") Integer pageSize,
//	                    String isbn,
//	                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate updateTime) {
//		log.info("分页查询，参数：{},{},{},{}",page,pageSize,isbn,updateTime);
//		//调用service分页查询
//		PageBean pageBean = saleInfoService.check(page,pageSize,isbn,updateTime);
//		return Result.success(pageBean);
//	}

//	//显示销售信息
//	@GetMapping ("/sales")
//	public Result getByIsbn(String isbn, Integer number,
//	                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate updateTime){
//		log.info("根据isbn，number显示销售记录，isbn，number:{},{},{}", isbn, number, updateTime);
//		SaleInfo saleInfo = saleInfoService.getByIsbn(isbn, number, updateTime);
//		return Result.success(saleInfo);
//	}

//	//根据isbn查询书籍库存
//	@GetMapping("/sales/{isbn}")
//	public Result getById(@PathVariable Integer isbn){
//		log.info("根据isbn查询书籍，isbn：{}", isbn);
//		SaleInfo saleInfo = saleInfoService.getByisbn(isbn);
//		return Result.success(saleInfo);
//	}
}
