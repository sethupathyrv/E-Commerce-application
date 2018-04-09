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
    <title>Title</title>
    <%ArrayList<WishListItem> wishList= (ArrayList<WishListItem>) request.getAttribute("wishList");%>
</head>
<body>
<%@include file="header.jsp"%>
<script src="/js/wishList.js"></script>
<div class="container">
    <%for(WishListItem w : wishList){%>
    <div class="row">
        <img src="<%=w.getItem().getUrl()%>" alt="" style="height: 160px;width: 160px; ">
        Price: <%=w.getItem().getPrice()%>
        SellingPrice : <%=w.getItem().getEffectivePrice()%>
        <a  type="btn" onclick="removeFromList(<%=w.getId()%>)">Remove this item</a>
        <a type="btn" onclick="moveToCart(<%=w.getId()%>)">Move to Cart</a>
    </div>
    <%}%>
</div>

<%@include file="footer.jsp"%>
</body>
</html>
