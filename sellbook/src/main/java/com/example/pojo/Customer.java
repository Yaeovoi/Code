package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 顾客信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	private Integer id;
	private String username;
	private String password;
}
