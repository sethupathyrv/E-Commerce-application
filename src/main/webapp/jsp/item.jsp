<%@ page import="com.ooad.web.model.Item" %><%--
  ~ Created by Harsha Raj on 18/02/18 15:37
  ~ Copyright (c) 2018. All rights reserved.
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
    <%--<script src="../js/item.js"></script>--%>
    <link rel="stylesheet" href="../css/item.css">
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
            <div id="price">
                <table>
                    <tbody>
                    <tr class="text-c1">
                        <td class="text-right text-nowrap">M.R.P:</td>
                        <td class="text-left text-nowrap">
                            <del>&#2352;</del><del><span id="originalPrice"><%= item.getPrice()%> </span></del>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-right text-c1">Price:</td>
                        <td class="text-left text-c2">
                            &#2352;<span id="currentPrice"> 2,495.00</span>
                        </td>
                    </tr>
                    <tr class="saving">
                        <td class="text-right text-c1">You Save:</td>
                        <td class="text-left text-c2">
                            &#2352;<span id="savingPrice"> 0.00 (<span id="savingPercentage">0</span>%)</span>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <span>Inclusive of all taxes</span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div id="cod">Cash on delivery <span id="cod_elgiblity">eligible.</span></div>
            <div id="availability"><span id="avail">In stock.</span></div>
            <div id="seller">Sold by <a href="#" id="sellerName">Deal Day</a></div>
            <div id="prodFeatures">
                <ul>
                    <li><span>Day And Date Display</span></li>
                    <li><span>Three hands that move in a smooth and uninterrupted fashion</span></li>
                    <li><span>2 Year Warranty</span></li>
                    <li><span>Packed In Espoir Gift Box</span></li>
                    <li><span>Works on Japanese Quartz format</span></li>
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
               <span>
                   <a href="#">
                       <div class="item-row">
                           <span id="deliverLocation">Deliver to Bangalore 560100</span>
                       </div>
                   </a>
               </span>
                <div class="item-column">
                    <span class="dropdown">
                        <label>Quantity:</label>
                        <!-- <select id="quantity" class="a-dropdown" name="quantity" autocomplete="off" tabindex="-1"></select>
                         <span id="a-autoid-5" class="a-button-small" tabindex="-1"></span>-->
                        <select>
                          <option value="one">1</option>
                          <option value="two">2</option>
                          <option value="three">3</option>
                          <option value="four">4</option>
                        </select>

                    </span>

                </div>

                <div>
                    <input type="button" class="btn1" value="Add to cart">
                </div>
                <div>
                    <input type="button" class="btn1" value="Buy now">
                </div>

            </div>

        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>