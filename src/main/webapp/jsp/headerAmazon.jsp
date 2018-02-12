<%@ page import="com.ooad.web.dao.ItemCategoryDao" %>
<%@ page import="com.ooad.web.model.ItemCategory" %><%--
  ~ Created by Shravan on  4/2/18 7:46 PM.
  ~ Copyright (c) 2018. All rights reserved.
  --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="css/headerAmazon.css"/>
<script src="js/header.js"></script>

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
                <%
                    if (session.getAttribute("username") == null) { %>
                <span>Hello. Sign in</span>
                <%
                } else { %>
                <span>hello <%=session.getAttribute("username")%></span>
                <%
                    }
                %>


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

