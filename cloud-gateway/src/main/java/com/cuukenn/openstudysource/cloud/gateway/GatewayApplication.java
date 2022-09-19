package com.cuukenn.openstudysource.cloud.gateway;

import com.cuukenn.openstudysource.cloud.user.api.client.IUserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changgg
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = {IUserFeignClient.class})
@Slf4j
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters, RouteDefinitionLocator locator) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        if (definitions == null) {
            return groups;
        }
        definitions.stream().filter(routeDefinition -> {
            log.info("routeDefinition id:{} {}", routeDefinition.getId(), routeDefinition.getUri().toString());
            return routeDefinition.getId().matches(".*-service");
        }).forEach(routeDefinition -> {
            String name = routeDefinition.getId().replaceAll("ReactiveCompositeDiscoveryClient_", "");
            swaggerUiConfigParameters.addGroup(name);
            groups.add(
                GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build()
            );
        });
        return groups;
    }
}
