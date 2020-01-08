package com.playshop.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignUpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("person") != null) {
            chain.doFilter(request, response);
        } else if (req.getParameter("retype_password") != null){
            request.getRequestDispatcher("/signup").forward(request, response);
        } else {
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void destroy(){
    }
}


