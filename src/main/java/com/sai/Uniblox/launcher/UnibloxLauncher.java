package com.sai.Uniblox.launcher;

import com.sai.Uniblox.utils.AuthorizationHeaderFilter;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import java.util.ArrayList;
import java.util.List;

import static com.sai.Uniblox.constants.ApiConstants.TOKEN;

@Configuration
@EnableSwagger2
public class UnibloxLauncher extends SpringBootServletInitializer {

    @Bean
    public WebMvcConfigurer configureCORS() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }

    @Bean
    public Docket api() {
        List<Parameter> globalParameters = new ArrayList<>();
        globalParameters.add(new ParameterBuilder()
                .name("Authorization")
                .description("Bearer {TOKEN}")
                .required(false)
                .defaultValue(String.format("Bearer %s", TOKEN))
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(globalParameters)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

    @Bean
    public FilterRegistrationBean<AuthorizationHeaderFilter> loggingFilter() {
        FilterRegistrationBean<AuthorizationHeaderFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationHeaderFilter());
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }

//    @Bean
//    public WebClient.Builder webClientBuilder() {
//        return WebClient.builder()
//                .defaultHeader("Authorization", "Bearer your_access_token");
//    }

}
