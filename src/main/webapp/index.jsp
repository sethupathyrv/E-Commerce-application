<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ooad.web.model.Item" %>
<%@ page import="java.io.IOException" %><%--
  ~ Created by Sandeep Tadepalli on 04/02/18 03:44
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
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/index.css">

    <script src="js/index.js"></script>
</head>
<body>
<%@include file="jsp/header.jsp" %>
<% ArrayList<Item> items = Item.getLastFive();%>
<section id="promo">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div id="promo-carousel" class="carousel slide" data-ride="carousel">

                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#promo-carousel" data-slide-to="0" class="active"></li>
                        <li data-target="#promo-carousel" data-slide-to="1"></li>
                        <li data-target="#promo-carousel" data-slide-to="2"></li>
                        <li data-target="#promo-carousel" data-slide-to="3"></li>
                        <li data-target="#promo-carousel" data-slide-to="4"></li>
                        <li data-target="#promo-carousel" data-slide-to="5"></li>
                    </ol>
                    <!-- Wrapper for slides -->
                    <div class="carousel-inner">
                        <div class="item active">
                            <img src="../images/carousel/Echo alexa.jpg" alt="Echo alexa" style="width:120%;">
                        </div>

                        <div class="item">
                            <img src="../images/carousel/Index.png" alt="Index" style="width:100%;">
                        </div>

                        <div class="item">
                            <img src="../images/carousel/Redmi 4.png" alt="Redmi 4 " style="width:100%;">
                        </div>

                        <div class="item">
                            <img src="../images/carousel/Shave it of.png" alt="Shave it of" style="width:100%;">
                        </div>

                        <div class="item">
                            <img src="../images/carousel/Gaming .png" alt="Gaming " style="width:100%;">
                        </div>

                        <div class="item">
                            <img src="../images/carousel/bottle .jpg" alt="bottles and flasks " style="width:100%;">
                        </div>

                        <!-- Controls -->
                        <a class="left carousel-control" href="#promo-carousel" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#promo-carousel" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<div class="container-fluid">
    <div class='row'>
        <div class='col-md-8'>
            <div class="carousel slide media-carousel" id="media">
                <div class="carousel-inner">
                    <%
                        int i = 0,j=0;
                        while(i < items.size()){
                            if(i%3==0){ %>
                                <div class="item  <%= (i==0) ? "active":""%>">
                                    <div class="row">
                                    <%
                                        while(i<items.size() && j <3) {
                                            final Item item = items.get(i); %>
                                            <div class="col-md-4">
                                                <div class='card'>
                                                    <img class='card-img-top' src='<%= item.getUrl() %>' alt='card image cap' style="height: 160px; width: 160px">
                                                    <div class="card-body">
                                                        <h5 class="card-title"><%=item.getName()%></h5>
                                                        <p class="card-text">Price <%=item.getPrice()%></p>
                                                        <a href="/item?id=<%=item.getId()%>">Buy</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <%i++;
                                            j++;
                                        }
                                        j=0;
                                    %>
                                    </div>
                                </div>
                            <%}
                        }%>
                    <a data-slide="prev" href="#media" class="left carousel-control"> < </a>
                    <a data-slide="next" href="#media" class="right carousel-control"> > </a>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <a href="/report">Report</a>
    </div>
</div>
<%@include file="jsp/footer.jsp" %>
</body>
</html>