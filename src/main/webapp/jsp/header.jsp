<%@ page import="com.ooad.web.dao.ItemCategoryDao" %>
<%@ page import="com.ooad.web.model.ItemCategory" %><%--
  ~ Created by Shravan on  4/2/18 7:46 PM.
  ~ Copyright (c) 2018. All rights reserved.
  --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs &amp; more</title>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="css/header.css"/>
</head>
<body>
<!-- Main body -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>

<header>
    <div id="nav-belt">
        <div class="nav-left">
            <div id="nav-logo">
                <a href="#" class="nav-logo-link"></a>
                <a href="#" class="nav-logo-tagline">Try Prime</a>
            </div>
        </div>
        <div class="nav-right">
            <div id="nav-holiday">
                <a href="#"></a>
            </div>
        </div>
        <div class="nav-fill">
            <div id="nav-search">
                <form>
                    <div class="nav-left">
                        <span class="nav-search-label">All</span>
                        <i class="fa fa-caret-down" aria-hidden="true"></i>
                        <select id="nav-search-select">
                            <% final ItemCategoryDao itemCategoryDao = new ItemCategoryDao();
                                for (ItemCategory itemCategory : itemCategoryDao.getAllCategories()) { %>
                            <option value="<%= itemCategory.getName()%>"><%= itemCategory.getDisplayName()%>
                            </option>
                            <% }%>
                        </select>
                    </div>
                    <div class="nav-right">
                        <i class="fa fa-search" aria-hidden="true"></i>
                        <input type="submit">
                    </div>
                    <div class="nav-fill">
                        <input type="text" autocomplete="off">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <nav id="nav-main">
        <div class="nav-left">
            <div class="nav-deliver">
                <a class="nav-a" href="#">
                    <span>Deliver to</span>
                    Bengaluru 560100
                </a>
            </div>
            <div class="nav-shop">
                <a class="nav-a" href="#">
                    <span>Shop by</span>
                    Category
                    <i class="fa fa-caret-down" aria-hidden="true"></i>
                </a>
            </div>
        </div>
        <div class="nav-right">
            <a class="nav-a" href="#">
                <span>Hello. Sign in</span>
                Your Orders
                <i class="fa fa-caret-down" aria-hidden="true"></i>
            </a>

            <a class="nav-a" href="#">
                <span>Try</span>
                Prime
                <i class="fa fa-caret-down" aria-hidden="true"></i>
            </a>

            <a class="nav-a" href="#">
                <span>Your</span>
                Lists
                <i class="fa fa-caret-down" aria-hidden="true"></i>
            </a>


            <a class="nav-a cart" href="#">
                <span>0</span>
                Cart
            </a>
        </div>
        <div class="nav-fill">
            <ul>
                <li><a href="#">Your Amazon.in</a></li>
                <li><a href="#">Today's Deals</a></li>
                <li><a href="#">Gift Cards &amp; Registry</a></li>
                <li><a href="#">Sell</a></li>
                <li><a href="#">Help</a></li>
            </ul>
        </div>
    </nav>
</header>

</body>
</html>
