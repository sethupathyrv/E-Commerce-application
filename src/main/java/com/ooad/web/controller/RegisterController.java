package com.ooad.web.controller;

import com.ooad.web.dao.UserDao;
import com.ooad.web.utils.BCrypt;
import com.ooad.web.utils.Constants;
import com.ooad.web.utils.EmailUtil;

import javax.mail.MessagingException;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("jsp/register.jsp");
        rd.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter pwOut = response.getWriter();
        String un = request.getParameter("username");
        String pw = request.getParameter("psword");
        String email = request.getParameter("email");
        String hash = EmailUtil.prepareRandomString(30);
        String EmailVerificationHash = BCrypt.hashpw(hash, Constants.BCRYPT_SALT);
        String message="";
        if(!dao.isEmailExists(email)) {
            dao.createUser(un, email, pw, EmailVerificationHash);
            try {
                EmailUtil.sendEmailRegistrationLink(email, hash, un);
                message="Registation Link Was Sent To Your Mail Successfully. Please Verify Your Email";
            } catch (MessagingException e) {
                e.printStackTrace();
                System.out.print(e.getMessage());
            }
            pwOut.print(message);
            response.setContentType("text/html");
            RequestDispatcher view = request.getRequestDispatcher("jsp/login.jsp");
            view.include(request, response); //index page is reloaded with text for new user to login
        }else{
            message="This Email was already registered";
            pwOut.print(message);
            response.setContentType("text/html");
            RequestDispatcher view = request.getRequestDispatcher("jsp/register.jsp");
            view.include(request, response); //index page is reloaded with text for new user to login
        }


    }
}
