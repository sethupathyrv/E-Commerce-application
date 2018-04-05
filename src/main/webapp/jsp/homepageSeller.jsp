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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-dropdownhover.css">
<link rel="stylesheet" href="../css/homepageSeller.css">
<link rel="stylesheet" href="../css/amazon1.css"/>
<link rel="stylesheet" href="../css/amazon2.css"/>
<link rel="stylesheet" href="../css/cart.css">
<script src="/js/jquery.min.js"></script>
<script src="/js/jquery.cookie.js"></script>
<script src="/js/homepageSeller.js"></script>
<script src="../js/addItem.js"></script>

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

        <div class="col-lg-12">
            <h1 id="pageTitle">Your Orders</h1>
                <div class="row">
                    <div class="col-lg-3"></div>
                    <div class="col-lg-3">
                        <h5 class="headTiles text-left">Order Details</h5>
                    </div>
                    <div class="col-lg-2">
                        <h5 class="headTiles text-left">Order Status</h5>
                    </div>
                </div>
                <hr>

                <% for (OrderItem oi :orderItems) {
                    UserAddress deliveryAddress = oi.getOrder().getDeliveryAddress();
                    Item item = oi.getItem(); %>

                <div class="cartItems row">
                    <div class=item-box-big">
                        <div class="col-lg-3">
                            <img id = "prodImage<%=item.getId()%>" width="150"  src="<%=item.getUrl()%>" height="150" class = "img-responsive" alt="watch">
                            <br>
                        </div>
                        <div class="col-lg-3">
                            <h1 id="title">
                                <span id="productTitle"><%=item.getName()%></span>
                            </h1>
                            <div>Quantity:<%=oi.getQuantity()%></div>

                            <div>Price: &#2352;<span id="currentPrice<%=item.getId()%>"><%=oi.getItemPrice()*oi.getQuantity()%></span></div>
                            <div>Buyer name: <%=oi.getOrder().getUser().getUserName()%></div>

                            <div>Delivery name: <%=deliveryAddress.getFullname()%></div>

                             <div>DeliveryCity: <%=deliveryAddress.getCity()%></div>
                            <br>
                        </div>

                        <div class="col-lg-3">
                                OrderItemStatus: <%=oi.getOrderItemStatus().getStatusCode()%>
                            <br>
                            <%if (oi.getOrderItemStatus()== OrderItemStatus.WAITING_FOR_SELLER){%>
                                <button onclick="dispatchItem(<%=oi.getId()%>)">Dispatch this Item</button>
                            <%} else { %>
                                <p><%=oi.getOrderItemStatus().getStatus()%></p>
                            <%}%>
                            <br>
                        </div>
                    </div>
                </div>
                <hr>
                <%}%>
</div>


</div>
</body>
</html>