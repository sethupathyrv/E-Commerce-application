<%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 17/2/18
  Time: 1:20 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Sell on Amazon</title>
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
<script type="text/javascript" src="http://cdn.jsdelivr.net/jquery.cookie/1.3.1/jquery.cookie.js"></script>

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
                </button>
            </div>
            <!-- Collection of nav links and other content for toggling -->
            <div id="navbarCollapse" class="collapse navbar-collapse">
                <ul class="nav nav-tabs">
                    <li><a href="/jsp/homepageSeller.jsp">Dashboard</a></li>
                    <li><a href="/jsp/infoSeller.jsp">Seller Information </a></li>
                    <li class="active"><a href="/jsp/addItem.jsp">Add Item</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<%--............Form and button.............--%>
<%--<form action="additem" method="post" enctype="multipart/form-data">--%>
<form id="test" action="#" method="post">
    <div class="col-md-5">
        <div class="a-section">
            <div class="a-box">
                <div class="a-box-inner a-padding-extra-large">
                    <h1 class="a-spacing-small">Create your Amazon listings</h1>

                    <div class="a-row a-spacing-base">

                        <label for="id" class="a-form-label">Id</label>
                        <input class="form-control" type="text" name="id" id="id" autocomplete="off"/>

                        <label for="name" class="a-form-label">Name</label>
                        <input class="form-control" type="text" name="name" id="name" autocomplete="off"/>

                        <label for="price" class="a-form-label">Price</label>
                        <input class="form-control" type="text" name="price" id="price" autocomplete="off"/>

                        <label for="quantity" class="a-form-label">Quantity</label>
                        <input class="form-control" type="text" name="quantity" id="quantity" autocomplete="off" />

                        <label for="offer" class="a-form-label">Offer</label>
                        <input class="form-control" type="text" name="offer" id="offer" autocomplete="off" />


                        <label for="sel" class="a-form-label">Sub-Sub Category</label>

                        <select id="sel" name="subSubCategory" onchange="show(this)">
                            <option value="">-- Select --</option>
                        </select>
                        <p id="msg"></p>

                        <label id="itemDescription" class="a-form-label">Item Description</label>

                        <textarea class="form-control" name="itemDescription" rows="5" cols="30"></textarea>


                        <label for="url" class="a-form-label">Image url</label>

                        <input class="form-control" type="file" name="url" id="url" />

                    </div>
                </div>
            </div>
        </div>

        <div class="a-section">
            <span id="continue" class="a-button a-button-span12 a-button-primary">
                <span class="a-button-inner">
                    <input tabindex="5" id="btnCookie" class="a-button-input" type="submit" aria-labelledby="continue-announce">
                        <span id="continue-announce" class="a-button-text" aria-hidden="true">
                            Submit
                        </span>
                </span>
            </span>
        </div>
    </div>
</form>


<%-- ...........Script to print values of recent item added........... --%>
<script type="text/javascript">
    $(function () {
        $("#btnCookie").bind("click", function () {
            $.cookie("id", $("#id").val());
            $.cookie("name", $("#name").val());
            window.location.href = "homepageSeller.jsp";
        });
    });
</script>


<%--..........To populate select dropdown using json file.........--%>
<script>
    window.onload = populateSelect();

    //........... THE JSON ARRAY..............
    function populateSelect() {
        $.getJSON("/json/test.json", function(json) {
            //..............Json object containg category: json ..............
            console.log(json);

            var ele = document.getElementById('sel');
            for (var i = 0; i < json.length; i++) {
//...................... POPULATE SELECT ELEMENT WITH JSON....................
                ele.innerHTML = ele.innerHTML +
                    '<option value="' + json[i]['ID'] + '">' + json[i]['Category_Name'] + '</option>';
            }
        });
    }

    //............GET THE SELECTED VALUE FROM <select> ELEMENT AND SHOW IT............
    function show(ele) {
        // GET THE SELECTED VALUE FROM <select> ELEMENT AND SHOW IT.
        var msg = document.getElementById('msg');
        msg.innerHTML = 'Selected Category: <b>' + ele.options[ele.selectedIndex].text + '</b> </br>' +
            'ID: <b>' + ele.value + '</b>';
    }
</script>

<%--............To convert form values into json object........--%>
<script>
    (function() {
        function toJSONString( form ) {
            var obj = {};
            var elements = form.querySelectorAll( "input, select, textarea" );
            for( var i = 0; i < elements.length; ++i ) {
                var element = elements[i];
                var name = element.name;
                var value = element.value;

                if( name ) {
                    obj[ name ] = value;
                }
            }
            return JSON.stringify( obj );
        }

        document.addEventListener( "DOMContentLoaded", function() {
            var form = document.getElementById( "test" );

            form.addEventListener( "submit", function( e ) {
                e.preventDefault();
                var json = toJSONString( this );
//..............Json object containg inputed form values : json..............
                console.log(json);
            }, false);

        });
    })();
</script>

<script>
    $(".reset").click(function() {
        $(this).closest('form').find("input[type=text], textarea").val("");
    });
</script>

</body>
</html>