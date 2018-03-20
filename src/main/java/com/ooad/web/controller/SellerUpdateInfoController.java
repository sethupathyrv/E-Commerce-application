package com.ooad.web.controller;

import com.ooad.web.dao.SellerDao;
import com.ooad.web.model.Seller;
import com.ooad.web.utils.TokenAuth;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SellerUpdateInfoController")
public class SellerUpdateInfoController extends HttpServlet {

    /*private SellerDao dao;

    public SellerUpdateInfoController() {
        super();
        dao = new SellerDao(); //create new data object

    }*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*PrintWriter pwOut = response.getWriter();
        String un = request.getParameter("username");
        String pw = request.getParameter("psword");
        String email = request.getParameter("email");
        String storename = request.getParameter("storename");
        String mobilenumber = request.getParameter("mobilenumber");
        String streetaddress = request.getParameter("streetaddress");
        String landmark = request.getParameter("landmark");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String pincode = request.getParameter("pincode");
        String country = request.getParameter("country");
        */
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
        if( seller == null){
            RequestDispatcher rd = request.getRequestDispatcher("jsp/sellerlogin.jsp");
            rd.forward(request,response );
        }
        else {
            request.setAttribute("seller", seller);
            RequestDispatcher rd = request.getRequestDispatcher("jsp/updateInfoSeller.jsp");
            rd.forward(request,response);
        }
    }
}

