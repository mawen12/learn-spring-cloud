package com.mawen.learn.springcloud.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.isomorphism.util.TokenBucket;
import org.isomorphism.util.TokenBuckets;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

public class ThrottleGatewayFilter implements GatewayFilter {

    private static final Log log = LogFactory.getLog(ThrottleGatewayFilter.class);

    private volatile TokenBucket tokenBucket;

    int capacity;

    int refillTokens;

    int refillPeriod;

    TimeUnit refillUnit;

    private TokenBucket getTokenBucket() {
        if (tokenBucket != null) {
            return tokenBucket;
        }

        synchronized (this) {
            if (tokenBucket == null) {
                tokenBucket = TokenBuckets.builder()
                        .withCapacity(capacity)
                        .withFixedIntervalRefillStrategy(refillTokens, refillPeriod, refillUnit)
                        .build();
            }
        }
        return tokenBucket;
    }

    public int getCapacity() {
        return capacity;
    }

    public ThrottleGatewayFilter setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public int getRefillTokens() {
        return refillTokens;
    }

    public ThrottleGatewayFilter setRefillTokens(int refillTokens) {
        this.refillTokens = refillTokens;
        return this;
    }

    public int getRefillPeriod() {
        return refillPeriod;
    }

    public ThrottleGatewayFilter setRefillPeriod(int refillPeriod) {
        this.refillPeriod = refillPeriod;
        return this;
    }

    public TimeUnit getRefillUnit() {
        return refillUnit;
    }

    public ThrottleGatewayFilter setRefillUnit(TimeUnit timeUnit) {
        this.refillUnit = timeUnit;
        return this;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        TokenBucket tokenBucket = getTokenBucket();

        log.debug("TokenBucket capacity: " + tokenBucket.getCapacity());
        // 获取令牌的密钥桶
        if (tokenBucket.tryConsume()) {
            return chain.filter(exchange);
        }

        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        return exchange.getResponse().setComplete();
    }
}
