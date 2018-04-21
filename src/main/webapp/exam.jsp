<%@ page import="com.ooad.web.model.User" %>
<%@ page import="com.ooad.web.model.Cart" %>
<%@ page import="com.ooad.web.model.CartItem" %><%--
  Created by IntelliJ IDEA.
  User: sandeep
  Date: 21/4/18
  Time: 10:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<script src="../js/jquery.min.js"></script>
<script src="../js/jquery.cookie.js"></script>
<script src="/js/handlebars-v4.0.11.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="exam.js"></script>

<body>
<%User u = (User) request.getAttribute("user");%>
<%Cart cart = (Cart) request.getAttribute("cart");%>
<div class="container">
    <div class="row">
        <p class="col-sm-6">Sandeep Tadepalli</p>
        <p class="col-sm-6">IMT2014057</p>
    </div>
</div>

<div id="gui1">
    <div id="gui1Header">
        <div class="row">
            <div class="col-sm-3">Sr No</div>
            <div class="col-sm-3">ItemId</div>
            <div class="col-sm-3">Item Price</div>
        </div>
    </div>
    <%int count = 1;%>
    <%float totalPrice = 0;%>
    <%for (CartItem c: cart.getCartItems()) {%>

    <div class="row">
        <div class="col-sm-3"><%=count%></div>
        <div class="col-sm-3"><%=c.getItem().getItemBarcode()%></div>
        <div class="col-sm-3"><%=c.getItem().getPrice()%></div>
        <%count++;
          totalPrice += c.getItem().getPrice()*c.getQuantity();  %>
    </div>
    <%}%>
    <div class="row">
        <div class="col-sm-2">Total Price</div>
        <div class="col-sm-1"><%=totalPrice%></div>
        <div class="col-sm-2">VAT</div>
        <div class="col-sm-1"><%=totalPrice*4/100%></div>
    </div>
    <div class="row">
        <button id = "q2" class="col-sm-2">Exam03|Q2</button>
        <button id="q3" class="col-sm-2">Exam03|Q3</button>
        <button><a id="home"  class="col-sm-2" href="/">Home</a></button>
    </div>
</div>

<div id="gui2" hidden>
    <div id="gui2_1">
        <label for="item1">Item1*</label>
        <select name="offer" id="item1">
            <%for (CartItem c: cart.getCartItems()){ %>
                <option value="<%=c.getItem().getId()%>">
                    <img src="<%=c.getItem().getUrl()%>"style="width: 10px;height: 10px" alt=""><%=c.getItem().getItemBarcode()%>
                </option>
            <%}%>
        </select>
        <label for="item2">Item2*</label>
        <select name="offer" id="item2">
            <%for (CartItem c: cart.getCartItems()){ %>
            <option value="<%=c.getItem().getId()%>"><%=c.getItem().getItemBarcode()%></option>
            <%}%>
        </select>
        <div class="row">
            <button id = "prevGui2" class="col-sm-2">prev</button>
            <button id="submitGui2" class="col-sm-2">Submit</button>
        </div>
    </div>

    <script id="example-template" type="text/x-handlebars-template">
        <table class="table" align="center" role="presentation">
            <tr>
                <td class="col-sm-4">&nbsp;</td>
                <td><img src="{{item1.url}}" alt="" class="col-sm-4" style="height:150px;width:150px"></td>
                <td><img  class="col-sm-4" src="{{item2.url}}" alt=""style="height:150px;width:150px"></td>
            </tr>
            <tr>
                <td class="col-sm-4">Price</td>
                <td class="col-sm-4">{{item1.price}}</td>
                <td class="col-sm-4">{{item2.price}}</td>
            </tr>
            <tr>
                <td class="col-sm-4">Colour</td>
                <td class="col-sm-4">{{item1.itemColour}}</td>
                <td class="col-sm-4">{{item2.itemColour}}</td>
            </tr>
            <tr>
                <td class="col-sm-4">Categoryy</td>
                <td class="col-sm-4">{{item1.SubCategory.ItemCategory.displayName}}</td>
                <td class="col-sm-4">{{item2.SubCategory.ItemCategory.displayName}}</td>
            </tr>
            <tr>
                <td class="col-sm-4">SubCategory</td>
                <td class="col-sm-4">{{item1.SubCategory.displayName}}</td>
                <td class="col-sm-4">{{item2.SubCategory.displayName}}</td>
            </tr>
            <tr>
                <td class="col-sm-4">Name</td>
                <td class="col-sm-4">{{item1.name}}</td>
                <td class="col-sm-4">{{item2.name}}</td>
            </tr>
        </table>
    </script>
    <div id="gui2_2">
        <div id="compareItems">
        </div>
        <button id="gui2_2_button">Prev</button>
    </div>



</div >

<div id="gui3" hidden>
    <div class="row center-block">&nbsp;&nbsp;Seller details of items in shopping cart</div>
    <%int sellerCount = 1;%>
    <div class="row">
        <div class="col-sm-3">Sr No</div>
        <div class="col-sm-3">Item Id</div>
        <div class="col-sm-3">Seller Id</div>
        <div class="col-sm-3">Seller Address</div>
    </div>
    <% for (CartItem c:cart.getCartItems()) {%>
    <div class="row">
        <div class="col-sm-3"><%=sellerCount%></div>
        <div class="col-sm-3"><%=c.getItem().getItemBarcode()%></div>
        <div class="col-sm-3"><%=c.getItem().getSeller().getUserName()%></div>
        <div class="col-sm-3"><%=c.getItem().getSeller().getAddress()%></div>
        <%sellerCount++;%>
    </div>
    <%}%>
    <button id="gui3_prev">Prev</button>
</div>



</body>
</html>
