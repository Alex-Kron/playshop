package com.playshop.web;

import com.playshop.dao.PersonDAO;
import com.playshop.entity.Item;
import com.playshop.entity.Person;
import com.playshop.exceptions.DBException;
import com.playshop.exceptions.ServiceException;
import com.playshop.services.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Person person = (Person) session.getAttribute("person");
        String action = request.getParameter("action");
        AdminService adminService = new AdminServiceImplements();
        if (action == null) {
            try {
                request.setAttribute("users", adminService.getAllPerson());
                request.getRequestDispatcher("/user_list.jsp").forward(request, response);
            } catch (DBException e) {
                response.sendRedirect("error");
            }
            return;
        }

        String username = request.getParameter("name");

        if (action.equalsIgnoreCase("delete") && username != null) {
            try {
                adminService.deletePerson(adminService.getPersonId(username));
                request.setAttribute("users", adminService.getAllPerson());
                request.getRequestDispatcher("/user_list.jsp").forward(request, response);
            } catch (DBException e) {
                response.sendRedirect("error");
            }
        }

        if (action.equalsIgnoreCase("change") && username != null) {
            try {
                if (adminService.getPerson(adminService.getPersonId(username)).getRole().equals("admin")) {
                    adminService.setUser(adminService.getPersonId(username));
                } else {
                    if (adminService.getPerson(adminService.getPersonId(username)).getRole().equals("user")) {
                        adminService.setAdmin(adminService.getPersonId(username));
                    }
                }
            } catch (DBException e) {
                response.sendRedirect("error");
            }
        }
    }
}
