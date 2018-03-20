package com.ooad.web.controller;

import com.ooad.web.model.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderController")
public class OrderController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int orderId = Integer.parseInt(request.getParameter("id"));
        Order o = Order.find(orderId);
        if( o == null){
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request,response );
        } else {
            request.setAttribute("order", o);
            RequestDispatcher rd = request.getRequestDispatcher("jsp/order.jsp");
            rd.forward(request, response);
        }
    }
}
