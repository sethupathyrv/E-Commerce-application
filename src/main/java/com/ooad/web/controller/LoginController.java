package com.ooad.web.controller;

import com.ooad.web.dao.UserDao;
import com.ooad.web.model.User;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response.Status;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("jsp/login.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        String em = request.getParameter("email");
        String pw = request.getParameter("psword");
        PrintWriter pwOut = response.getWriter();
        JSONObject validate = dao.validateLogin(em, pw);
        if (validate.getInt("status") == Status.OK.getStatusCode()) {
            User user = dao.getUser(em);
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUserName());
            session.setAttribute("email", em);
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        } else {
            pwOut.print("<p style=\"color:red\">Incorrect Username or Password!</p>");
            RequestDispatcher view = request.getRequestDispatcher("jsp/login.jsp");
            view.include(request, response);
        }

        pwOut.close();

    }
}
