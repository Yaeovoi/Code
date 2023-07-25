package com.example.service;

import com.example.pojo.BookInfo;
import com.example.pojo.PageBean;

import java.util.List;

public interface BookInfoService {

//	//查询全部书籍信息
//	List<BookInfo> list();

	//分页查询
	PageBean page(Integer page, Integer pageSize, String isbn, String name, String author);

	//批量删除
	void delete(List<Integer> ids);

	//新增书籍
	void save(BookInfo bookInfo);

	//根据ID查询书籍
	BookInfo getById(Integer id);

	//更新书籍信息
	void update(BookInfo bookInfo);
}
