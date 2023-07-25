package com.example.mapper;

import com.example.pojo.SaleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface SaleInfoMapper {

	//统计销售情况
	List<SaleInfo> list(String isbn, LocalDate updateTime);

	//根据isbn，number增加销售信息
	void insert(String isbn, Integer number, LocalDate updateTime);

	//根据isbn查询库存
	@Select("select number from book_info where isbn=#{isbn}")
	Integer getNumber(String isbn);

	//修改书籍库存
	@Update("update book_info set number=#{bookNumber} where isbn=#{isbn}")
	void updateBookNumber(Integer bookNumber, String isbn);


//	//根据isbn查询书籍库存
//	@Select("select number from book_info where ISBN=#{isbn}")
//	SaleInfo getByisbn(Integer isbn);

	//显示销售记录
//	@Select("select * from sale_info where ISBN=#{isbn} and number=#{number} and update_time=#{updateTime} limit 1")
//	SaleInfo getByIsbn(String isbn, Integer number, LocalDate updateTime);
}
