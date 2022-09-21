package com.cuukenn.cloud.auth;

import com.cuukenn.openstudysource.cloud.user.api.client.UserFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author changgg
 */
@SpringBootApplication
@EnableFeignClients(clients = UserFeignClient.class)
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
