package com.sai.Uniblox.launcher;

import com.sai.Uniblox.entity.Coupon;
import com.sai.Uniblox.entity.Product;
import com.sai.Uniblox.entity.User;
import com.sai.Uniblox.repository.CouponRepository;
import com.sai.Uniblox.repository.ProductRepository;
import com.sai.Uniblox.repository.UserRepository;
import com.sai.Uniblox.utils.AuthorizationHeaderFilter;
import com.sai.Uniblox.utils.CouponDataLoader;
import com.sai.Uniblox.utils.DataLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.sai.Uniblox.constants.ApiConstants.API_TOKEN;

@Configuration
@EnableSwagger2
public class UnibloxLauncher {

    @Bean
    public WebMvcConfigurer configureCORS() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000");
            }
        };
    }

    @Bean
    public Docket api() {
        List<Parameter> globalParameters = new ArrayList<>();
        globalParameters.add(new ParameterBuilder()
                .name("Authorization")
                .description("Bearer {API_TOKEN}")
                .required(true)
                .defaultValue(String.format("Bearer %s", API_TOKEN))
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

    @Bean
    public CommandLineRunner loadData(CouponRepository couponRepository,
                                      ProductRepository productRepository,
                                      UserRepository userRepository) {
        return args -> {
            List<Coupon> coupons = couponRepository.findAll();
            List<Product> products = productRepository.findAll();
            List<User> users = userRepository.findAll();

            if (coupons.isEmpty()) {
                coupons = CouponDataLoader.buildCouponData();
                for (Coupon coupon : coupons) {
                    couponRepository.save(coupon);
                }
            }
            if (products.isEmpty()) {
                products = DataLoader.readDataFromFile("products", Product.class);
                for (Product product : products) {
                    productRepository.save(product);
                }
            }
            if (users.isEmpty()) {
                users = DataLoader.readDataFromFile("users", User.class);
                for (User user : users) {
                    userRepository.save(user);
                }
            }
        };
    }
}
