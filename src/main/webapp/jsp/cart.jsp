<%@ page import="com.ooad.web.model.Cart" %><%--
  Created by IntelliJ IDEA.
  User: chakri
  Date: 14-Mar-18
  Time: 1:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <% Cart cart= (Cart)request.getAttribute("cart");%>
</head>

<body>
<p> <%= cart.toJSON().toString(4)%></p>
<input type="hidden" value="<%=cart%>" id='itemId' class='h_v'>
<button id="checkout" onclick="/checkout">checkout</button>
</body>
</html>
