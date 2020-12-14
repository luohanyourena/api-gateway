package com.letter.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * //权限校验
 * create:luohan
 */
@Component
public class AuthbuyerFilter extends ZuulFilter {
    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/order/order/create")){
            return true;
        }
        return false;
    }

    @Override
    public Object run() {
        //校验各接口访问的权限
        //order/create  只能买家访问
        //order/finish  只能卖家访问
        //product/list  都能访问
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        Cookie[] cookies = request.getCookies();

                String openid ="";
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("openid")){
                        openid = cookie.getValue();
                    }
                }
                if (StringUtils.isBlank(openid)){
                    currentContext.setSendZuulResponse(false);
                    currentContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
                }

//        }else{
//            currentContext.setSendZuulResponse(false);
//            currentContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
//        }


        return null;
    }
}
