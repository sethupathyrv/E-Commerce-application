<%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 19/2/18
  Time: 9:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ooad.web.model.Seller" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sell on Amazon</title>

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
    <script src="../js/infoSeller.js"></script>
    <script src="../js/jquery.cookie.js"></script>

    <% Seller seller = (Seller) request.getAttribute("seller"); %>

</head>

<body>

<div class="container-fluid">
    <%-- ..........Logo header...........--%>
    <div class="row nav-belt ">
        <header class="container-fluid header">

            <div class="logo col-lg-2 col-md-2">
                <a href="#" class="nav-logo-link">
                    <img src="https://images-na.ssl-images-amazon.com/images/G/31/rainier/nav/sc-unified._CB360962420_.png">
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
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <!-- Collection of nav links and other content for toggling -->
                <div id="navbarCollapse" class="collapse navbar-collapse">
                    <ul class="nav nav-tabs">
                        <li><a href="/seller">Dashboard</a></li>
                        <li class="active"><a href="/infoseller">Seller Information </a></li>
                        <li><a href="/seller/add">Add Item</a></li>
                        <li><a href="#" id="sellerLogout"> Logout</a></li>
                        <li><a href="/infoseller"> <%=seller.getUserName()%></a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>


    <%--............Form and button.............--%>

        <div class="col-md-6">
            <div class="a-section">
                <div class="a-box">
                    <div class="a-box-inner a-padding-extra-large">

                        <h1 class="a-spacing-small">Your Details</h1>

                        <div class="a-row a-spacing-base">

                            <div>Username : <%=seller.getUserName()%></div>
                            <div>Email :  <%=seller.getEmailId()%></div>
                            <div>Store Name :  <%=seller.getStoreName()%></div>
                            <div>Mobile Number : <%=seller.getMobileNumber()%></div>
                            <div>Street Address : <%=seller.getStreetAddress()%></div>
                            <div>Landmark : <%=seller.getLandmark()%></div>
                            <div>City : <%=seller.getCity()%></div>
                            <div>State : <%=seller.getState()%></div>
                            <div>Pincode : <%=seller.getPincode()%></div>
                            <div>Country : <%=seller.getCountry()%></div>

                        </div>

                        <div class="a-section">
                            <span id="continue" class="a-button a-button-span12 a-button-primary">
                                <span class="a-button-inner">
                                    <a href="/sellerupdate">
                                    <input tabindex="5" class="a-button-input" id="update" type="button" aria-labelledby="continue-announce">
                                        <span id="continue-announce" class="a-button-text" aria-hidden="true">
                                            Update
                                        </span>
                                     </a>
                                </span>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

</div>
</body>
</html>