package com.example;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/monitorsystem";
		String username = "root";
		String password = "123456";
		String moduleName = "sys";
		String mapperLocation = "F:\\SpringBootCode\\MonitorSystem\\src\\main\\resources\\mapper\\"+moduleName;
		String tables = "clean_info," +
						"dept_info," +
						"device_info," +
						"device_request," +
						"level_request," +
						"login_log," +
						"project_info," +
						"projecting_info," +
						"rank_info," +
						"register_meeting," +
						"register_record," +
						"resource_info," +
						"resource_request," +
						"user_info," +
						"user_register_meeting";

		FastAutoGenerator.create(url, username, password)
				.globalConfig(builder -> {
					builder.author("CXK") // 设置作者
							//.enableSwagger() // 开启 swagger 模式
							//.fileOverride() // 覆盖已生成文件
							.outputDir("F:\\SpringBootCode\\MonitorSystem\\src\\main\\java"); // 指定输出目录
				})
//				.packageConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
//					int typeCode = metaInfo.getJdbcType().TYPE_CODE;
//					if (typeCode == Types.SMALLINT) {
//						// 自定义类型转换
//						return DbColumnType.INTEGER;
//					}
//					return typeRegistry.getColumnType(metaInfo);
//
//				}))
				.packageConfig(builder -> {
					builder.parent("com.example") // 设置父包名
							.moduleName(moduleName) // 设置父包模块名
							.pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
				})
				.strategyConfig(builder -> {
					builder.addInclude(tables); // 设置需要生成的表名
							//.addTablePrefix("x_"); // 设置过滤表前缀
				})
				.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
				.execute();
	}

}
