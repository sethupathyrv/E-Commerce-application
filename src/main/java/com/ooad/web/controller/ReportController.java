package com.ooad.web.controller;

import com.ooad.web.dao.UserDao;
import com.ooad.web.model.Item;
import com.ooad.web.model.Seller;
import com.ooad.web.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ReportController")
public class ReportController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<User> u = User.getAllUsers();
        Collection<Seller> s = Seller.getAllSellers();
        Collection<Item> i = Item.getAllItems();
        request.setAttribute("users",u);
        request.setAttribute("sellers",s);
        request.setAttribute("items",i);
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/report.jsp");
        rd.forward(request,response);
    }
}
