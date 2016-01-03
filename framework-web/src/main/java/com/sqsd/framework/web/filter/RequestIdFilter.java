package com.sqsd.framework.web.filter;

import com.sqsd.framework.log.RequestId;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Administrator on 2015/4/30.
 */
public class RequestIdFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RequestId.createId();
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
