<%--
  Created by IntelliJ IDEA.
  User: sandeep
  Date: 29/3/18
  Time: 1:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">--%>
<script src="/js/handlebars-v4.0.11.js"></script>
<script src="/js/url-v2.5.2.js"></script>
<link rel="stylesheet" href="/css/items.css">
<link rel="stylesheet" href="/css/jquery.rateyo.min.css"/>
<script src="js/bootstrap.min.js"></script>
<div class="container-fluid">
    <div class="row col-sm-2 filter">
        <div class="row"><h4 class="filterQuantifier pull-left">Price</h4></div>
        <div class="row priceInputRow pull-left">
            <input type="text" class="col-sm-3 filterInput" id="minPrice" placeholder="Min" maxlength="9"
                   name="minPrice">
            <input type="text" class="col-sm-3 filterInput" id="maxPrice" placeholder="Max" maxlength="9"
                   name="maxPrice">
        </div>
        <br><br>
        <select id="color" onchange="sort()">
            <option value="000">Color</option>
            <option value="Red">Red</option>
            <option value="Black">Black</option>
        </select>
        <button class="filterButton" id="priceFilterButton" onclick="applyPriceFilter()">Go</button>
        <br><br>
        <div class="row">
            <h4 class="filterQuantifier pull-left">Sort By</h4>
        </div>
        <div class="row">
            <select id="sort" onchange="sort()">
                <option value="000">-Sort by-</option>
                <option value="price:asc">Price (low to high)</option>
                <option value="price:dec">Price (high to low)</option>
            </select>
        </div>
        <div class="row"><a class="btn" onclick="clearFilter()">Clear Filters</a></div>
    </div>
    <div id="items" class="col-sm-10">

    </div>
</div>

<script id="example-template" type="text/x-handlebars-template">
    {{#each items}}
    <div class="col-sm-4 item">
        <a href="/item?id={{id}}"><img src="{{url}}" alt="" class="productImage"></a>
        <h2 class="productName">{{name}}</h2>
        <p class="productSeller"> by {{seller.username}}</p>
        <p class="productPrice">&#2352; {{price}}</p>
        Seller Rating: <div class="rateYo">
        <p hidden id="sellerRating">{{seller.rating}}</p>
        <p hidden id="sellerRatingCount">{{seller.ratingCount}}</p>
    </div>
    <%--<img src="/images/fake_reviews.png" alt="" data-toggle="tooltip" title="Out of the scope of course">--%>
    </div>
    {{/each}}
</script>
<%@include file="footer.jsp" %>
<script src="/js/jquery.rateyo.min.js"></script>
<script src="/js/items.js"></script>

</body>
</html>
