package com.odin.odinbff.configuration.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter  implements Filter {

    @Value("${cors.origins}")
    private String corsOrigins;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request =  (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", corsOrigins);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization");
        response.setHeader("Access-Control-Expose-Headers", "Location");

        if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);

        } else {
            chain.doFilter(req, res);
        }
    }
    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}

}