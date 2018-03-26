<%--
  Created by IntelliJ IDEA.
  User: minal
  Date: 17/3/18
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ooad.web.model.Seller" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sell on Amazon</title>

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
    <script src="../js/updateInfoSeller.js"></script>
    <script src="../js/jquery.cookie.js"></script>

    <% Seller seller = (Seller) request.getAttribute("seller"); %>

</head>

<body>

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
                        <li><a href="/seller">Dashboard</a></li>
                        <li class="active"><a href="/infoseller">Seller Information </a></li>
                        <li><a href="/seller/add">Add Item</a></li>
                        <li><a href="#" id="sellerLogout"> Logout</a></li>
                        <li><a href="/infoseller"> <%=seller.getUserName()%></a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>


    <%--............Form and button.............--%>
    <form id="updateInfoSeller" action="#" method="post" enctype="multipart/form-data">
        <div class="col-md-6">
            <div class="a-section">
                <div class="a-box">
                    <div class="a-box-inner a-padding-extra-large">

                        <h1 class="a-spacing-small">Tell us about your business</h1>

                        <div class="a-row a-spacing-base">

                            <label for="storeName" class="a-form-label">Store Name*</label>

                            <input type="text" maxlength="128" id="storeName" name="storename" tabindex="1"
                                   class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off"
                                    placeholder="<%=seller.getStoreName()%>" required="true">


                            <%--<label for="sel" class="a-form-label">Select Product Category</label>

                            <select id="sel" name="productCategory" onchange="show(this)">
                                <option value="">-- Select --</option>
                            </select>
                            <p id="msg"></p>--%>

                            <label for="mobileNumber" class="a-form-label">Mobile Number*</label>

                            <input type="number" maxlength="128" id="mobileNumber" name="mobilenumber" tabindex="1"
                                   class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off"
                                    placeholder="<%=seller.getMobileNumber()%>" required="true">



                            <div class="a-divider a-divider-break"></div>

                            <h3>Enter your address</h3>

                            <label for="streetAddress" class="a-form-label">Street Address*</label>

                            <input type="text" maxlength="128" id="streetAddress" name="streetaddress" tabindex="1"
                                   class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off"
                                    placeholder="<%=seller.getStreetAddress()%>" required="true">


                            <label for="landmark" class="a-form-label">Landmark*</label>

                            <input type="text" maxlength="128" id="landmark" name="landmark" tabindex="1"
                                   class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off"
                                    placeholder="<%=seller.getLandmark()%>" required="true">


                            <label for="city" class="a-form-label">City*</label>

                            <input type="text" maxlength="128" id="city" name="city" tabindex="1"
                                   class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off"
                                    placeholder="<%=seller.getCity()%>" required="true">


                            <label for="state" class="a-form-label">State*</label>

                            <input type="text" maxlength="128" id="state" name="state" tabindex="1"
                                   class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off"
                                    placeholder="<%=seller.getState()%>" required="true">

                            <label for="pincode" class="a-form-label">Pincode*</label>

                            <input type="number" maxlength="128" id="pincode" name="pincode" tabindex="1"
                                   class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off"
                                    placeholder="<%=seller.getPincode()%>" required="true">


                            <label for="country" class="a-form-label">Country*</label>

                            <input type="text" maxlength="128" id="country" name="country" tabindex="1"
                                   class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off"
                                    placeholder="<%=seller.getCountry()%>" required="true">

                        </div>
                    </div>
                </div>
            </div>

            <div class="a-section">
                <span id="continue1" class="a-button a-button-span12 a-button-primary">
                    <span class="a-button-inner">

                        <input tabindex="5" class="a-button-input" id="submit" type="submit" aria-labelledby="continue1-announce">
                            <span id="continue1-announce" class="a-button-text" aria-hidden="true">
                                Submit
                            </span>

                    </span>
                </span>
            </div>

            <div class="a-section">
                <span id="continue2" class="a-button a-button-span12 a-button-primary">
                    <span class="a-button-inner">
                        <a href="/infoseller">
                        <input tabindex="5" class="a-button-input" id="back" type="button" aria-labelledby="continue2-announce">
                            <span id="continue2-announce" class="a-button-text" aria-hidden="true">
                                Back
                            </span>
                        </a>
                    </span>
                </span>
            </div>


</div>





    </form>
</div>

<%--..........To populate select dropdown using json file.........
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

&lt;%&ndash;............To convert form values into json object........&ndash;%&gt;
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
</script>--%>

</body>
</html>
