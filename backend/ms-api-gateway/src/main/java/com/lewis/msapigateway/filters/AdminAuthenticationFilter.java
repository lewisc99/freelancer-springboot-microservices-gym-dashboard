package com.lewis.msapigateway.filters;

import com.lewis.msapigateway.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Component
@RefreshScope
public class AdminAuthenticationFilter implements GatewayFilter {



    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            List<String> bearer = new ArrayList<>();
            bearer = exchange.getRequest().getHeaders().get("Authorization");

            if(bearer != null) {
                String jwt = bearer.get(0).substring(7);

                Map<String, List<String>> claims =
                        jwtUtil.validateTokenAndRetrieveSubject(jwt);

                if (claims.get("invalid-token").size() == 0) {
                    List<String> getRoles = new ArrayList<String>();
                    getRoles = claims.get("roles");

                    var result = getRoles.stream().filter(f -> {
                        var admin = f.contains("admin");
                        var manager = f.contains("manager");
                        return manager || admin;
                    }).collect(Collectors.toList());

                    if (!result.isEmpty()) {
                        return chain.filter(exchange);

                    } else {
                        return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);

                    }
                } else {
                    return onError(exchange, "No authorization in header", HttpStatus.UNAUTHORIZED);

                }
            }
        return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String error, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }

}