package me.mocha.appjam.config;

import me.mocha.appjam.model.entiity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("me.mocha.appjam.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .ignoredParameterTypes(User.class);
    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Appjam API",
                " Smart school 구축을 위한 API",
                "0.0.1-SNAPSHOT",
                "",
                new Contact("hwangseonu", "https://github.com/hwangseonu", "hwangseonu12@naver.com"),
                "MIT",
                "https://github.com/DSM-ESC/appjam-backend/blob/master/LICENSE",
                Collections.emptyList()
        );
    }

}
