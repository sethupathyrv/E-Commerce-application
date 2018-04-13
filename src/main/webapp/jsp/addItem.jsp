<%@ page import="com.ooad.web.utils.TokenAuth" %>
<%@ page import="com.ooad.web.model.Seller" %><%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 17/2/18
  Time: 1:20 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--
~ Created by Sandeep Tadepalli on 10/02/18 15:05
~ Copyright (c) 2018. All rights reserved.
-->

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sell on Amazon</title>
    <%Cookie[] cookies = request.getCookies();
        Seller seller = null;
        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("sellerAuthToken")){
                    seller = TokenAuth.getSellerFromToken(cookie.getValue());
                }
            }
        }%>
</head>
<body>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-dropdownhover.css">
<link rel="stylesheet" href="../css/amazon1.css"/>
<link rel="stylesheet" href="../css/amazon2.css"/>
<link rel="stylesheet" href="../css/homepageSeller.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script src="../js/bootstrap-dropdownhover.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../js/jquery.cookie.js"></script>
<script src="../js/addItem.js"></script>

<div class="container-fluid">
<%-- ..........Logo header...........--%>
<div class="row nav-belt ">
    <header class="container-fluid header">

        <div class="logo col-lg-2 col-md-2">
            <a href="#" class="nav-logo-link">
                <img src="https://images-na.ssl-images-amazon.com/images/G/31/rainier/nav/sc-unified._CB360962420_.png">
            </a>
        </div>

        <div class="logo col-lg-2 col-md-2 right">
            <a href="/" class="nav-logo-link">
                <img src="/images/logo.png" alt="Home" style="background-color: black">
            </a>
        </div>

    </header>
</div>

<%--.........Navbar................--%>
<div class="row bar">
    <nav class="navbar navbar-default navbar-fixed">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <!-- Collection of nav links and other content for toggling -->
            <div id="navbarCollapse" class="collapse navbar-collapse">
                <ul class="nav nav-tabs">
                    <li><a href="/seller">Dashboard</a></li>
                    <li><a href="/infoseller">Seller Information </a></li>
                    <li class="active"><a href="/seller/add">Add Item</a></li>
                    <li><a href="#" id="sellerLogout"> Logout</a></li>
                    <li><a href="/infoseller"> <%=seller.getUserName()%></a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<%--............Form and button.............--%>
<form id="addItemForm" action="#" method="post" enctype="multipart/form-data">
    <div class="col-md-5">
        <div class="a-section">
            <div class="a-box">
                <div class="a-box-inner a-padding-extra-large">
                    <h1 class="a-spacing-small">Create your Amazon listings</h1>

                    <div class="a-row a-spacing-base">


                        <label for="name" class="a-form-label">Name*</label>
                        <input class="form-control" type="text" name="name" id="name" autocomplete="off" required="true"/>

                        <label for="itemPrice" class="a-form-label">Price*</label>
                        <input class="form-control" type="number" name="itemPrice" id="itemPrice" autocomplete="off" required="true"/>

                        <label for="quantity" class="a-form-label">Quantity*</label>
                        <input class="form-control" type="number" name="quantity" id="quantity" autocomplete="off" required="true"/>


                        <label id="description" class="a-form-label">Item Description</label>

                        <textarea class="form-control" id="itemDescription" name="description" rows="5" cols="30"></textarea>

                        <label for="brand" class="a-form-label">Brand*</label>
                        <input class="form-control" type="text" name="brand" id="brand" autocomplete="off" required="true"/>

                        <label for="itemHeight" class="a-form-label">Height*</label>
                        <input class="form-control" type="number" name="itemHeight" id="itemHeight" autocomplete="off" required="true"/>

                        <label for="itemWidth" class="a-form-label">Width*</label>
                        <input class="form-control" type="number" name="itemWidth" id="itemWidth" autocomplete="off" required="true"/>

                        <label for="itemBarcode" class="a-form-label">Item Barcode*</label>
                        <input class="form-control" type="number" name="itemBarcode" id="itemBarcode" autocomplete="off" required="true"/>

                        <label for="itemColour" class="a-form-label">Colour*</label>
                        <select name="itemColour" id="itemColour">
                            <option value="-1">Colour</option>
                            <option value="Red">Red</option>
                            <option value="Black">Black</option>
                            <option value="Green">Green</option>
                        </select>

                        <label>Category*:</label>
                        <select id="category" name="category">
                            <option value="000">-Select Category-</option>
                        </select>


                        <label>subCategory*:</label>
                        <select id="subCategory" name="subCategory">
                            <option value="000">-Select subCategory-</option>
                        </select>

                        <label>Offer*</label>
                        <select name="offer" id="offer">
                            <option value="-1">No Offer</option>
                            <option value="201">Discount Offer</option>
                            <option value="202">Price Offer</option>
                            <option value="203">Buy X Get Y free</option>
                        </select>

                        <div id="date" hidden>
                        <label for="startDate" class="a-form-label">Start Date*</label>
                        <input type="date" id="startDate" value="2018-04-13" >
                        <label for="endDate" class="a-form-label">End Date*</label>
                        <input type="date" id="endDate" value="2018-04-13" >
                        </div>

                        <div id="discountOfferDiv" hidden>
                            <label for="discountOffer" class="a-form-label">Discount Percentage*</label>
                            <input type="number" id="discountOffer" value="0" class="form-control" required="true">
                        </div>
                        <div id="priceOfferDiv" hidden>
                            <label for="priceOffer" class="a-form-label">Price Offer*</label>
                            <input type="number" id="priceOffer" value="0" class="form-control" required="true">
                        </div>

                        <div id="bundleOfferDiv" hidden>
                            Buy X*: <input type="number" id="bundleOfferX" class="form-control" value="0">
                            Get Y*:<input type="number" id="bundleOfferY" class="form-control" value="0">
                        </div>

                        <label for="itemImage" class="a-form-label">Image*</label>

                        <input class="form-control" type="file" name="itemImage" id="itemImage" required="true" />

                    </div>
                </div>
            </div>
        </div>

        <div class="a-section">
            <span id="continue" class="a-button a-button-span12 a-button-primary">
                <span class="a-button-inner">
                    <input tabindex="5" id="btnCookie" class="a-button-input" type="submit" aria-labelledby="continue-announce">
                        <span id="continue-announce" class="a-button-text" aria-hidden="true">
                            Submit
                        </span>
                </span>
            </span>
        </div>
    </div>

</form>

</div>
</body>
</html>