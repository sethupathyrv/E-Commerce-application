<%@ page import="com.ooad.web.model.Item" %>
<%@ page import="com.ooad.web.model.User" %>
<%@ page import="com.ooad.web.utils.TokenAuth" %>
<%@ page import="com.ooad.web.model.Seller" %><%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 19/2/18
  Time: 7:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sell on Amazon</title>
    <% Item item = (Item) request.getAttribute("item"); %>
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
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type = "text/javascript" src = "http://cdn.jsdelivr.net/jquery.cookie/1.3.1/jquery.cookie.js"></script>
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
                        <li class="active"><a href="/seller">Dashboard</a></li>
                        <li><a href="/infoseller">Seller Information </a></li>
                        <li><a href="/seller/add">Add Item</a></li>
                        <li><a href="#" id="sellerLogout"> Logout</a></li>
                        <li><a href="/infoseller"> <%=seller.getUserName()%></a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>

    <div class="col-md-6">
        <div class="a-section">
            <div class="a-box">
                <div class="a-box-inner a-padding-extra-large">
                    <h1 class="a-spacing-small">
                        Items added by you
                    </h1>

                    <table>
                        <thead>
                        <tr>
                            <th>
                                <td>Item ID</td>
                                <td>Item Name</td>
                            </th>
                        </tr>

                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="checkbox"></td>
                            <td><%=session.getAttribute("id")%></td>

                        </tr>
                        </tbody>
                    </table>
                    <div>
                        <input type="button" class="btn1" value="Delete">
                    </div>

                    <div class="a-row a-spacing-base">

    <%-- ........Display list of items added by seller (Under construction) ........--%>
                            <%--<input type="button" onclick="CreateTableFromJSON()" value="Create Table From JSON" />
                            <div id="showData"></div>

                            <script>
                                function CreateTableFromJSON() {
                                    var myBooks = [
                                        {"id":"1","name":"pen","itemPrice":"20","offer":"10%","subSubCategory":"001","description":"reynolds","url":"C:\\fakepath\\pen.jpg"}
                                    ]

                                    // EXTRACT VALUE FOR HTML HEADER.
                                    // ('Book ID', 'Book Name', 'Category' and 'Price')
                                    var col = [];
                                    for (var i = 0; i < myBooks.length; i++) {
                                        for (var key in myBooks[i]) {
                                            if (col.indexOf(key) === -1) {
                                                col.push(key);
                                            }
                                        }
                                    }

                                    // CREATE DYNAMIC TABLE.
                                    var table = document.createElement("table");

                                    // CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

                                    var tr = table.insertRow(-1);                   // TABLE ROW.

                                    for (var i = 0; i < col.length; i++) {
                                        var th = document.createElement("th");      // TABLE HEADER.
                                        th.innerHTML = col[i];
                                        tr.appendChild(th);
                                    }

                                    // ADD JSON DATA TO THE TABLE AS ROWS.
                                    for (var i = 0; i < myBooks.length; i++) {

                                        tr = table.insertRow(-1);

                                        for (var j = 0; j < col.length; j++) {
                                            var tabCell = tr.insertCell(-1);
                                            tabCell.innerHTML = myBooks[i][col[j]];
                                        }
                                    }

                                    // FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
                                    var divContainer = document.getElementById("showData");
                                    divContainer.innerHTML = "";
                                    divContainer.appendChild(table);
                                }
                            </script>--%>


     <%--...........Script to print values of recent item added........... --%>
                            <span id = "lblData"></span>

                            <script type="text/javascript">
                                $(function () {
                                    if ($.cookie("id") != null && $.cookie("name") != null) {
                                        var data = "<!--<u>Values from Cookies</u>--><br /><br />";
                                        data += "<b>Id:</b> " + $.cookie("id") + " <b>Name:</b> " + $.cookie("name");
                                        $("#lblData").html(data);
                                        $.removeCookie("id");
                                        $.removeCookie("name");
                                    }
                                });
                            </script>



                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
