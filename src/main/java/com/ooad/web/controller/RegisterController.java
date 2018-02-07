package com.ooad.web.controller;

import com.ooad.web.dao.UserDao;
import com.ooad.web.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterController")
public class RegisterController extends HttpServlet {

    private UserDao dao;

    public RegisterController() {
        super();
        dao = new UserDao(); //create new data object

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter pwOut= response.getWriter();
        String un = request.getParameter("username");
        String pw = request.getParameter("psword");
        String email=request.getParameter("email");


            User user = new User(3,un,email,pw,true);
            dao.createUser(user);
            pwOut.print("Registration Successful! Please Login.");
            response.setContentType("text/html");
            RequestDispatcher view = request.getRequestDispatcher("jsp/login.jsp");
            view.include(request, response); //index page is reloaded with text for new user to login

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("jsp/register.jsp");
        rd.forward(request,response);

    }
}
