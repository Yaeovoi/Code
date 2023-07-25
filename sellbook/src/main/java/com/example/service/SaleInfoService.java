package com.example.service;

import com.example.pojo.PageBean;
import java.time.LocalDate;

public interface SaleInfoService {

	//根据isbn,number添加销售记录
	void addByIsbn(String isbn, Integer number);

	//统计销售情况
	PageBean page(Integer page, Integer pageSize, String isbn, LocalDate updateTime);

//	//根据isbn，number显示销售记录
//	SaleInfo getByIsbn(String isbn, Integer number, LocalDate updateTime);

//	//根据isbn查询书籍库存
//	SaleInfo getByisbn(Integer isbn);
}
