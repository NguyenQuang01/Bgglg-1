package com.example.itspower.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Power2SME Pvt. Ltd.", "http://www.power2sme.com", "support@power2sme.com");
        return new ApiInfo("NBFC API", "Information related to api exposed by NBFC system.", "1.0",
                "https://www.power2sme.com/termsandconditions", contact, "License of API",
                "https://www.power2sme.com/privacypolicy", new ArrayList<>());
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/**/v2/api-docs/**", "/v2/api-docs");
        registry.addRedirectViewController("/**/swagger-resources/configuration/ui/**", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/**/swagger-resources/configuration/security/**", "/swagger-resources/configuration/security");
        registry.addRedirectViewController("/**/swagger-resources/**", "/swagger-resources");
    }
}
