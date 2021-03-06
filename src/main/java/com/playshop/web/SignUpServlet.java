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

public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retype_password");

        if (login == null || password == null || retypePassword == null
                || login.isEmpty() || password.isEmpty() || retypePassword.isEmpty()
                || !password.equals(retypePassword)){
            request.setAttribute("exception", "Please check that all fields are filled in correctly and resend.");
            this.getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
            return;
        }

        PersonService personService = new PersonServiceImplements();
        Person person;
        try{
            person = personService.signUp(login, password);
        } catch (DBException e){
            person = null;
        }

        if (person != null) {
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
        } else {
            request.setAttribute("exception", "Registration error. Contact administrator.");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/signup.jsp").forward(request, response);
    }
}

