package com.mawen.learn.springcloud;


import com.mawen.learn.springcloud.filter.AdditionalRoutes;
import com.mawen.learn.springcloud.filter.ThrottleGatewayFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

@EnableDiscoveryClient
@SpringBootApplication
@Import(AdditionalRoutes.class)
public class GatewayApp {

    public static final String HELLO_FROM_FAKE_ACTUATOR_METRICS_GATEWAY_REQUESTS = "hello from fake /actuator/metrics/spring.cloud.gateway.requests";

    @Value("${test.uri:http://httpbin.org:80}")
    String uri;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class, args);
    }

//    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.host("**.abc.org")
                        .and()
                        .path("/hello")
                        .filters(f -> f.prefixPath("/httpbin")
                                .addResponseHeader("X-TestHeader", "foobar"))
                        .uri(uri)
                )
                .route("read_body_pred", r -> r.host("*.readbody.org")
                        .and()
                        .readBody(String.class, s -> s.trim().equalsIgnoreCase("hi"))
                        .filters(f -> f.prefixPath("/httpbin")
                                .addResponseHeader("X-TestHeader", "read_body_pred"))
                        .uri(uri)
                )
                .route("rewrite_request_obj", r -> r.host("*.rewriterequestobj.org")
                        .filters(f -> f.prefixPath("/httpbin")
                                .addResponseHeader("X-TestHeader", "rewrite_request")
                                .modifyRequestBody(String.class, Hello.class, MediaType.APPLICATION_JSON_VALUE, (exchange, s) -> Mono.just(new Hello(s.toLowerCase(Locale.ROOT)))))
                        .uri(uri)
                )
                .route("rewrite_request_upper", r -> r.host("*.rewriterequestupper.org")
                        .filters(f -> f.prefixPath("/httpbin")
                                .addResponseHeader("X-TestHeader", "rewrite_request_upper")
                                .modifyRequestBody(String.class, String.class, (exchange, s) -> {
                                    return Mono.just(s.toUpperCase(Locale.ROOT) + s.toLowerCase(Locale.ROOT));
                                }))
                        .uri(uri)
                )
                .route("rewrite_response_upper", r -> r.host("*.rewriteresponseupper.org")
                        .filters(f -> f.prefixPath("/httpbin")
                                .addResponseHeader("X-TestHeader", "rewrite_response_upper")
                                .modifyResponseBody(String.class, String.class, (exchange, s) -> {
                                    return Mono.just(s.toUpperCase(Locale.ROOT) + s.toLowerCase(Locale.ROOT));
                                }))
                        .uri(uri)
                )
                .route("rewrite_empty_response", r -> r.host("*.rewriteemptyresponse.org")
                        .filters(f -> f.prefixPath("/httpbin")
                                .addResponseHeader("X-TestHeader", "rewrite_empty_response")
                                .modifyResponseBody(String.class, String.class, (exchange, s) -> {
                                    if (s == null) {
                                        return Mono.just("emptybody");
                                    }
                                    return Mono.just(s.toUpperCase(Locale.ROOT));
                                }))
                        .uri(uri)
                )
                .route("rewrite_response_fail_supplier", r -> r.host("*.rewriterresponsefailsupplier.org")
                        .filters(f -> f.prefixPath("/httpbin")
                                .addResponseHeader("X-TestHeader", "rewrite_response_fail_supplier")
                                .modifyResponseBody(String.class, String.class, (exchange, s) -> {
                                    if (s == null) {
                                        return Mono.error(new IllegalArgumentException("this should not happend"));
                                    }
                                    return Mono.just(s.toUpperCase(Locale.ROOT));
                                }))
                        .uri(uri)
                )
                .route(r -> r.path("/image/webp")
                        .filters(f -> f.prefixPath("/httpbin")
                                .addResponseHeader("X-AnotherHeader", "baz"))
                        .uri(uri)
                )
                .route(r -> r.order(-1)
                        .host("**.throttle.org")
                        .and()
                        .path("/get")
                        .filters(f -> f.prefixPath("/httpbin")
                                .filter(new ThrottleGatewayFilter()
                                        .setCapacity(1)
                                        .setRefillTokens(1)
                                        .setRefillPeriod(10)
                                        .setRefillUnit(TimeUnit.SECONDS)))
                        .uri(uri)
                )
                .build();
    }

//    @Bean
    public RouterFunction<ServerResponse> testFunRouterFunction() {
        return RouterFunctions.route(RequestPredicates.path("/testfun"),
                request -> ServerResponse.ok().body(BodyInserters.fromValue("hello")));
    }

//    @Bean
    public RouterFunction<ServerResponse> testWhenMetricPathIfNotMeet() {
        return RouterFunctions.route(RequestPredicates.path("/actuatpr/metrics/spring.cloud.gateway.requests"),
                request -> ServerResponse.ok().body(BodyInserters.fromValue(HELLO_FROM_FAKE_ACTUATOR_METRICS_GATEWAY_REQUESTS)));
    }

    static class Hello {
        String message;

        Hello() {
        }

        Hello(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
