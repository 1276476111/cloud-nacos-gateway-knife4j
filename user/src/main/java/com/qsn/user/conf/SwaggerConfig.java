package com.qsn.user.conf;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 配置Swagger主页信息
 *
 * @author qiusn
 * @date 2021-03-05
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {


    /**
     * 创建RestApi 并包扫描controller
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qsn.user"))
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    /**
     * 创建Swagger页面 信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().
                title("用户模块").
                contact("小凡").
                version("1.0 version").
                termsOfServiceUrl("api/user/**").
                description("用户模块-这是一个 cloud+nacos+gateway+knife4j 的项目")
                .build();
    }

}