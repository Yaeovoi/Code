package com.lantu.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

	private String name;
	private Integer age;
	private Integer balance;

	//ID自增
	@TableId(type = IdType.AUTO)
	private Long id;

	//value="email" 是email取代表里的mail作为查询条件
	//select=false 是mail这个值不作为查询条件
//	@TableField(value = "email", select = false)
	private String email;

	//exist=false 是表里没有status字段，不做映射
	@TableField(exist = false)
	private String status;

	@TableLogic
	private Integer deleted;

	//乐观锁版本号，防止并发出现的一些同时操作数据库的问题
	@Version
	private Integer version;
}
