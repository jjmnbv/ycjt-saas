package com.beitu.saas.rest.config;

import com.beitu.saas.common.config.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaochong
 * @Create 2017/9/11 下午7:27
 * @Description
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private ConfigUtil configUtil;

    @Bean
    public Docket ycjtApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("accessToken").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2).enable(configUtil.getSwaggerEnable())
                .groupName("message")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.beitu.saas.rest.controller"))// 选择那些路径和api会生成document
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("贝途-SaaS-API文档")
                .description("洋葱借条想借就借")
                .termsOfServiceUrl("http://yangcongjietiao.com/")
                .contact(new Contact("xiaochong", "chong.xiao@beitu-inc.com", "chong.xiao@beitu-inc.com"))
                .version("1.3")
                .build();
    }

}
