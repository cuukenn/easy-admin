package com.cuukenn.openstudysource.cloud.gateway;

import com.cuukenn.openstudysource.cloud.user.api.client.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.beans.factory.ObjectProvider;
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
@EnableFeignClients(clients = {UserFeignClient.class})
public class GatewayApplication {
    private static final Logger log = LoggerFactory.getLogger(GatewayApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(ObjectProvider<SwaggerUiConfigParameters> swaggerUiConfigParameters, RouteDefinitionLocator locator) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        swaggerUiConfigParameters.ifAvailable(config -> {
            List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
            if (definitions == null) {
                return;
            }
            definitions.stream().filter(routeDefinition -> {
                log.info("routeDefinition id:{} {}", routeDefinition.getId(), routeDefinition.getUri().toString());
                return routeDefinition.getId().matches(".*-service");
            }).forEach(routeDefinition -> {
                String name = routeDefinition.getId().replaceAll("ReactiveCompositeDiscoveryClient_", "");
                config.addGroup(name);
                groups.add(
                    GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build()
                );
            });
        });
        return groups;
    }
}
