package com.ivan.spring.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class ResponseFilter extends ZuulFilter {
    private static final boolean SHOULD_FILTER = true;
    private static final int ORDER = 1;
    @Resource
    Tracer tracer;

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.getResponse().addHeader("tmx-correlation-id", tracer.getCurrentSpan().traceIdString());
        return null;
    }
}
