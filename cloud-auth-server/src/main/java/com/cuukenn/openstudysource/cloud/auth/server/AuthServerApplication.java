package com.cuukenn.openstudysource.cloud.auth.server;

import com.cuukenn.openstudysource.cloud.user.api.client.IUserFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author changgg
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = IUserFeignClient.class)
public class AuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
}
