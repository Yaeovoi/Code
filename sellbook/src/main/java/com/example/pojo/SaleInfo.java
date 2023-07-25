package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 销售信息实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleInfo {
	private LocalDate updateTime;
	private String isbn;
	private String name;
	private Integer number;
	private String price;
	private String total;
	private String totalSum;
}
