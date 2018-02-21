<%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 19/2/18
  Time: 9:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ooad.web.dao.ItemCategoryDao" %>
<%@ page import="com.ooad.web.model.ItemCategory" %>

<!DOCTYPE html>
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
                    <li class="active"><a href="/jsp/infoSeller.jsp">Seller Information </a></li>
                    <li><a href="/jsp/addItem.jsp">Add Item</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>


<%--............Form and button.............--%>
<form id="test" action="#" method="post">
    <div class="col-md-5">
        <div class="a-section">
            <div class="a-box">
                <div class="a-box-inner a-padding-extra-large">

                    <h1 class="a-spacing-small">Tell us about your business</h1>

                    <div class="a-row a-spacing-base">

                        <label for="store_name" class="a-form-label">Store Name</label>

                        <input type="text" maxlength="128" id="store_name" name="storename" tabindex="1"
                               class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off">


                        <label for="sel" class="a-form-label">Select Product Category</label>

                        <select id="sel" name="productCategory" onchange="show(this)">
                            <option value="">-- Select --</option>
                        </select>
                        <p id="msg"></p>


                        <div class="a-divider a-divider-break"></div>

                        <h3>Enter your address</h3>

                        <label for="pincode" class="a-form-label">Pincode</label>

                        <input type="text" maxlength="128" id="pincode" name="pincode" tabindex="1"
                               class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off">


                        <label for="add1" class="a-form-label">Address Line 1 (Required)</label>

                        <input type="text" maxlength="128" id="add1" name="add1" tabindex="1"
                               class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off">


                        <label for="add2" class="a-form-label">Address Line 2</label>

                        <input type="text" maxlength="128" id="add2" name="add2" tabindex="1"
                               class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off">


                        <label for="city" class="a-form-label">City</label>

                        <input type="text" maxlength="128" id="city" name="city" tabindex="1"
                               class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off">


                        <label for="state" class="a-form-label">State</label>

                        <input type="text" maxlength="128" id="state" name="state" tabindex="1"
                               class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off">


                        <label for="country" class="a-form-label">Country</label>

                        <input type="text" maxlength="128" id="country" name="country" tabindex="1"
                               class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off">

                    </div>
                </div>
            </div>
        </div>

        <div class="a-section">
            <span id="continue" class="a-button a-button-span12 a-button-primary">
                <span class="a-button-inner">
                    <input tabindex="5" class="a-button-input" type="submit" aria-labelledby="continue-announce">
                        <span id="continue-announce" class="a-button-text" aria-hidden="true">
                            Update
                        </span>
                </span>
            </span>
        </div>

    </div>
</form>

<%--..........To populate select dropdown using json file.........--%>
<script>
    window.onload = populateSelect();

    function populateSelect() {

        //......... THE JSON ARRAY..........
        $.getJSON("/json/test.json", function(json) {
            //..............Json object containg category: json ..............
            console.log(json);

            //........... POPULATE SELECT ELEMENT WITH JSON...........
            var ele = document.getElementById('sel');
            for (var i = 0; i < json.length; i++) {
                ele.innerHTML = ele.innerHTML +
                    '<option value="' + json[i]['ID'] + '">' + json[i]['Category_Name'] + '</option>';
            }
        });
    }

    //............GET THE SELECTED VALUE FROM <select> ELEMENT AND SHOW IT............
    function show(ele) {
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

</body>
</html>