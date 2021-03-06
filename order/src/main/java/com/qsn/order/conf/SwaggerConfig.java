package com.qsn.order.conf;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 配置Swagger主页信息
 * 需要 knife4j 的权限控制（账号密码） 才需要引用此包:  @Import(BeanValidatorPluginsConfiguration.class), 对应jar：spring-boot-starter-validation
 *
 * @author qiusn
 * @date 2021-03-05
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {


    /**
     * 创建RestApi 并包扫描controller
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qsn.order"))
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
                title("订单模块").
                contact("小凡").
                version("1.0 version").
                termsOfServiceUrl("api/order/**").
                description("订单模块-这是一个 cloud+nacos+gateway+knife4j 的项目")
                .build();
    }

}