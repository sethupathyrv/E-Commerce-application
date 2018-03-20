package com.ooad.web.controller;

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

@WebServlet(name = "AdminController")
public class AdminController extends HttpServlet {
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
        if( user.getUserName() == "admin"){
            RequestDispatcher rd = request.getRequestDispatcher("jsp/admin.jsp");
            rd.forward(request,response );
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("jsp/headerAmazon.jsp");
            rd.forward(request,response);
        }
    }
}
