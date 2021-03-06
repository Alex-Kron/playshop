package com.playshop.web;

import com.playshop.entity.Person;
import com.playshop.exceptions.DBException;
import com.playshop.services.PersonService;
import com.playshop.services.PersonServiceImplements;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null || login.isEmpty() || password.isEmpty()){
            request.setAttribute("exception", "Please check that all fields are filled in correctly and resend.");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
            return;
        }

        PersonService personService = new PersonServiceImplements();

        Person person;
        try{
            person = personService.signIn(login, password);
        } catch (DBException e){
            person = null;
        }

        if (person != null) {
            request.getSession(true).setAttribute("person", person);
            response.sendRedirect("items");
        } else {
            request.setAttribute("exception", "The entered data is not correct.");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/signin.jsp").forward(request, response);
    }
}

