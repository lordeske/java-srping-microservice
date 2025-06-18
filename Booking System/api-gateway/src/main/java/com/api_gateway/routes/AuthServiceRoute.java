package com.api_gateway.routes;


import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class AuthServiceRoute {

    @Bean
    public RouterFunction<ServerResponse> authRoutes() {
        return GatewayRouterFunctions.route("auth-service")
                .nest(RequestPredicates.path("/api/v1/auth"),
                        builder -> builder
                                .route(RequestPredicates.POST("/register"),
                                        HandlerFunctions.http("http://localhost:8086/api/v1/auth/register"))
                                .route(RequestPredicates.POST("/login"),
                                        HandlerFunctions.http("http://localhost:8086/api/v1/auth/login"))
                )
                .build();
    }
}