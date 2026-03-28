package com.gateway.demo;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.stripPrefix;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    RouterFunction<ServerResponse> gatewayRoutes() {
        return route("java1_route")
                .route(path("/Java1/api/**"), http())
                .before(uri("http://localhost:8081"))
                .before(stripPrefix(1))
                .build()
                .and(
                        route("java2_route")
                                .route(path("/Java2/api/**"), http())
                                .before(uri("http://localhost:8082"))
                                .before(stripPrefix(1))
                                .build()
                )
                .and(
                        route("java3_route")
                                .route(path("/Java3/api/**"), http())
                                .before(uri("http://localhost:8083"))
                                .before(stripPrefix(1))
                                .build()
                );
    }
}