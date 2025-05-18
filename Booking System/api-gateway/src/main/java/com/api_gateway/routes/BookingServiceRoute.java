package com.api_gateway.routes;


import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class BookingServiceRoute {


    @Bean
    public RouterFunction<ServerResponse> bookingRoutes() {
        return GatewayRouterFunctions.route("booking-service")
                .nest(RequestPredicates.path("/api/v1/booking"),
                        builder -> builder
                                .route(RequestPredicates.POST(""),
                                        HandlerFunctions.http("http://localhost:8081/api/v1/booking"))
                                .route(RequestPredicates.GET("/{id}"),
                                        request -> {
                                            String id = request.pathVariable("id");
                                            return HandlerFunctions.http(
                                                    URI.create("http://localhost:8081/api/v1/booking/" + id)
                                            ).handle(request);
                                        })
                                .filter(CircuitBreakerFilterFunctions.circuitBreaker(
                                        "bookingServiceCircuitBreaker",
                                        URI.create("forward:/fallbackRoute")
                                ))
                )
                .build();
    }





    @Bean
    public RouterFunction<ServerResponse> bookingServiceApiDocs()
    {

        return GatewayRouterFunctions.route("booking-service-api-docs")
                .route(RequestPredicates.path("/docs/bookingservice/v3/api-docs"),
                        HandlerFunctions.http("http://localhost:8081"))
                .filter(setPath("/v3/api-docs"))
                .build();



    }





    @Bean
    public RouterFunction<ServerResponse> fallbackRoute()
    {

        return GatewayRouterFunctions.route("fallbackRoute")
                .POST("/fallbackRoute",
                        request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body("Booking Service je ugasen"))
                .build();

    }






}
