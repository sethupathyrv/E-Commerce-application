<%@ page import="com.ooad.web.model.Seller" %>
<%@ page import="com.ooad.web.model.Item" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.ooad.web.model.Offer.Offer" %><%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 15/4/18
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <% Seller seller = (Seller) request.getAttribute("seller"); %>
    <% Collection<Item> items = (Collection<Item>) request.getAttribute("items");%>
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
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <!-- Collection of nav links and other content for toggling -->
                <div id="navbarCollapse" class="collapse navbar-collapse">
                    <ul class="nav nav-tabs">
                        <li><a href="/seller" class="active">Dashboard</a></li>
                        <li><a href="/infoseller">Seller Information </a></li>
                        <li><a href="/seller/add">Add Item</a></li>
                        <li class="active"><a href="/selleritems">Seller Items </a></li>
                        <li><a href="#" id="sellerLogout"> Logout</a></li>
                        <li><a href="/infoseller"> <%=seller.getUserName()%></a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>

        <div class="container-fluid">
            <h4><b>Items added by you</b></h4><%=items.size()%>
            <div class="container">
                <div class="row">
                    <div class="col-sm-1"><b>Item</b></div>
                    <div class="col-sm-1"><b>Name</b></div>
                    <div class="col-sm-1"><b>Barcode</b></div>
                    <div class="col-sm-1"><b>Price</b></div>
                    <div class="col-sm-1"><b>Offer</b></div>
                    <div class="col-sm-1"><b>Brand</b></div>
                    <div class="col-sm-2"><b>Category</b></div>
                    <div class="col-sm-1"><b>Sub Category</b></div>
                    <div class="col-sm-1"><b>Quantity</b></div>
                </div>
                <hr>
                <%for (Item i: items){%>
                    <%Offer o = i.getOffer();%>
                <br>
                <div class="row">
                    <div class="item-box-big">
                        <div class="col-sm-1"><img src="<%=i.getUrl()%>" width="150" height="150" class = "img-responsive"></div>
                        <div class="col-sm-1"><%=i.getName()%></div>
                        <div class="col-sm-1"><%=i.getItemBarcode()%></div>
                        <div class="col-sm-1"><%=i.getPrice()%></div>
                        <div class="col-sm-1"><%=o.getOfferCode()%></div>
                        <div class="col-sm-1"><%=i.getBrand()%></div>
                        <div class="col-sm-2"><%=i.getSubCategory().getItemCategory().getDisplayName()%></div>
                        <div class="col-sm-1"><%=i.getSubCategory().getDisplayName()%></div>
                        <div class="col-sm-1"><%=i.getQuantity()%></div>
                        <%--<div class="col-sm-1"><a href="#">Delete</a></div>--%>
                        <div class="col-sm-1"><a href="/item/update?id=<%=i.getId()%>">Update</a></div>
                        <%--<div><input id="addtocart"type="button" class="btn1" value="Update"></div>--%>
                    </div>
                </div>
                <br><hr>
                <%}%>
            </div>
</div>
</body>
</html>
