package com.playshop.web;

import com.playshop.exceptions.DBException;
import com.playshop.services.AdminService;
import com.playshop.services.AdminServiceImplements;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PurchaseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            AdminService adminService = new AdminServiceImplements();
            try {
                request.setAttribute("purchases", adminService.getPurchases());
                request.getRequestDispatcher("/view_purchases.jsp").forward(request, response);
            } catch (DBException e) {
                response.sendRedirect("error");
            }
        }
    }
}
