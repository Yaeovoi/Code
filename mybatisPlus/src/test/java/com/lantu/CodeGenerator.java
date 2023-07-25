package com.lantu;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
	public static void main(String[] args) {
		FastAutoGenerator.create("jdbc:mysql://localhost:3306/mpdb", "root", "123456")
				.globalConfig(builder -> {
					builder.author("LiangZhaoquan") // 设置作者
							//.enableSwagger() // 开启 swagger 模式
							//.fileOverride() // 覆盖已生成文件
							.outputDir("F:\\SpringBootCode\\mybatisPlus\\src\\main\\java"); // 指定输出目录
				})
				.packageConfig(builder -> {
					builder.parent("com.lantu") // 设置父包名
							.moduleName("user") // 设置父包模块名
							.pathInfo(Collections.singletonMap(OutputFile.xml, "F:\\SpringBootCode\\mybatisPlus\\src\\main\\resources\\mapper\\user" )); // 设置mapperXml生成路径
				})
				.strategyConfig(builder -> {
					builder.addInclude("user","dept") // 设置需要生成的表名
							.addTablePrefix(""); // 设置过滤表前缀
				})
				.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
				.execute();

	}
}
