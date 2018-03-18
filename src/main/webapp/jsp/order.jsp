<%@ page import="com.ooad.web.model.*" %>
<%@ page import="java.util.Collection" %><%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 14/3/18
  Time: 9:46 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="images/Amazon.ico" type="image/x-icon">
    <title>
        Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs &amp; more</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">

    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../css/order.css">
    <% Order order = (Order) request.getAttribute("order"); %>
    <% UserAddress address = order.getDeliveryAddress();%>
    <% Collection<OrderItem> orderItems = order.getOrderItems();%>
    <% User u = order.getUser();%>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container-fluid">
    <h3>Review your order</h3>
    <p>By clicking on the "Place your Order" button, you agree to Amazon.in's privacy notice and conditions of use</p>

    <div class="row">
        <div class="col-lg-9">
            <div class="item-box-big">
                <h4>Shipping Address</h4>
                <p><%=address.getFullname()%></p>
                <p>mobile: <%=address.getMobilenumber()%></p>
                <p><%=address.getStreetAddress()%></p>
                <p><%=address.getPincode()%></p>
            </div>
        </div>

        <div class="col-lg-3">
            <div class="item-box">
                <div>
                    <input type="button" class="btn1" value="Place your Order">
                </div>

                <div>
                    <h3>Order Summary</h3>
                    <table>
                        <tbody>
                        <tr class="text-c1">
                            <td class="text-right text-nowrap">Items:</td>
                            <td><%=order.getItemsSubToatal()%></td>
                        </tr>
                        <tr>
                            <td class="text-right text-c1">Delivery:</td>
                            <td><%=order.getShippingCharges()%></td>
                        </tr>
                        <tr class="saving">
                            <td class="text-right text-c1">Total:</td>
                            <td><%=order.getItemsSubToatal()+order.getShippingCharges()%></td>
                        </tr>

                        </tbody>
                    </table>
                    <hr>
                    <table>
                        <tbody>
                        <tr>
                            <td>Order Total:</td>
                            <td>225.00</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            </div>

        </div>
    </div>
    <%--<div class="row">--%>
        <%--<div class="item-box-big">--%>
            <%--<div class="row">--%>
                <%--<div class="col-lg-4">--%>
                    <%--<img id = "prodImage" width="175"  height="200" class = "img-responsive" alt="watch"   >--%>
                <%--</div>--%>
                <%--<div class="col-lg-5">--%>
                    <%--<h1 id="title">--%>
                        <%--<span id="productTitle">Watch </span>--%>
                    <%--</h1>--%>
                    <%--<div id="itemPrice">--%>
                        <%--<table>--%>
                            <%--<tbody>--%>
                            <%--<tr class="text-c1">--%>
                                <%--<td class="text-right text-nowrap">M.R.P:</td>--%>
                                <%--<td class="text-left text-nowrap">--%>
                                    <%--<del>&#2352;</del><del><span id="originalPrice"> </span></del>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <%--<tr>--%>
                                <%--<td class="text-right text-c1">Price:</td>--%>
                                <%--<td class="text-left text-c2">--%>
                                    <%--&#2352;<span id="currentPrice"> 2,495.00</span>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <%--<tr class="saving">--%>
                                <%--<td class="text-right text-c1">You Save:</td>--%>
                                <%--<td class="text-left text-c2">--%>
                                    <%--&#2352;<span id="savingPrice"> 0.00 (<span id="savingPercentage">0</span>%)</span>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <%--<tr>--%>
                                <%--<td></td>--%>
                                <%--<td>--%>
                                    <%--<span>Inclusive of all taxes</span>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <%--</tbody>--%>
                        <%--</table>--%>
                    <%--</div>--%>
                    <%--<div id="cod">Cash on delivery <span id="cod_elgiblity">eligible.</span></div>--%>
                    <%--<div id="availability"><span id="avail">In stock.</span></div>--%>
                    <%--&lt;%&ndash;<div id="itemId" type="hidden"><%=item.getId()%></div>&ndash;%&gt;--%>
                    <%--<input type="hidden" id='itemId' class='h_v'>--%>
                <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

    <%for (OrderItem oi : orderItems){%>
        <%=oi.getItem()%>
    <%}%>
<%@include file="footer.jsp" %>
</body>
</html>
