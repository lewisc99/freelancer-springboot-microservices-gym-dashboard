package com.lewis.msapigateway.config;

import com.lewis.msapigateway.filters.AuthenticationFilter;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.client.HttpClient;

@Configuration
public class ApiGatewayConfiguration {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder)
    {
        return builder.routes()
                .route("ms-authentication", r -> r.path("/ms-auth/**").filters( f -> f.stripPrefix(1))
                        .uri("lb://ms-authentication"))

                .route("ms-employee", r -> r.path("/ms-employee/**")
                        .filters(f -> f.stripPrefix(1).filter(filter))
                        .uri("lb://ms-employee"))
                .build();
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
    }

}
