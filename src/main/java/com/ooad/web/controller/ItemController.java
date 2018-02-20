package com.ooad.web.controller;

import com.ooad.web.model.Item;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ItemController")
public class ItemController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int itemId = Integer.parseInt(request.getParameter("id"));
        // getItem() using ItemDao.
        //Creating a Dummy item
        Item item = new Item(itemId,"Watch",200,"/images/watch.jpg", null);
        request.setAttribute("item",item);
        System.out.print("item"+item.toString());
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/item.jsp");
        rd.forward(request,response);
    }
}
