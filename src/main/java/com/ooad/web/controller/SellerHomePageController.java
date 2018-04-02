package com.ooad.web.controller;

import com.ooad.web.model.OrderItem;
import com.ooad.web.model.Seller;
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
import java.util.Collection;

@WebServlet(name = "SellerHomePageController")
public class SellerHomePageController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Seller seller = null;
        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("sellerAuthToken")){
                    seller = TokenAuth.getSellerFromToken(cookie.getValue());
                }
            }
        }
        RequestDispatcher rd;
        if(seller == null) {
         rd = request.getRequestDispatcher("jsp/sellerlogin.jsp");
         rd.forward(request,response);
        }else{
            Collection<OrderItem> orderItems = seller.getOrderItems();
            rd = request.getRequestDispatcher("jsp/homepageSeller.jsp");
            request.setAttribute("orderItems",orderItems );
            rd.forward(request,response );
        }
    }
}
