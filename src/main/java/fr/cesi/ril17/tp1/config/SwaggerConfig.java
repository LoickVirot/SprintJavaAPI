package fr.cesi.ril17.tp1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("fr.cesi.ril17.tp1.controllers.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(buildApiInfo())
                ;
    }

    public ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("TP1 Api Specifications")
                .description("Endpoints of Spring TP1 Project")
                .version("1.0")
                .build()
                ;
    }
}