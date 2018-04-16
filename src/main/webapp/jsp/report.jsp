<%@ page import="java.util.Collection" %>
<%@ page import="com.ooad.web.model.*" %><%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 9/4/18
  Time: 11:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="images/Amazon.ico" type="image/x-icon">
    <title>
        Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs &amp; more</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/index.css">
    <script src="js/index.js"></script>
    <% Collection<User> users = (Collection<User>) request.getAttribute("users");%>
    <% Collection<Seller> sellers = (Collection<Seller>) request.getAttribute("sellers");%>
    <% Collection<Item> items = (Collection<Item>) request.getAttribute("items");%>
</head>
<body>
<%@include file="header.jsp" %>
<h2><b>Report</b></h2>
<hr>
<div class="container-fluid">
    <h4><b>Users</b></h4><%=users.size()%>
    <div class="container">
        <div class="row">
            <div class="col-sm-1"><b>Username</b></div>
            <div class="col-sm-2"><b>EmailId</b></div>
            <div class="col-sm-1"><b>Password</b></div>
        </div>
        <%for (User u: users){%>
            <div class="row">
                <div class="item-box-big">
                    <div class="col-sm-1"><%=u.getUserName()%></div>
                    <div class="col-sm-2"><%=u.getEmailId()%></div>
                    <div class="col-sm-1"><%=u.getPassword()%></div>
                </div>
            </div>
        <%}%>
    </div>
</div>
<hr>
<div class="container-fluid">
    <h4><b>Sellers</b></h4><%=sellers.size()%>
    <div class="container">
        <div class="row">
            <div class="col-sm-1"><b>Username</b></div>
            <div class="col-sm-2"><b>EmailId</b></div>
            <div class="col-sm-1"><b>Password</b></div>
        </div>
        <%for (Seller s: sellers){%>
        <div class="row">
            <div class="item-box-big">
                <div class="col-sm-1"><%=s.getUserName()%></div>
                <div class="col-sm-2"><%=s.getEmailId()%></div>
                <div class="col-sm-1"><%=s.getPassword()%></div>
            </div>
        </div>
        <%}%>
    </div>
</div>
<hr>
<div class="container-fluid">
    <h4><b>Items</b></h4><%=items.size()%>
    <div class="container">
        <div class="row">
            <div class="col-sm-1"><b>Item</b></div>
            <div class="col-sm-1"><b>Name</b></div>
            <div class="col-sm-1"><b>Barcode</b></div>
            <div class="col-sm-1"><b>Price</b></div>
            <div class="col-sm-1"><b>Seller</b></div>
            <div class="col-sm-1"><b>Offer</b></div>
            <div class="col-sm-2"><b>Category</b></div>
            <div class="col-sm-2"><b>Sub Category</b></div>
            <div class="col-sm-1"><b>Quantity</b></div>
        </div>
        <%for (Item i: items){%>
        <br>
        <div class="row">
            <div class="item-box-big">
                <div class="col-sm-1"><img src="<%=i.getUrl()%>" width="150" height="150" class = "img-responsive"></div>
                <div class="col-sm-1"><%=i.getName()%></div>
                <div class="col-sm-1"><%=i.getItemBarcode()%></div>
                <div class="col-sm-1"><%=i.getPrice()%></div>
                <div class="col-sm-1"><%=i.getSeller().getUserName()%></div>
                <div class="col-sm-1"><%=i.getOffer().getOfferCode()%></div>
                <div class="col-sm-2"><%=i.getSubCategory().getItemCategory().getDisplayName()%></div>
                <div class="col-sm-2"><%=i.getSubCategory().getDisplayName()%></div>
                <div class="col-sm-1"><%=i.getQuantity()%></div>
            </div>
        </div>
        <%}%>
    </div>
</div>
<br>
<%@include file="footer.jsp" %>
</body>
</html>
