<%@ page import="com.ooad.web.dao.ItemCategoryDao" %>
<%@ page import="com.ooad.web.model.ItemCategory" %>
<!DOCTYPE html>
<!--
~ Created by Sandeep Tadepalli on 10/02/18 15:05
~ Copyright (c) 2018. All rights reserved.
-->

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-dropdownhover.css">
<link rel="stylesheet" href="../css/header.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script src="../js/bootstrap-dropdownhover.js"></script>
<header class="container-fluid header">
    <div class="row nav-belt ">
        <div class="logo col-lg-2 col-md-2">
            <a href="#" class="nav-logo-link"><img src="../images/logo.png" alt=""></a>
            <a href="#" class="nav-logo-tagline">Try Prime</a>
        </div>
        <div class="col-lg-6 col-md-6">
            <div class="row  search-box ">
                <form action="" id="item-search" style="height: inherit">
                    <div class="col-lg-2 col-md-2 search-category">
                        <select name="" id="nav-search-select">
                            <option value="all">All Categories</option>
                            <% final ItemCategoryDao itemCategoryDao = new ItemCategoryDao();
                                for (ItemCategory itemCategory : itemCategoryDao.getAllCategories()) { %>
                            <option value="<%= itemCategory.getName()%>"><%= itemCategory.getDisplayName()%>
                            </option>
                            <% }%>
                            <option>All Categories</option>
                        </select>
                    </div>
                    <input class="col-lg-9 col-md-9 search-input" type="text">
                    <div class="col-lg-1 col-md-1 search-icon" id="search-icon">
                        <i class="fa fa-search fa-1g pull-right icn" aria-hidden="true"></i>
                    </div>
                </form>

            </div>
        </div>
        <div class="col-lg-4 col-md-4">
            <img src="../images/amazon_app_quiz.jpg" alt="">
        </div>
    </div>
    <div class="row nav-main">
        <div class="col-lg-3 col-md-3">
            <div class="row nav-left">
                <div class="col-md-6 nav-border-round">
                    <div class="row">
                        <div class="col-md-2 " id="map-location-picker">
                            <img src="../images/location.png" alt="">
                        </div>
                        <div class="col-md-10" id="nav-deliver-div">
                            <a href="#" id="nav-deliver">
                                <span style="font-size: 12px;font-weight: 400; ">Deliver to Tadepalli </span>
                                <br>Bangalore 560100
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="dropdown nav-border-round">
                        <a class="dropdown-toggle" type="button" id="nav-shopby" data-toggle="dropdown"
                           data-hover="dropdown">
                            <span style="font-size: 12px; font-weight: 400">shop by</span>
                            <br>Category <i class="fa fa-caret-down" aria-hidden="true"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li class="dropdown">
                                <a href="#">One more dropdown</a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Action</a></li>
                                    <li><a href="#">Another action</a></li>
                                    <li class="dropdown">
                                        <a href="#">One more dropdown</a>
                                        <ul class="dropdown-menu">
                                            ...
                                        </ul>
                                    </li>
                                    <li><a href="#">Something else here</a></li>
                                    <li><a href="#">Separated link</a></li>
                                </ul>
                            </li>
                            <li><a href="#">Something else here</a></li>
                            <li><a href="#">Separated link</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-5 col-md-6">
            <div class="row nav-fill">
                <br>
                <a href="#">Sandeep's Amazon.in</a> &nbsp;&nbsp;&nbsp;
                <a href="#">Today's deals</a>&nbsp;&nbsp;&nbsp;
                <a href="#">Amazon Pay</a>&nbsp;&nbsp;&nbsp;
                <a href="#">Sell</a>&nbsp;&nbsp;&nbsp;
                <a href="#">Customer Service</a> &nbsp;&nbsp;

                <!--<div class="col-md-3">
                    <a href="#" >Sandeep's Amazon.in</a>
                </div>
                <div class="col-md-3">
                    <a href="#" >Today's deals</a>
                </div>
                <div class="col-md-2">
                    <a href="#" >Amazon Pay</a>
                </div>
                <div class="col-md-1">
                    <a href="#">Sell</a>
                </div>
                <div class="col-md-3">
                    <a href="#">Customer Service</a>
                </div>-->


            </div>
        </div>
        <div class="col-lg-4 col-md-3">
            <div class="row nav-left">
                <div class="col-md-4 nav-border-round" id="nav-account">
                    <a href="#">
                        <span class="nav-line-1">Hello, Sandeep </span><br>
                        <span class="nav-line-2">Your Orders</span>
                        <i class="fa fa-caret-down" aria-hidden="true"></i>
                    </a>
                </div>
                <div class="col-md-2 nav-border-round" id="nav-prime">
                    <a href="#">
                        <span class="nav-line-1">Try</span><br>
                        <span class="nav-line-2"> Prime</span>
                        <i class="fa fa-caret-down" aria-hidden="true"></i>
                    </a>
                </div>
                <div class="col-md-3 nav-border-round" id="nav-yourlist">
                    <a href="#">
                        <span class="nav-line-1">Your </span><br>
                        <span class="nav-line-2">Lists </span> <i class="fa fa-caret-down" aria-hidden="true"></i>
                    </a>
                </div>
                <div class="col-md-3 cart">
                    <span id="cart-contents">0</span>
                </div>
            </div>
        </div>
    </div>
</header>
<script>
    $("#search-icon").click(function () {
        $("#item-search").submit();
    });
</script>

</body>
</html>