<%@ page import="com.ooad.web.model.Item" %>
<%@ page import="com.ooad.web.model.Offer.DiscountOffer" %>
<%@ page import="com.ooad.web.model.Offer.PriceOffer" %>
<%@ page import="com.ooad.web.model.Offer.BuyXGetYOffer" %><%--
  ~ Created by Harsha Raj on 18/02/18 15:37
  ~ Copyright (c) 2018. All rights reserved.
  --%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="images/Amazon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/jquery.rateyo.min.css"/>

    <title>
        Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs &amp; more</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/item.css">

    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="../js/item.js"></script>
    <% Item item = (Item) request.getAttribute("item"); %>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-4">
            <img id = "prodImage" width="346"  src=<%=item.getUrl()%> height="346" class = "img-responsive" alt="watch"   >
        </div>
        <div class="col-lg-5">
            <h1 id="title">
                <span id="productTitle"> <%= item.getName()%></span>
            </h1>
            <hr>
            <div id="itemPrice">
                <table>
                    <tbody>
                    <tr class="text-c1">
                        <td class="text-right text-nowrap">M.R.P:</td>
                        <%if (item.getOffer().getOfferCode() == -1){ %>
                            <td class="text-left text-nowrap">
                            &#2352;<span id="originalPrice"><%= item.getPrice()%> </span>
                            </td>
                        <%} else{%>
                        <td class="text-left text-nowrap">
                            <del>&#2352;</del><del><span id="originalPrice"><%= item.getPrice()%> </span></del>
                        </td>
                        <%}%>
                    </tr>
                    <%--<tr>--%>
                        <%--<td class="text-right text-c1">Price:</td>--%>
                        <%--<td class="text-left text-c2">--%>
                            <%--&#2352; <span id="currentPrice"><%=item.getPrice()%></span>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <% if(item.getOffer().getOfferCode()==201){
                        DiscountOffer o = (DiscountOffer) item.getOffer();%>
                    <tr class="saving">
                        <td class="text-right text-c1">Offer:</td>
                        <td class="text-left text-c2">
                            &#2352;<span id="savingPrice"> <%=o.getPercentage()*item.getPrice()/100%>   (<span id="savingPercentage"><%=o.getPercentage()%></span>%)</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-right text-c1">Price:</td>
                        <td class="text-left text-c2">
                            &#2352; <span id="currentPrice"><%=item.getPrice()-(o.getPercentage()*item.getPrice()/100)%> </span>
                        </td>
                    </tr>
                    <%} else if (item.getOffer().getOfferCode() == 202){
                        PriceOffer o = (PriceOffer )item.getOffer();
                    %>
                    <tr class="saving">
                        <td class="text-right text-c1">Offer:</td>
                        <td class="text-left text-c2">
                           &#2352;<span id="savingPrice"> <%=o.getPriceCut()%></span>
                    </td>
                    </tr>
                    <tr>
                        <td class="text-right text-c1">Price:</td>
                        <td class="text-left text-c2">
                            &#2352; <span id="currentPrice"><%=item.getPrice()-(o.getPriceCut())%> </span>
                        </td>
                    </tr>

                    <%} else if(item.getOffer().getOfferCode() == 203){
                        BuyXGetYOffer o = (BuyXGetYOffer) item.getOffer();
                        int x = o.getX();
                        int y = o.getY();%>
                    <tr class="saving">
                        <td class="text-right text-c1">Offer:</td>
                        <td class="text-left text-c2">
                            <span id="savingPrice" data-toggle="tooltip" title="add <%=x+y%> items with same offer to the cart to get the offer"> Buy <%=x%> and get <%=y%> free</span>
                        </td>
                    </tr>

                    <tr>
                        <td class="text-right text-c1">BarCode</td>
                        <td class="text-left text-c2"><%=item.getItemBarcode()%></td>
                    </tr>
                    <%}%>

                    <tr>
                        <td></td>
                        <td>
                            <span>Inclusive of all taxes</span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <% if(item.getQuantity()>0){ %>
            <div id="availability"><span id="avail">In stock.</span></div>
            <div id="numitems"><span id="numbitems">Quantity available : <%=item.getQuantity()%></span></div>
            <%} else { %>

            <div id="availability"><span id="avail">Out of stock.</span></div>
            <%} %>


            <%--<div id="itemId" type="hidden"><%=item.getId()%></div>--%>
            <input type="hidden" value="<%=item.getId()%>" id='itemId' class='h_v'>

            <div id="seller">Sold by <a href="#" id="sellerName" data-toggle="tooltip" title="<%=item.getSeller().getId()%>"><%=item.getSeller().getUserName()%></a> </div>
            <input type="text" id = "sellerRatingHidden" value="<%=item.getSeller().getSellerRating()  %>" hidden>
            Seller Rating: <div id="rateYo"></div>
           <div id="ratingCountHidden"> Number of Ratings: <%=item.getSeller().getRatingsCount()%></div>
           <div id="ratingCountHidden"> Total Ratings: <%=item.getSeller().getTotalRatings()%></div>
            <div id="prodFeatures">
                <ul>
                    <%=item.getDescription()%>
                </ul>
            </div>
        </div>
        <div class="col-lg-3">
            <span>
                <a href="#">Share</a>
                <a id="icon1" href="#">
                    <i class="fa fa-twitter"></i>
                </a>
                <a id="icon2" href="#">
                    <i class="fa fa-facebook"></i>
                </a>
                <a id="icon3" href="#">
                    <i class="fa fa-Pinterest"></i>
                </a>
            </span>
            <img id="purchase_protection" src="/images/amazon_purchase_protection.JPG" class="img-responsive" width="248" alt="Purchase Protection">
            <div class="item-box">
                <div class="item-column">
                    <span class="dropdown">
                        <label>Quantity:</label>
                        <input class="form-control" type="number" value="1" name="quantity" id="quantity" autocomplete="off" required="true"/>
                    </span>

                </div>

                <div>
                    <input id="addtocart"type="button" class="btn1" value="Add to cart">
                </div>

                <div>
                    <input type="button" class="btn1" id="directBuy" value="Buy now">
                </div>
                <div>
                    <input type="button" class="btn1" id="moveToList" value="Save for Later">
                </div>

            </div>

        </div>
    </div>
</div>
<script src="/js/jquery.rateyo.min.js"></script>
<%@include file="footer.jsp" %>
</body>
</html>
