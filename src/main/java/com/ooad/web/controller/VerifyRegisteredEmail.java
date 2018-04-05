package com.ooad.web.controller;

import com.ooad.web.dao.UserDao;
import com.ooad.web.utils.BCrypt;
import com.ooad.web.utils.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "VerifyRegisteredEmail")
public class VerifyRegisteredEmail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("userEmail");
        String hash = BCrypt.hashpw(request.getParameter("hash"), Constants.BCRYPT_SALT);
        String scope = request.getParameter("scope");
        UserDao userDao = new UserDao();
        if(userDao.verifyEmailHash(userEmail,hash) && scope.equals("activation")){
            userDao.updateStatus(userEmail);
            String message = "Email verified successfully. Account was activated. Click <a href=\"/login\">here</a> to login";
            request.setAttribute("message",message);
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/emailverification.jsp");
            rd.forward(request,response);
        }else{
            String message = "Wrong Email Validation Input";
            request.setAttribute("message",message);
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/emailverification.jsp");
            rd.forward(request,response);
        }


    }
}
