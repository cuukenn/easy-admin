package com.cuukenn.cloud.auth;

import com.cuukenn.openstudysource.cloud.framework.auth.AuthAutoconfiguration;
import com.cuukenn.openstudysource.cloud.user.api.client.UserFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author changgg
 */
@SpringBootApplication(exclude = AuthAutoconfiguration.class)
@EnableFeignClients(clients = UserFeignClient.class)
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
