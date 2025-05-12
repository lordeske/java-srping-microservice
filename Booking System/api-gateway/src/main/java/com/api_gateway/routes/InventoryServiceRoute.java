package com.api_gateway.routes;


import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class InventoryServiceRoute {

    public RouterFunction<ServerResponse> inventoryRoutes()
    {
        //// TO DO
        return GatewayRouterFunctions.route("inventory-service")
                .route(RequestPredicates.path("/api/v1/inventory/venue/{venueId}"),
                        request -> (request, "venueId", forwardWithPathVariable "https://localhost:8080/api/v1/inventory/venue/{venueId}"))


                .route(RequestPredicates.path("/api/v1/inventory/event/{eventId}"),
            request -> (request, "eventId", "https://localhost:8080/api/v1/inventory/event/{event}"))





    }


}
