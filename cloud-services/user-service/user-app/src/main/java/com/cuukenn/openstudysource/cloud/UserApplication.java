package com.cuukenn.openstudysource.cloud;

import com.cuukenn.openstudysource.cloud.framework.auth.AuthAutoconfiguration;
import com.cuukenn.openstudysource.cloud.framework.auth.SaTokenConfigure;
import com.cuukenn.openstudysource.cloud.framework.auth.StpInterfaceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * @author changgg
 */
@SpringBootApplication(exclude = AuthAutoconfiguration.class)
@EnableDiscoveryClient
@Import({SaTokenConfigure.class, StpInterfaceImpl.class})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
