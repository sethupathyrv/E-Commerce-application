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

<%--<div class="container-fluid">
    <div class="col-lg-12">
        <h1 id="pageTitle">Your Orders</h1>
        <span><h5>Ship to <%=user.getUserName()%></h5></span>
        <div class="row">
                <div class="col-lg-5"></div>
                <div class="col-lg-3">
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

        <% for (Order order: orderedItems) {%>
        <div class="cartItems row">
            <% for (OrderItem orderItem: order.getOrderItems()) {%>
            <div class=item-box-big">

            <div class="col-lg-3">
                    <img id = "prodImage<%=orderItem.getItem().getId()%>" width="150"  src="<%=orderItem.getItem().getUrl()%>" height="150" class = "img-responsive" alt="watch">
                </div>
                <div class="col-lg-2">
                    <% System.out.println(orderItem); %>
                    &lt;%&ndash;<% System.out.println(order.getUser()); %>&ndash;%&gt;
                    <h1 id="title">
                        <span id="productTitle"><%=orderItem.getItem().getName()%></span>
                    </h1>
                    <div id="seller">Sold by <a href="#" id="sellerName" data-toggle="tooltip" title="<%=orderItem.getItem().getSeller().getId()%>"><%=orderItem.getItem().getSeller().getUserName()%></a></div>
                    <div>Quantity: <%=orderItem.getQuantity()%></div>
                    <div class="Price col-lg-2 text-left">&#2352;<span id="currentPrice<%=orderItem.getItemPrice()%>"><%=order.getItemsSubToatal()%></span></div>
                </div>
                <div class="col-lg-3">
                    &lt;%&ndash;<p><%=order.getDeliveryAddress()%></p>&ndash;%&gt;
                    <br>
                    <div class="displayAddressDiv">
                        <div><b><%=order.getDeliveryAddress().getFullname()%></b></div>
                        <div><%=order.getDeliveryAddress().getStreetAddress()%>,</div>
                        <div><%=order.getDeliveryAddress().getLandmark()%>, <%=order.getDeliveryAddress().getCity()%>,</div>
                        <div><%=order.getDeliveryAddress().getState()%>, <%=order.getDeliveryAddress().getPincode()%>,</div>
                        <div>Mob:<%=order.getDeliveryAddress().getMobilenumber()%></div>
                    </div>
                </div>
                <div class="col-lg-2 text-left">
                    &lt;%&ndash;<p><%=order.getOrderStatus()%></p>&ndash;%&gt;
                    <br><br>
                        <a href="#"><input type="button" class="btn" value="<%=order.getOrderStatus()%>"></a>

                </div>

                <div class="col-lg-2">
                    &lt;%&ndash;Add Seller review part&ndash;%&gt;
                </div>
            </div>
            <% } %>
        </div>
        <hr>
        <% } %>

    </div>

</div>--%>


<div class="container-fluid orders">
    <%=orderedItems.size()%>
    <%for(Order o: orderedItems) {
        ArrayList<OrderItem> orderItems = (ArrayList<OrderItem>) o.getOrderItems();
        OrderStatus os = o.getOrderStatus();
        UserAddress shippingAddress = o.getDeliveryAddress(); %>
        <div class="container order">
            <div class="row orderDetails">
                OrderId <%=o.getId()%>
                Ship to  <%=shippingAddress.getFullname()%>
                Order Status <%=os.getStatus()%>
            </div>
            <div class="row orderItems center-block">
            <%for(OrderItem oi : orderItems){
                Offer offer= oi.getItem().getOffer();%>

                <div class="row orderItem">
                    <img class="col-sm-2" src="<%=oi.getItem().getUrl()%>" alt="" style="height: 160px;width: 160px">
                    <%=oi.getItem().getName()%>
                    <%if(offer.getOfferCode() == 201){
                        DiscountOffer dof = (DiscountOffer) offer;%>
                        <%=oi.getItemPrice()*(100-dof.getPercentage())/100%>
                    <%}else if(offer.getOfferCode() == 202){
                        PriceOffer p = (PriceOffer) offer;%>
                        <%=oi.getItemPrice() - p.getPriceCut()%>
                    <%} else { %>
                        <%=oi.getItemPrice()%>
                    <%}%>

                    Status :<%=oi.getOrderItemStatus()%>
                    <%if(oi.getOrderItemStatus() == OrderItemStatus.SHIPPED){ %>
                        <button onclick="itemDelivered(<%=oi.getId()%>)">Item Delivered</button>
                    <%}%>
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

