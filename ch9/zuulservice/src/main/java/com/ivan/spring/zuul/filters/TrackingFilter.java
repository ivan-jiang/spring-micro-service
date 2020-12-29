package com.ivan.spring.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
@Slf4j
public class TrackingFilter extends ZuulFilter {
    private static final boolean SHOULD_FILTER = true;
    private static final int ORDER = 1;

    @Resource
    FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
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
        // CorrelationId为空，创建一个，设置到RequestContext
        if (filterUtils.getCorrelationId() == null) {
            String corrId = UUID.randomUUID().toString();
            filterUtils.setCorrelationId(corrId);
        }

        RequestContext context = RequestContext.getCurrentContext();
        log.info("uri:{}", context.getRequest().getRequestURI());
        return null;
    }
}
