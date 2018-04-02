<%@ page import="com.ooad.web.utils.TokenAuth" %>
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
</head>
<body>
<script src="/js/jquery.min.js"></script>
<script src="/js/homepageSeller.js"></script>
    <% for (OrderItem oi :orderItems) {
        UserAddress deliveryAddress = oi.getOrder().getDeliveryAddress();
        Item item = oi.getItem(); %>
        <img src="<%=item.getUrl()%>" alt="" style="height: 160px;width: 160px;">
        <br>
        Quantity:<%=oi.getQuantity()%>
    <br>
        DeliveryCity: <%=deliveryAddress.getCity()%>
    <br>
        OrderItemStatus: <%=oi.getOrderItemStatus().getStatusCode()%>
    <br>
    <button onclick="dispatchItem(<%=oi.getId()%>)">Dispatch this Item</button>
    <br>
    <hr>
    <%}%>
</body>
</html>