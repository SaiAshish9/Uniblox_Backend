package com.sai.Uniblox.launcher;

import com.sai.Uniblox.entity.Coupon;
import com.sai.Uniblox.entity.Product;
import com.sai.Uniblox.entity.User;
import com.sai.Uniblox.repository.CouponRepository;
import com.sai.Uniblox.repository.ProductRepository;
import com.sai.Uniblox.repository.UserRepository;
import com.sai.Uniblox.utils.CouponDataLoader;
import com.sai.Uniblox.utils.DataLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UnibloxLauncher {

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
