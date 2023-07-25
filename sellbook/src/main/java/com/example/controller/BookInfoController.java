package com.example.controller;

import com.example.pojo.BookInfo;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.BookInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 书籍信息Controller
 */
@Slf4j
@RestController
@CrossOrigin
public class BookInfoController {

	@Autowired
	private BookInfoService bookInfoService;

	//分页条件查询
	@GetMapping("/books")
	public Result page(@RequestParam(defaultValue = "1") Integer page,
	                   @RequestParam(defaultValue = "8") Integer pageSize,
					   String isbn, String name, String author) {
		log.info("分页查询，参数：{},{},{},{},{}",page,pageSize,isbn,name,author);
		//调用service分页查询
		PageBean pageBean = bookInfoService.page(page,pageSize,isbn,name,author);
		return Result.success(pageBean);
	}

	//批量删除
	@DeleteMapping("/books/{ids}")
	public Result delete(@PathVariable List<Integer> ids){
		log.info("批量删除：ids:{}",ids);
		bookInfoService.delete(ids);
		return Result.success();
	}

	//新增书籍
	@PostMapping("/books")
	public Result save(@RequestBody BookInfo bookInfo){
		log.info("新增书籍，bookInfo:{}", bookInfo);
		bookInfoService.save(bookInfo);
		return Result.success();
	}

	//根据ID查询书籍
	@GetMapping("/books/{id}")
	public Result getById(@PathVariable Integer id){
		log.info("根据ID查询书籍，id：{}", id);
		BookInfo bookInfo = bookInfoService.getById(id);
		return Result.success(bookInfo);
	}

	//更新书籍信息
	@PutMapping("/books")
	public Result update(@RequestBody BookInfo bookInfo){
		log.info("更新书籍信息：{}", bookInfo);
		bookInfoService.update(bookInfo);
		return Result.success();
	}

	//	@GetMapping("/books")
//	public Result list(){
//		log.info("查询全部书籍信息：");
//
//		//调用service查询书籍信息
//		List<BookInfo> bookInfoList = bookInfoService.list();
//		return Result.success(bookInfoList);
//	}
}
