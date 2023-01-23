package com.lewis.msapigateway.config;


import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.client.HttpClient;


@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder)
    {
        return builder.routes()
                .route("ms-authentication", r -> r.path("/ms-auth/**").filters( f -> f.stripPrefix(1))
                        .uri("lb://ms-authentication"))
                .build();
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
    }

    @Bean
    public RouterFunction<ServerResponse> testWhenMetricPathIsNotMeet() {
        return RouterFunctions.route(
                RequestPredicates.path("ms-employee/**"),
                request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject("hello"))
        );
    }
}
