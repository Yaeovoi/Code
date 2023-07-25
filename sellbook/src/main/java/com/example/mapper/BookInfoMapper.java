package com.example.mapper;

import com.example.pojo.BookInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookInfoMapper {

	//查询书籍所有信息
//	@Select("select * from book_info")
//	List<BookInfo> list();

	//分页条件查询
	public List<BookInfo> list(String isbn, String name, String author);

	//批量删除
	void delete(List<Integer> ids);

	//新增书籍
	@Insert("insert into book_info(id, isbn, name, address, price, number, author)" +
	        "values(#{id},#{isbn},#{name},#{address},#{price},#{number},#{author})")
	void insert(BookInfo bookInfo);

	//根据ID查询书籍
	@Select("select * from book_info where id = #{id}")
	BookInfo getById(Integer id);

	//更新书籍信息
	void update(BookInfo bookInfo);
}
