/*
 * Created by Shravan on  14/2/18 11:52 PM.
 * Copyright (c) 2018. All rights reserved.
 */

package com.ooad.web.controller;

import com.ooad.web.dao.SellerDao;
import com.ooad.web.dao.UserDao;
import com.ooad.web.model.User;
import org.json.JSONObject;
import com.ooad.web.model.Seller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.PrintWriter;

public class SellerLoginController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("jsp/sellerlogin.jsp");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SellerDao sellerdao = new SellerDao();
        String em = request.getParameter("email");
        String pw = request.getParameter("psword");
        PrintWriter pwOut = response.getWriter();
        JSONObject validate = sellerdao.validateSellerLogin(em, pw);
        if (validate.getInt("status") == Response.Status.OK.getStatusCode()) {
            Seller seller = sellerdao.getSeller(em);
            HttpSession session = request.getSession();
            session.setAttribute("username", seller.getUserName());
            session.setAttribute("email", em);
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        } else {
            pwOut.print("<p style=\"color:red\">Incorrect Username or Password!</p>");
            RequestDispatcher view = request.getRequestDispatcher("jsp/sellerlogin.jsp");
            view.include(request, response);
        }

        pwOut.close();

    }
}
