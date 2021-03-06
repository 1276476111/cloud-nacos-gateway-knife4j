# spring cloud + nacos +gateway + knife4j

## 这是一个微服务聚合文档
项目文档访问地址： http://localhost:8333/doc.html
## 什么是knife4j？
knife4j 就是 swagger的升级版， 除了美化了swagger的界面。而且还有其他的增强功能<br>
 
## 增强功能有哪些？
- tags分组标签排序、api接口排序、markdown文档下载、权限控制

## 注意
- 聚合服务的文档需要用到gateway，所以想搭建聚合服务文档应先搭建网关
- 一个版本的 knife4j 有一种配置方法， 不可将不同版本knife4j的配置方式混在一起
- 使用排序时，需要先在文档页面进行设置： **访问文档访问地址->文档管理->个性化设置->将**“启用Knife4j提供的增强功能”**勾选即可**
- 使用权限控制时， 网关不需要单独配置yml文件。但是需要权限控制的服务需要用到 yml 的文件配置
- gateway是根据配置的路由去映射文档的。 请不要忘记添加映射的路由

##无论是网关还是其他服务，都引用如下maven
```
        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-boot-starter</artifactId>
        </dependency>
```
## 网关配置
```

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 在集成Spring Cloud Gateway网关的时候,会出现没有basePath的情况(即定义的例如/user、/order等微服务的前缀),
 *
 * 这个情况在使用zuul网关的时候不会出现此问题,因此,在Gateway网关需要添加一个Filter实体Bean
 *
 * @author qiusn
 * @date 2021-03-04
 */
@Component
public class SwaggerProvider implements SwaggerResourcesProvider {
    /**
     * 接口地址
     */
    public static final String API_URI = "/v2/api-docs";

    /**
     * 路由加载器
     */
    @Autowired
    private RouteLocator routeLocator;

    /**
     * 网关应用名称
     */
    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public List<SwaggerResource> get() {
        //接口资源列表
        List<SwaggerResource> resources = new ArrayList<>();
        //服务名称列表
        List<String> routeHosts = new ArrayList<>();
        // 获取所有可用的应用名称
        routeLocator.getRoutes().filter(route -> route.getUri().getHost() != null)
                .filter(route -> !applicationName.equals(route.getUri().getHost()))
                .subscribe(route -> routeHosts.add(route.getUri().getHost()));
        // 去重，多负载服务只添加一次
        Set<String> existsServer = new HashSet<>();
        routeHosts.forEach(host -> {
            // 拼接url
            String url = "/" + host + API_URI;
            //不存在则添加
            if (!existsServer.contains(url)) {
                existsServer.add(url);
                SwaggerResource swaggerResource = new SwaggerResource();
                swaggerResource.setUrl(url);
                swaggerResource.setName(host);
                resources.add(swaggerResource);
            }
        });
        return resources;
    }

}
```
```
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import java.util.Optional;

/**
 * swagger访问接口
 *
 * @author qiusn
 * @date 2021-03-04
 **/
@RestController
@RequestMapping("/swagger-resources")
public class SwaggerHandler {

    /**
     * 权限配置
     */
    @Autowired(required = false)
    private SecurityConfiguration securityConfiguration;

    @Autowired(required = false)
    private UiConfiguration uiConfiguration;

    private final SwaggerResourcesProvider swaggerResources;

    @Autowired
    public SwaggerHandler(SwaggerResourcesProvider swaggerResources) {
        this.swaggerResources = swaggerResources;
    }


    @GetMapping("/configuration/security")
    public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration() {
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()), HttpStatus.OK));
    }

    @GetMapping("/configuration/ui")
    public Mono<ResponseEntity<UiConfiguration>> uiConfiguration() {
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()), HttpStatus.OK));
    }

    /**
     * 获取接口信息
     */
    @GetMapping("")
    public Mono<ResponseEntity> swaggerResources() {
        return Mono.just((new ResponseEntity<>(swaggerResources.get(), HttpStatus.OK)));
    }
}
```
## 网关.yml文件
```
server:
  port: 8333

spring:
  application:
    name: gateway-service
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true # 启用自动根据服务ID生成路由
          lower-case-service-id: true # 设置路由的路径为小写的服务ID
      routes:
        - id: order-service
          uri: lb://order-service
          predicates:
            - Path=/api/order/**
        - id: user-service
          uri: lb://user-service
          predicates:
            - Path=/api/user/**
```

## 其他服务配置
```
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
                description("用户模块-这是一个 cloud+nacos+gateway+knife4j 的项目")
                .build();
    }

}
```
## 其他服务.yml文件
```
knife4j:
  # 开启Swagger的Basic认证功能,默认是false
  # 注：（1）默认账号/密码  admin/123321; （2）但是如果不配置密码。 即使输入对了，也始终在输入密码的地方重新循环;（3）如果用浏览器记住密码了则不用输入， swagger会直接读取进去不会再手动输入一次；
  basic:
    enable: true
    # Basic认证用户名
    username: test
    # Basic认证密码
    password: 1234567
## 开启生产环境屏蔽(true看不到文档；false可以看到文档，但是密码失效)
#  production: false
```

