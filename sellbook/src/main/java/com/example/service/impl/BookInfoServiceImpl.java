package com.example.service.impl;

import com.example.mapper.BookInfoMapper;
import com.example.pojo.BookInfo;
import com.example.pojo.PageBean;
import com.example.service.BookInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookInfoServiceImpl implements BookInfoService {

	@Autowired
	private BookInfoMapper bookInfoMapper;

	//分页条件查询
	@Override
	public PageBean page(Integer page, Integer pageSize, String isbn, String name, String author) {
		//1.设置分页参数
		PageHelper.startPage(page, pageSize);

		//2.执行查询
		List<BookInfo> bookInfoList = bookInfoMapper.list(isbn, name, author);
		Page<BookInfo> p = (Page<BookInfo>)bookInfoList;

		//3.封装PageBean对象
		PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
		return pageBean;
	}

	//批量删除
	@Override
	public void delete(List<Integer> ids) {
		bookInfoMapper.delete(ids);
	}

	//新增书籍
	@Override
	public void save(BookInfo bookInfo) {
		bookInfoMapper.insert(bookInfo);
	}

	//根据ID查询书籍
	@Override
	public BookInfo getById(Integer id) {
		return bookInfoMapper.getById(id);
	}

	//更新书籍信息
	@Override
	public void update(BookInfo bookInfo) {
		bookInfoMapper.update(bookInfo);
	}

}
