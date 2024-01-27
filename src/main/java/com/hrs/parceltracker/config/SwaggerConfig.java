package com.hrs.parceltracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Refrence:
 * @Author: obed osei frimpong
 * @Date: 2024/01/26
 * @Modify:
 **/
@Configuration
public class SwaggerConfig{

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.hrs"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Parcel tracker service ")
                .description("Parcel Tracker service")
                .contact(new Contact("Obed","","obedosei@gmail.com"))
                .version("0.0.1")
                .build();
    }
}

