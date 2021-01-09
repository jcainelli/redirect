package com.jcainelli.redirect.config.swagger;

import com.jcainelli.redirect.util.RedirectConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .tags(new Tag("/", "Raiz"), new Tag(RedirectConstants.REST_END_POINT_REDIRECT, "Informações sobre os serviços"))
            .select()
            .apis(RequestHandlerSelectors
                .basePackage("com.jcainelli.redirect.controller"))
            .paths(PathSelectors.regex("/.*"))

            .build().apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Encurtador de Url - API")
            .description("Serviço para encurtar e atender requisições em urls encurtadas (redirecionamento).")
            .contact(new Contact("Jean Cainelli","http://github.com/jcainelli","jcainelli@gmail.com"))
            .version("1.0.0")
            .build();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/doc/v2/api-docs", "/v2/api-docs").setKeepQueryParams(true);
        registry.addRedirectViewController("/doc/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/doc/swagger-resources/configuration/security",
            "/swagger-resources/configuration/security");
        registry.addRedirectViewController("/doc/swagger-resources", "/swagger-resources");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc/**").addResourceLocations("classpath:/META-INF/resources/");
    }

}
