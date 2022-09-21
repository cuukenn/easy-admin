package com.cuukenn.openstudysource.cloud;

import com.cuukenn.openstudysource.cloud.framework.auth.AuthAutoconfiguration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author changgg
 */
@SpringBootApplication(exclude = AuthAutoconfiguration.class)
@EnableDiscoveryClient
@OpenAPIDefinition(info =
@Info(title = "Department API", version = "${springdoc.version}", description = "Documentation User API")
)
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
