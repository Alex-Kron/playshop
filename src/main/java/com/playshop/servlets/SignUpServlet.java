package com.playshop.servlets;

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
            request.setAttribute("exception", "Not registered");
            request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
            return;
        }

        PersonService personService = new PersonServiceImplements();

        assert personService != null;
        Person person;
        try{
            person = personService.signUp(login, password);
        } catch (DBException e){
            person = null;
        }

        if (person != null) {
            request.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(request, response);
        } else {
            request.setAttribute("exception", "Not registered");
            request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
    }
}
