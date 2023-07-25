package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 店员信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
	private Integer id;
	private String username;
	private String password;
}
