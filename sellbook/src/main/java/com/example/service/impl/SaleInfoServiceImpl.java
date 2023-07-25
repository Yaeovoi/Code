package com.example.service.impl;

import com.example.mapper.SaleInfoMapper;
import com.example.pojo.PageBean;
import com.example.pojo.SaleInfo;
import com.example.service.SaleInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class SaleInfoServiceImpl implements SaleInfoService {

	@Autowired
	private SaleInfoMapper saleInfoMapper;

	//根据isbn,number添加销售记录
	@Override
	public void addByIsbn(String isbn, Integer number) {
		LocalDate updateTime = LocalDate.now();//设置系统日期
		Integer allNumber = saleInfoMapper.getNumber(isbn);//查询书籍库存
		Integer bookNumber = allNumber - number;//获得更新后的书籍库存数量
		try {
			saleInfoMapper.insert(isbn, number, updateTime);//增加销售记录
			saleInfoMapper.updateBookNumber(bookNumber, isbn);//更新书籍库存
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//统计销售情况
	@Override
	public PageBean page(Integer page, Integer pageSize, String isbn, LocalDate updateTime) {
		//1.设置分页参数
		PageHelper.startPage(page, pageSize);

		//2.执行查询
		List<SaleInfo> saleInfoList = saleInfoMapper.list(isbn, updateTime);
		Page<SaleInfo> p = (Page<SaleInfo>)saleInfoList;

		//3.封装PageBean对象
		PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
		return pageBean;
	}

//	//根据isbn，number显示销售记录
//	@Override
//	public SaleInfo getByIsbn(String isbn, Integer number, LocalDate updateTime) {
//		return saleInfoMapper.getByIsbn(isbn, number, updateTime);
//	}

//	//根据isbn查询书籍库存
//	@Override
//	public SaleInfo getByisbn(Integer isbn) {
//		return saleInfoMapper.getByisbn(isbn);
//	}

}
