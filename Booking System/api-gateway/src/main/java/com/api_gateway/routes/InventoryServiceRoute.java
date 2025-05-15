package com.api_gateway.routes;


import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class InventoryServiceRoute {

    @Bean
    public RouterFunction<ServerResponse> inventoryRoutes() {
        return GatewayRouterFunctions.route("inventory-service")
                // venue by ID
                .route(RequestPredicates.path("/api/v1/inventory/venue/{venueId}"),
                        request -> forwardWithPathVariable(
                                request,
                                "venueId",
                                "http://localhost:8080/api/v1/inventory/venue/{venueId}")
                )

                // event by ID
                .route(RequestPredicates.path("/api/v1/inventory/events/{eventId}"),
                        request -> forwardWithPathVariable(
                                request,
                                "eventId",
                                "http://localhost:8080/api/v1/inventory/events/{eventId}")
                )

                .build();
    }


    @Bean
    public RouterFunction<ServerResponse> inventoryServiceApiDocs()
    {

        return GatewayRouterFunctions.route("inventory-service-api-docs")
                .route(RequestPredicates.path("/docs/inventoryservice/v3/api-docs"),
                        HandlerFunctions.http("http://localhost:8080"))
                .filter(setPath("/v3/api-docs"))
                .build();



    }



    private static ServerResponse forwardWithPathVariable(ServerRequest serverRequest,
                                                          String pathVariable,
                                                          String baseUrl) throws Exception {
        String value = serverRequest.pathVariable(pathVariable);
        String resolvedUrl = baseUrl.replace("{" + pathVariable + "}", value);
        return HandlerFunctions.http(resolvedUrl).handle(serverRequest);
    }



}
