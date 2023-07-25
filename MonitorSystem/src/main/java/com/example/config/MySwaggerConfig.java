package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableOpenApi
@EnableWebMvc
//配置Swagger生成的API文档信息，包括基本信息、安全方案、安全上下文
//生成符合OpenAPI 3.0规范的API文档，并提供良好的接口描述和调试工具
public class MySwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30)

				//apiInfo方法设置API文档的基本信息，包括标题、描述、版本和联系人等
				.apiInfo(apiInfo())

				//选择哪些API接口会生成文档
				.select()

				//过滤出基准包路径下的所有接口
				.apis(RequestHandlerSelectors.basePackage("com.example"))

				//paths方法设置了生成文档的条件，
				//这里使用PathSelectors.any方法表示所有路径都生成文档
				.paths(PathSelectors.any())

				//调用build方法构建Docket对象
				.build()

				//securitySchemes方法设置了安全方案 用于身份验证
				.securitySchemes(Collections.singletonList(securityScheme()))

				//securityContexts方法设置了安全上下文
				//设置了安全参考和路径过滤规则，以限制对于"/auth"路径的访问不需要身份验证
				.securityContexts(Collections.singletonList(securityContext()));
	}

	//通过ApiKey构造函数创建了一个ApiKey对象，用于身份验证。这里指定了头部名称为"X-Token"
	private SecurityScheme securityScheme() {
		//return new ApiKey("Authorization", "Authorization", "header");
		return new ApiKey("X-Token", "X-Token", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()

				//使用defaultAuth方法返回的安全参考对象
				.securityReferences(defaultAuth())

				//通过PathSelectors.regex方法设置了路径过滤规则，排除了以"/auth"开头的路径
				.forPaths(PathSelectors.regex("^(?!auth).*$"))

				.build();
	}

	//定义了私有方法defaultAuth，返回一个安全参考列表
	private List<SecurityReference> defaultAuth() {
		//创建了一个AuthorizationScope对象，表示授权范围为"global"，描述为"accessEverything"
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;

		return Collections.singletonList(
				new SecurityReference("X-Token", authorizationScopes));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("莜莜私人专属接口文档")
				.description("SpringBoot+Vue")
				.version("1.0")
				.contact(new Contact("qqcn", "http://www.qqcn.cn", "qqcn@aliyun.com"))
				.build();
	}
}

