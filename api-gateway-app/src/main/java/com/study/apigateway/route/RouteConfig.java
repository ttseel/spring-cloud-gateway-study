package com.study.apigateway.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    public static final String MICROSERVICE_HOST = "http://localhost:8080";
    private static final String ECHO_HTTP_BIN = "http://httpbin.org"; // 내가 보낸 HTTP Request를 그대로 Response로 주는 사이트. HTTP Request를 테스트할 때 유용하다

    @Bean
    public RouteLocator helloRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 예시1: 간단한 re-routing
                .route("path_simple_hello", // route id
                        r -> r.path("/hello") // Predicate 설정 : /hello의 경로로 들어왔을 때
                                .uri(MICROSERVICE_HOST) // MICROSERVICE_HOST로 re-routing
                )
                // 예시2: Filter를 이용해 route를 변경 후 re-routing
                .route("rewrite_route",
                        r -> r.path("/gateway-hello")
                                .filters(f -> f.rewritePath("/gateway-hello", "/microservice-hello")) // "/gateway-hello"를 "/microservice-hello"로 변경
                                .uri(MICROSERVICE_HOST)
                )
                // 예시3: 헤더를 추가해서 re-routing
                .route("add-header-route",
                        r -> r.path("/get")
                                .filters(f -> f.addRequestHeader("role", "hello-api")) // 헤더 추가 : key=role, value=hello-api
                                .uri(ECHO_HTTP_BIN)
                )
                .build();
    }
}


