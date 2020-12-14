package com.letter.apigateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.letter.apigateway.exception.RatelimitExecption;
import com.netflix.zuul.ZuulFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 限流
 * create:luohan
 */
@Component
public class RatelimitFilter extends ZuulFilter {
    private  static  final RateLimiter LIMITER =RateLimiter.create(100);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        if (!LIMITER.tryAcquire()){
            throw new RatelimitExecption();
        }
        return null;
    }
}
