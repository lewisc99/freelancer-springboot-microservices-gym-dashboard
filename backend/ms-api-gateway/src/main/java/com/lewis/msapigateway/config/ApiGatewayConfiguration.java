package com.lewis.msapigateway.config;

import com.lewis.msapigateway.filters.AdminAuthenticationFilter;
import com.lewis.msapigateway.filters.RolesAuthenticationFilter;
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
    AdminAuthenticationFilter adminFilter;

    @Autowired
    RolesAuthenticationFilter rolesFilter;

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder)
    {
        return builder.routes()
                .route("ms-authentication", r -> r.path("/ms-auth/**").filters( f -> f.stripPrefix(1))
                        .uri("lb://ms-authentication"))

                .route("ms-employee", r -> r.path("/ms-employee/**")
                        .filters(f -> f.stripPrefix(1).filter(adminFilter))
                        .uri("lb://ms-employee"))

                .route("ms-employee", r -> r.path("/ms-user/**")
                        .filters(f -> f.stripPrefix(1).filter(rolesFilter))
                        .uri("lb://ms-user"))
                .build();
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
    }

}
