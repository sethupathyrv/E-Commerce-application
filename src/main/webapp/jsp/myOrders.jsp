<%@ page import="com.ooad.web.model.*" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.ooad.web.model.Offer.Offer" %>
<%@ page import="com.ooad.web.model.Offer.DiscountOffer" %>
<%@ page import="com.ooad.web.model.Offer.PriceOffer" %>
<%--
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
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/myOrders.js"></script>
    <link rel="stylesheet" href="../css/order.css">
    <link rel="stylesheet" href="../css/item.css">
    <link rel="stylesheet" href="../css/cart.css">
    <% Collection<Order> orderedItems = (Collection<Order>) request.getAttribute("orderedItems");%>

</head>

<body>
<%@include file="header.jsp" %>

<div class="container-fluid orders">
    <div class="col-lg-12">
        <h1 id="pageTitle">Your Orders</h1>
        <span><h5><%=orderedItems.size()%> orders placed</h5></span>
        <div class="row">
            <div class="col-lg-6">
                <h5 class="headTiles text-center">Order Details</h5>
            </div>
            <div class="col-lg-2">
                <h5 class="headTiles text-left">Shipping Address</h5>
            </div>
            <div class="col-lg-2">
                <h5 class="headTiles text-left">Order Status</h5>
            </div>
            <div class="col-lg-2">
                <h5 class="headTiles text-left">Seller Review</h5>
            </div>
        </div>
        <hr>
    </div>
    <%for(Order o: orderedItems) {
        ArrayList<OrderItem> orderItems = (ArrayList<OrderItem>) o.getOrderItems();
        OrderStatus os = o.getOrderStatus();
        UserAddress shippingAddress = o.getDeliveryAddress(); %>
        <div class="container order">

            <div class="row orderItems center-block">
            <%for(OrderItem oi : orderItems){
                Offer offer= oi.getItem().getOffer();%>

                <div class="cartItems row">
                    <div class=item-box-big">
                    <div class="col-lg-1">
                        <div>OrderId <b><%=o.getId()%></b></div>
                    </div>
                    <div class="col-lg-2">
                        <img id = "prodImage<%=oi.getItem().getId()%>" width="150"  src="<%=oi.getItem().getUrl()%>" height="150" class = "img-responsive" alt="watch">
                    </div>
                    <div class="col-lg-3 text-left">
                        <h1 id="title">
                            <span id="productTitle"><%=oi.getItem().getName()%></span>
                        </h1>
                        <div id="seller">Sold by <a href="#" id="sellerName" data-toggle="tooltip" title="<%=oi.getItem().getSeller().getId()%>"><%=oi.getItem().getSeller().getUserName()%></a></div>
                        <div>Ship to:  <%=shippingAddress.getFullname()%></div>
                        <div>Order Status: <%=os.getStatus()%></div>
                        <div class="Price col-lg-2 text-left"><span id="currentPrice<%=oi.getItemPrice()%>">
                                <%if(offer.getOfferCode() == 201){
                                    DiscountOffer dof = (DiscountOffer) offer;%>
                                &#2352;<%=oi.getItemPrice()*(100-dof.getPercentage())/100%>
                                <%}else if(offer.getOfferCode() == 202){
                                    PriceOffer p = (PriceOffer) offer;%>
                                &#2352;<%=oi.getItemPrice() - p.getPriceCut()%>
                                <%} else { %>
                                &#2352;<%=oi.getItemPrice()%>
                                <%}%>
                            </span>
                        </div>
                    </div>
                    <div class="col-lg-2 text-left">
                        <div class="displayAddressDiv">
                            <div><b><%=shippingAddress.getFullname()%></b></div>
                            <div><%=shippingAddress.getStreetAddress()%>,</div>
                            <div><%=shippingAddress.getLandmark()%>, <%=shippingAddress.getCity()%>,</div>
                            <div><%=shippingAddress.getState()%>, <%=shippingAddress.getPincode()%>,</div>
                            <div>Mob:<%=shippingAddress.getMobilenumber()%></div>
                        </div>
                    </div>

                    <div class="col-lg-2 text-left">
                        <%=oi.getOrderItemStatus()%>
                        <%if(oi.getOrderItemStatus() == OrderItemStatus.SHIPPED){ %>
                        <button onclick="itemDelivered(<%=oi.getId()%>)">Item Delivered</button>
                        <%}%>
                    </div>
                    <div class="col-lg-2">
                        <%--Add Seller review part--%>
                    </div>
                </div>
                </div>
            <%}%>

            </div>
        </div>
    <hr>
    <%}%>

</div>
<%@include file="footer.jsp" %>
</body>
</html>

