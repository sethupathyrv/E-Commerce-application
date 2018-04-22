package com.ooad.web.controller;

import com.ooad.web.dao.ItemDao;
import com.ooad.web.dao.SellerDao;
import com.ooad.web.model.Item;
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
import java.util.ArrayList;

@WebServlet(name = "ExamController")
public class ExamController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* Cookie[] cookies = request.getCookies();
        User user = null;
        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("authToken")){
                    user = TokenAuth.getUserFromToken(cookie.getValue());
                }
            }
        }
        if(user==null){
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
            rd.forward(request,response);*/

        RequestDispatcher rd = request.getRequestDispatcher("/exam.jsp");
        SellerDao sellerDao = new SellerDao();
        ArrayList<Seller> sellers= (ArrayList<Seller>) sellerDao.getAllSeller();
        ArrayList<Integer> totalItems = new ArrayList<Integer>();
        ItemDao itemDao = new ItemDao();
        for(Seller seller : sellers){
            totalItems.add(itemDao.getSellerItem(seller.getId()).size());
        }
        request.setAttribute("itemSoldCount", itemDao.getQuantitySold(Item.find(7)));
        request.setAttribute("sellers",sellers );
        request.setAttribute("totalItems",totalItems );
        rd.forward(request,response);


    }
}
