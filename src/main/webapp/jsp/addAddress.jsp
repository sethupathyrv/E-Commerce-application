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
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/addAddress.js"></script>
    <%--<script src="../js/item.js"></script>--%>
    <link rel="stylesheet" href="../css/item.css">
    <% Item item = (Item) request.getAttribute("item"); %>
</head>
<%@include file="header.jsp" %>
<body>
<%--............Form and button.............--%>
<form id="addAddressForm" action="#" method="post" enctype="multipart/form-data">
    <div class="col-md-5">
        <div class="a-section">
            <div class="a-box">
                <div class="a-box-inner a-padding-extra-large">
                    <h1 class="a-spacing-small">Add new address</h1>

                    <div class="a-row a-spacing-base">


                        <label for="fullName" class="a-form-label">Full Name</label>
                        <input class="form-control" type="text" name="fullname" id="fullName" autocomplete="off"/>

                        <label for="mobileNumber" class="a-form-label">mobile number</label>
                        <input class="form-control" type="text" name="mobileNumber" id="mobileNumber" autocomplete="off"/>

                        <label for="pincode" class="a-form-label">pincode</label>
                        <input class="form-control" type="text" name="pincode" id="pincode" autocomplete="off" />

                        <label for="streetAddress" class="a-form-label">Street Address</label>
                        <textarea class="form-control" id="streetAddress" name="streetAddress" rows="5" cols="30"></textarea>

                        <label for="landmark" class="a-form-label">landmark</label>
                        <input class="form-control" type="text" name="landmark" id="landmark" autocomplete="off" />

                        <label for="pincode" class="a-form-label">city</label>
                        <input class="form-control" type="text" name="city" id="city" autocomplete="off" />

                        <label for="state" class="a-form-label">state</label>
                        <input class="form-control" type="text" name="state" id="state" autocomplete="off" />

                    </div>
                </div>
            </div>
        </div>

        <div class="a-section">
            <span id="continue" class="a-button a-button-span12 a-button-primary">
                <span class="a-button-inner">
                    <input tabindex="5" id="btnCookie" class="a-button-input" type="submit" aria-labelledby="continue-announce">
                        <span id="continue-announce" class="a-button-text" aria-hidden="true">
                            Add address
                        </span>
                </span>
            </span>
        </div>
    </div>

</body>
</html>