<%@ page import="com.ooad.web.model.WishListItem" %><%--
  Created by IntelliJ IDEA.
  User: sandeep
  Date: 7/4/18
  Time: 10:21 PM
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

    <link rel="stylesheet" href="../css/order.css">
    <link rel="stylesheet" href="../css/item.css">
    <link rel="stylesheet" href="../css/cart.css">
    <%ArrayList<WishListItem> wishList= (ArrayList<WishListItem>) request.getAttribute("wishList");%>
</head>
<body>
<%@include file="header.jsp"%>
<script src="/js/wishList.js"></script>
<div class="container">
    <div class="col-lg-12">
        <h1 id="pageTitle">Your Wishlist</h1>
        <span><h5><%=wishList.size()%> items in your wishlist</h5></span>
        <hr>
    </div>
    <%for(WishListItem w : wishList){%>
    <div class="row">
        <div class="col-lg-3">
            <img src="<%=w.getItem().getUrl()%>" alt="" style="height: 160px;width: 160px; ">
        </div>
        <div class="col-lg-4">
            <h1 id="title">
                <span id="productTitle"><%=w.getItem().getName()%></span>
            </h1>
            <div>Original Price: &#2352;<%=w.getItem().getPrice()%></div>
            <div class="Price col-lg-2 text-left"><span id="currentPrice<%=w.getItem().getPrice()%>">
                                &#2352;<%=w.getItem().getEffectivePrice()%>
                            </span>
            </div>
        </div>
        <div class="col-lg-2">
            <a  type="btn" onclick="removeFromList(<%=w.getId()%>)">Remove this item</a>
        </div>
        <div class="col-lg-2">
            <a type="btn" onclick="moveToCart(<%=w.getId()%>)">Move to Cart</a>
        </div>
    </div>
    <hr>
    <br>
    <%}%>
</div>

<%@include file="footer.jsp"%>
</body>
</html>
