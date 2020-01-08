package com.playshop.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class ErrorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void destroy() {
    }
}


