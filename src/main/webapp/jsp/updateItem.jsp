<%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 17/3/18
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<script src="../js/jquery.cookie.js"></script>
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

            <div class="logo col-lg-2 col-md-2 right">
                <a href="/" class="nav-logo-link">
                    <img src="http://localhost:8081/images/logo.png" alt="Home" style="background-color: black">
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
                        <li class="active"><a href="/seller">Dashboard</a></li>
                        <li><a href="/infoseller">Seller Information </a></li>
                        <li><a href="/seller/add">Add Item</a></li>
                        <li><a href="#" id="sellerLogout"> Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>

    <%--............Form and button.............--%>
    <form id="addItemForm" action="#" method="post" enctype="multipart/form-data">
        <div class="col-md-6">
            <div class="a-section">
                <div class="a-box">
                    <div class="a-box-inner a-padding-extra-large">
                        <h1 class="a-spacing-small">Update Item details</h1>

                        <div class="a-row a-spacing-base">


                            <label for="name" class="a-form-label">Name</label>
                            <input class="form-control" type="text" name="name" id="name" autocomplete="off"/>

                            <label for="itemPrice" class="a-form-label">Price</label>
                            <input class="form-control" type="text" name="itemPrice" id="itemPrice" autocomplete="off"/>

                            <label for="quantity" class="a-form-label">Quantity</label>
                            <input class="form-control" type="text" name="quantity" id="quantity" autocomplete="off" />

                            <%--<label for="sel" class="a-form-label">Sub-Sub Category</label>--%>

                            <%--<select id="sel" name="subSubCategory" onchange="show(this)">--%>
                            <%--<option value="">-- Select --</option>--%>
                            <%--</select>--%>
                            <%--<p id="msg"></p>--%>

                            <label id="description" class="a-form-label">Item Description</label>

                            <textarea class="form-control" id="itemDescription" name="description" rows="5" cols="30"></textarea>

                            <label for="brand" class="a-form-label">Brand</label>
                            <input class="form-control" type="text" name="brand" id="brand" autocomplete="off"/>

                            <label for="itemHeight" class="a-form-label">Height</label>
                            <input class="form-control" type="text" name="itemHeight" id="itemHeight" autocomplete="off"/>

                            <label for="itemWidth" class="a-form-label">Width</label>
                            <input class="form-control" type="text" name="itemWidth" id="itemWidth" autocomplete="off"/>

                            <label id="details" class="a-form-label">Item Details</label>

                            <div class="dropdown" id="offerType">
                                <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Dropdown Example
                                    <span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Discount</a></li>
                                    <li><a href="#">Buy y get x</a></li>
                                    <li><a href="#">1+1</a></li>
                                </ul>
                            </div>

                            <textarea class="form-control" id="itemDetails" name="details" rows="5" cols="30"></textarea>




                            <label for="itemImage" class="a-form-label">Image</label>

                            <input class="form-control" type="file" name="itemImage" id="itemImage" />

                        </div>
                    </div>
                </div>
            </div>

            <div class="a-section">
                <span id="continue" class="a-button a-button-span12 a-button-primary">
                    <span class="a-button-inner">
                        <input tabindex="5" id="btnCookie" class="a-button-input" type="submit" aria-labelledby="continue-announce">
                            <span id="continue-announce" class="a-button-text" aria-hidden="true" value="add-row">
                                Submit
                            </span>
                    </span>
                </span>
            </div>
        </div>
    </form>
</div>
<%--..........To populate select dropdown using json file.........--%>
<script>
    /*    window.onload = populateSelect();

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
        }*/
</script>


</body>
</html>
