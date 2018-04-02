<%@ page import="com.ooad.web.model.*" %>
<%@ page import="java.util.Collection" %><%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 2/4/18
  Time: 11:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="images/Amazon.ico" type="image/x-icon">
    <title>
        Amazon.com Shopping Cart </title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">

    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../css/cart.css">
    <% Collection<Order> orderedItems = (Collection<Order>) request.getAttribute("orderedItems");%>

</head>

<body>
<%@include file="header.jsp" %>
<div class="container-fluid">
    <% for (Order o: orderedItems) {%>
    <div class="orderItemsrow">
        <div class="col-lg-9">

            <div class=item-box-big">

                <div class="col-lg-10">

                    <p>UserId: <%=o.getUser()%> </p>
                    <p>Order item subtotal: <%=o.getItemsSubToatal()%> </p>
                    <p>Delivery address: <%=o.getDeliveryAddress()%></p>
                    <p>Order Status: <%=o.getOrderStatus()%></p>

                </div>
            </div>
        </div>
        <div class="col-lg-3"></div>
    </div>

    <% } %>
</div>

<%@include file="footer.jsp" %>
</body>
</html>

