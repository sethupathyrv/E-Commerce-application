<%--
  Created by IntelliJ IDEA.
  User: sandeep
  Date: 21/4/18
  Time: 10:48 AM
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
<script src="exam2.js"></script>
<body>
<div class="container">
    <div class="row">
        <p class="col-sm-6">Sandeep Tadepalli</p>
        <p class="col-sm-6">IMT2014057</p>
    </div>
</div>

<div id="gui2">
    <div id="gui2Input">
        <label for="sellerId">Enter Seller Id*</label>
        <input type="text" id="sellerId">
        <button id="gui2Submit">Submit</button>
    </div>

    <script id="example-template1" type="text/x-handlebars-template">
        <table class="table" align="center" role="presentation">
            <tr>
                <td class="col-sm-3">Srl. No</td>
                <td class="col-sm-3">Item Id </td>
                <td class="col-sm-3">Item Name </td>
                <td class="col-sm-3">Quantity Sold </td>

            </tr>
            {{#each items}}
            <tr>
                <td class="col-sm-3">{{srlNo}}</td>
                <td class="col-sm-3">{{item.itemBarcode}}</td>
                <td class="col-sm-3">{{item.name}}</td>
                <td class="col-sm-3">{{item.quantitySold}}</td>
            </tr>
            {{/each}}
        </table>
    </script>

    <div id="gui2Content">

    </div>
</div>

<div id="gui3">
    <div id="gui3Input">
        <label for="from">range QtyItm From*</label>
        <input type="number" id="from">
        <label for="to">to*</label>
        <input type="number" id="to">
        <button id="gui3Submit">Submit</button>
    </div>

    <script id="example-template2" type="text/x-handlebars-template">
        <div class="row">
            {{#each items}}
            <div class="item col-sm-6">
                <img src="{{url}}" alt="" style="height:150px;width:150px">
                id: {{itemBarcode}}<br>
                QuantitySold: {{quantitySold}}<br>
                price: {{price}}
            </div>
            {{/each}}
        </div>
    </script>
    <div id="gui3Content">
    </div>
</div>
<a href="/" class="btn btn-primary">Home</a>

</body>
</html>
