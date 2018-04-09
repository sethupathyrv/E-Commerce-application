<%@ page import="com.ooad.web.dao.ItemCategoryDao" %>
<%@ page import="com.ooad.web.model.ItemCategory" %>
<%@ page import="com.ooad.web.utils.TokenAuth" %>
<%@ page import="com.ooad.web.model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ooad.web.model.ItemSubCategory" %>
<% Cookie[] cookies = request.getCookies();
   User user = null;
   if(cookies != null){
       for(Cookie cookie: cookies){
           if(cookie.getName().equals("authToken")){
                user = TokenAuth.getUserFromToken(cookie.getValue());
           }
       }
   }
%>
<link rel="stylesheet" href="/css/fontawesome-all.min.css">
<link rel="stylesheet" href="">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-dropdownhover.css">
<link rel="stylesheet" href="../css/header.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script src="../js/bootstrap-dropdownhover.js"></script>
<script src="js/header.js"></script>
<header class="container-fluid header">
    <div class="row nav-belt ">
        <div class="logo col-lg-2 col-md-2">
            <a href="/" class="nav-logo-link"><img src="../images/logo.png" alt=""></a>
        </div>
        <div class="col-lg-6 col-md-6">
            <div class="row  search-box ">
                <form action="" id="item-search" style="height: inherit">
                    <div class="col-lg-2 col-md-2 search-category">
                        <select name="" id="nav-search-select">
                            <option value="all">All Categories</option>
                            <% final ItemCategoryDao itemCategoryDao = new ItemCategoryDao();
                                ArrayList<ItemCategory> itemCategories = (ArrayList<ItemCategory>) itemCategoryDao.getAllCategories();
                                for (ItemCategory itemCategory : itemCategories) { %>
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
                                <span style="font-size: 12px;font-weight: 400; ">Deliver to
                                    <%= (user==null)? "" : user.getUserName() %> </span>
                                <br><p id="loc"></p>
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
                            <%for (ItemCategory itemCategory : itemCategories) { %>
                            <li class="dropdown">
                                <a href="#"><%=itemCategory.getDisplayName()%></a>
                                <% if(itemCategory.getDisplayName().equals("Others")) continue;%>
                                <ul class="dropdown-menu">
                                    <% ArrayList<ItemSubCategory> itemsubCategories = (ArrayList<ItemSubCategory>) itemCategoryDao.getAllsubCategories(itemCategory.getId());
                                        for (ItemSubCategory itemsubCategory : itemsubCategories) { %>
                                        <li><a href="/items?category=<%=itemCategory.getName()%>&subcategory=<%=itemsubCategory.getDisplayName()%>"><%=itemsubCategory.getDisplayName()%></a></li>
                                    <%}%>
                                </ul>
                            </li>
                            <%}%>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-5 col-md-6">
            <div class="row nav-fill">
                <br>
                <a href="#">
                    <%= (user==null)? "your":user.getUserName()%>
                    Amazon.in</a> &nbsp;&nbsp;&nbsp;
                <a href="/amazonpay">Amazon Pay</a>&nbsp;&nbsp;&nbsp;
                <a href="/sellerlogin">Sell</a>&nbsp;&nbsp;&nbsp;
                <% if(user!=null && user.getEmailId().equals("admin@admin.com")) { %>
                    <a href="/admin">admin</a> &nbsp;&nbsp;
                <%}%>
            </div>
        </div>
        <div class="col-lg-4 col-md-3">
            <div class="row nav-left">
                <div class="col-md-4 nav-border-round" id="nav-account">
                    <% if(user == null) {%>
                        <a href="/login">
                            <span class="nav-line-1">Hello, Sign in </span><br>
                            <span class="nav-line-2">Your Orders</span>
                            <i class="fa fa-caret-down" aria-hidden="true"></i>
                        </a>
                    <% } else { %>
                    <div class="dropdown nav-border-round">
                        <a href="#" class="dropdown-toggle" id = "nav-signIn" data-toggle="dropdown" data-hover="dropdown">
                            <span class="nav-line-1">Hello <%= user.getUserName()%> </span><br>
                            <span class="nav-line-2">Your Orders</span>
                            <i class="fa fa-caret-down" aria-hidden="true"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#" id="nav-logout">Logout</a></li>
                            <li><a href="#">Your account</a></li>
                            <li><a href="/list">Your List</a></li>
                            <li><a href="/myorders">Your Orders</a></li>
                        </ul>
                    </div>
                    <%}%>
                </div>
                <div class="col-md-2 nav-border-round" id="nav-prime">
                    <a href="#">
                        <span class="nav-line-1">Try</span><br>
                        <span class="nav-line-2"> Prime</span>
                        <i class="fa fa-caret-down" aria-hidden="true"></i>
                    </a>
                </div>
                <div class="col-md-3 nav-border-round" id="nav-yourlist">
                    <a href="/list">
                        <span class="nav-line-1">Your </span><br>
                        <span class="nav-line-2">Lists </span> <i class="fa fa-caret-down" aria-hidden="true"></i>
                    </a>
                </div>
                <a href="/cart">
                    <div class="col-md-3 cart">
                        <span id="cart-contents"><%=(user==null)? 0:user.getCart().size()%></span>
                    </div>
                </a>
            </div>
        </div>
    </div>
</header>