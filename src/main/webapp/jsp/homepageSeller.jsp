<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ooad.web.model.*" %><%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 19/2/18
  Time: 7:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <% ArrayList<OrderItem> orderItems = (ArrayList<OrderItem>) request.getAttribute("orderItems");%>
    <% Seller seller = (Seller) request.getAttribute("seller"); %>
</head>
<body>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-dropdownhover.css">
<link rel="stylesheet" href="../css/homepageSeller.css">
<script src="/js/jquery.min.js"></script>
<script src="/js/jquery.cookie.js"></script>
<script src="/js/homepageSeller.js"></script>
<div class="container-fluid">
    <%-- ..........Logo header...........--%>
    <div class="row nav-belt ">
        <header class="container-fluid header">

            <div class="logo col-lg-2 col-md-2">
                <a href="#" class="nav-logo-link">
                    <img src="https://images-na.ssl-images-amazon.com/images/G/31/rainier/nav/sc-unified._CB360962420_.png">
                </a>
            </div>

        </header>
    </div>

    <%--.........Navbar................--%>
    <div class="row bar">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <!-- Collection of nav links and other content for toggling -->
                <div id="navbarCollapse" class="collapse navbar-collapse">
                    <ul class="nav nav-tabs">
                        <li><a href="/seller" class="active">Dashboard</a></li>
                        <li><a href="/infoseller">Seller Information </a></li>
                        <li><a href="/seller/add">Add Item</a></li>
                        <li><a href="#" id="sellerLogout"> Logout</a></li>
                        <li><a href="/infoseller"> <%=seller.getUserName()%></a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>

    <% for (OrderItem oi :orderItems) {
        UserAddress deliveryAddress = oi.getOrder().getDeliveryAddress();
        Item item = oi.getItem(); %>
        <img src="<%=item.getUrl()%>" alt="" style="height: 160px;width: 160px;">
        <br>
        Quantity:<%=oi.getQuantity()%>
        <br>
        user name <%=oi.getOrder().getUser().getUserName()%>
        <br>
        Delivery name <%=deliveryAddress.getFullname()%>
        <br>
            DeliveryCity: <%=deliveryAddress.getCity()%>
        <br>
            OrderItemStatus: <%=oi.getOrderItemStatus().getStatusCode()%>
        <br>
        <%if (oi.getOrderItemStatus()== OrderItemStatus.WAITING_FOR_SELLER){%>
            <button onclick="dispatchItem(<%=oi.getId()%>)">Dispatch this Item</button>
        <%} else { %>
            <p><%=oi.getOrderItemStatus().getStatus()%></p>
        <%}%>
        <br>
        <hr>
    <%}%>
</div>
</body>
</html>