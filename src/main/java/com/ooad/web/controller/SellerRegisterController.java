/*
 * Created by Shravan on  15/2/18 3:02 PM.
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.controller;

import com.ooad.web.dao.SellerDao;
import com.ooad.web.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SellerRegisterController")
public class SellerRegisterController extends HttpServlet {

    private SellerDao dao;

    public SellerRegisterController() {
        super();
        dao = new SellerDao(); //create new data object

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("jsp/sellerregister.jsp");
        rd.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter pwOut = response.getWriter();
        String un = request.getParameter("username");
        String pw = request.getParameter("psword");
        String email = request.getParameter("email");

        dao.createSeller(un, email, pw);
        pwOut.print("Registration Successful! Please Login.");
        response.setContentType("text/html");
        RequestDispatcher view = request.getRequestDispatcher("jsp/sellerlogin.jsp");
        view.include(request, response); //index page is reloaded with text for new user to login

    }

}
