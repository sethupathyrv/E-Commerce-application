package com.ooad.web.controller;

import com.ooad.web.model.Order;
import com.ooad.web.model.User;
import com.ooad.web.utils.TokenAuth;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet(name = "MyOrdersController")
public class MyOrdersController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        User user = null;
        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("authToken")){
                    user = TokenAuth.getUserFromToken(cookie.getValue());
                }
            }
        }
        if(user==null){
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
            rd.forward(request,response);
        }else{

            RequestDispatcher rd = request.getRequestDispatcher("jsp/myOrders.jsp");//TODO addressDashboardbb page
            Collection<Order> o =  user.getAllOrders(user.getId());
            request.setAttribute("orderedItems",o);
            rd.forward(request,response);
        }
    }
}
