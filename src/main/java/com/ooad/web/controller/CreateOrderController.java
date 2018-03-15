package com.ooad.web.controller;

import com.ooad.web.dao.UserAddressDao;
import com.ooad.web.model.User;
import com.ooad.web.model.UserAddress;
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

@WebServlet(name = "CreateOrderController")
public class CreateOrderController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        User user = null;
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("authToken")) {
                    user = TokenAuth.getUserFromToken(cookie.getValue());
                }
            }
        }
        if(user.getDefaultAddressId()==-1){
            RequestDispatcher rd = request.getRequestDispatcher("jsp/addressDashboard.jsp");//TODO addressDashboardbb page
            UserAddressDao userAddressDao = new UserAddressDao();
            ArrayList<UserAddress> userAddresses = (ArrayList<UserAddress>) userAddressDao.getAddressfromId(user.getId());
            request.setAttribute("userAddress",userAddresses);
            rd.forward(request,response);
        }
        else{
            user.createOrder(user.getDefaultAddressId());
            RequestDispatcher rd = request.getRequestDispatcher("jsp/");//TODO order page
            rd.forward(request,response);
        }
    }
}
