package com.playshop.web;

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

public class ItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            Item item = getItem(request);
            AdminService adminService = new AdminServiceImplements();
            int id = adminService.getItemId(item);
            if (id > 0) {
                adminService.updateItem(id, item);
            } else {
                adminService.createItem(item);
            }
            response.sendRedirect("items");
        } catch (ServiceException e) {
            try {
                request.setCharacterEncoding("UTF-8");
                Item item = new Item("","",0, 0);
                request.setAttribute("item", item);
                request.setAttribute("exception", "Input data is not correct");
                request.getRequestDispatcher("/WEB-INF/jsp/edit_item.jsp").forward(request, response);
            } catch (Exception e1) {
                response.sendRedirect("error");
            }
        } catch (Exception e){
            response.sendRedirect("error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Person person = (Person) session.getAttribute("person");
        String action = request.getParameter("action");
        PersonService personService = new PersonServiceImplements();
        if (action == null) {
            try {
                request.setAttribute("items", personService.getItems(person));
                request.getRequestDispatcher("/WEB-INF/jsp/view_items.jsp").forward(request, response);
            } catch (DBException e) {
                response.sendRedirect("error");
            }
            return;
        }

        String itemIdString = request.getParameter("id");

        if (action.equalsIgnoreCase("view") && itemIdString != null) {
            try {
                Item item = personService.getItem(Integer.parseInt(itemIdString));
                request.setAttribute("item", item);
                request.getRequestDispatcher("/WEB-INF/jsp/view_items.jsp").forward(request, response);
            } catch (DBException e) {
                response.sendRedirect("error");
            }
            return;
        }

        switch (person.getRole()) {
            case "admin":
                AdminService adminService = new AdminServiceImplements();
                if (action.equalsIgnoreCase("delete") && itemIdString != null) {
                    try {
                        adminService.deleteItem(Integer.parseInt(itemIdString));
                        response.sendRedirect("items");
                    } catch (DBException e) {
                        response.sendRedirect("error");
                    }
                } else if (action.equalsIgnoreCase("edit") && itemIdString != null) {
                    try {
                        Item item = adminService.getItem(Integer.parseInt(itemIdString));
                        request.setAttribute("item", item);
                        request.getRequestDispatcher("/WEB-INF/jsp/edit_item.jsp").forward(request, response);
                    } catch (DBException e) {
                        response.sendRedirect("error");
                    }

                } else if (action.equalsIgnoreCase("add")) {
                    Item item = new Item("", "", 0, 0);
                    request.setAttribute("item", item);
                    request.getRequestDispatcher("/WEB-INF/jsp/edit_item.jsp").forward(request, response);
                } else {
                    response.sendRedirect("error");
                }
                break;
            case "user":
                UserService userService = new UserServiceImplements();
                if (action.equalsIgnoreCase("buy") && itemIdString != null) {
                    try {
                        userService.setPurchase(Integer.parseInt(itemIdString), 1, person);
                        response.sendRedirect("items");
                    } catch (DBException | ServiceException e) {
                        response.sendRedirect("error");
                    }

                } else {
                    response.sendRedirect("error");
                }
                break;
            default:
                response.sendRedirect("error");
                break;
        }
    }

    private Item getItem(HttpServletRequest request) throws ServiceException {
        try {
            String idString = request.getParameter("id");
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String priceString = request.getParameter("price");
            String amountString = request.getParameter("amount");

            if (idString == null || idString.isEmpty()
                    || title == null || title.isEmpty()
                    || priceString == null || priceString.isEmpty()
                    || amountString == null || amountString.isEmpty()){
                throw new ServiceException("A data-entry error");
            }

            float price = Integer.valueOf(priceString);
            int amount = Integer.parseInt(amountString);
            return new Item(title, description, amount, price);
        } catch (Exception e){
            throw new ServiceException("Error added items", e);
        }
    }
}
