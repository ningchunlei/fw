package com.sqsd.framework.web.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015/4/30.
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestIdFilterConfig {
    @Value("${web.idFilter:/*}")
    private String urlPattern;

    @Bean
    public FilterRegistrationBean globalTrackIdFilterBean() {
        RequestIdFilter filter = new RequestIdFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        List<String> urlPatterns = Arrays.asList(urlPattern.split(";"));
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
