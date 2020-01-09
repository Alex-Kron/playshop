package com.playshop.web.filter;

import com.playshop.entity.Person;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UsersFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("person") != null) {
            Person person = (Person) session.getAttribute("person");
            if (person.getRole().equals("admin")){
                chain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("/signup").forward(request,response);
            }
        } else {
            request.getRequestDispatcher("/signup").forward(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
