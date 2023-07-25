package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 书籍信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInfo {
	private Integer id;
	private String isbn;
	private String name;
	private String address;
	private String price;
	private Integer number;
	private String author;
}
