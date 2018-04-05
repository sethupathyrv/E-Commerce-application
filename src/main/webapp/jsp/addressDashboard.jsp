<%@ page import="java.util.Collection" %>
<%@ page import="com.ooad.web.model.UserAddress" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: chakri
  Date: 15-Mar-18
  Time: 1:43 AM
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>





<html class="a-no-js noie notouch">

<head>



    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="format-detection" content="telephone=no" />
    <meta name="format-detection" content="address=no" />
    <title>Select a delivery address</title>


    <!-- Beginning of prime JS assets which is to log prime related metrics -->
    <link class="aui-page-asset" rel="stylesheet" href="../css/2.css" type="text/css">
    <link rel="stylesheet" href="../css/1.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>

    <script src="../js/deliverAddress.js"></script>


    <% ArrayList<UserAddress> userAddresses=
            (ArrayList<UserAddress>) request.getAttribute("userAddress");%>


</head>

<body>






<!-- no javascript alert message for AGS -->

<div class="checkout checkout-as checkout-as-desktop">




    <div id="a-navbar" class="a-row a-container">
        <div id="nav-inner" class="a-span12">
            <img src="/images/checkout-spc-address-banner.gif" alt="Select a Shipping Address - Amazon.in Checkout" data-testid="">





        </div>
    </div>

    <div class="a-container">
        <div class="clearfix">

            <h1 class="a-spacing-base" data-testid="">Select a delivery address</h1>





            <div class="a-spacing-base">
                <p data-testid=""> Is the address you'd like to use displayed below? If so, click the corresponding "Deliver to this address" button.&nbsp;Or you can <a href="#new-address">enter a new delivery address</a>.&nbsp; </p>
                <h3 data-testid=""> On the move? Pick up your order from our pickup store. </h3>
                <div data-testid="">
                    <div class="a-fixed-left-grid aui-showgrid">
                        <div class="a-fixed-left-grid-inner" id="ppp-logo-inner">
                            <div class="a-fixed-left-grid-col a-col-left aok-float-left" id="ppp-left-grid">
                                <div class="a-row" id="ppp-left-row"><a href="#new-store"><img src="/images/pickup.png" width="115" height="33" border="0" /></a></div>
                            </div>
                            <div class="a-fixed-left-grid-col a-col-right aok-float-left" id="ppp-right-grid">
                                <div class="a-row">Orders for select areas in <a href="#"> select cities </a> can now be delivered to Amazon pickup locations.&nbsp;<a href="#new-store">Search for a Pickup location near you</a></div>
                            </div>
                        </div>
                    </div>
                </div>




                <hr class="a-spacing-large" />
            </div>

            <form class="a-nostyle">

                <div class="a-spacing-base address-book">

                <%for(UserAddress u:userAddresses){%>
                    <div id="address-book-entry-0" class="address-book-entry a-spacing-double-large address-book-new-col">
                        <div class="js-same-height addr-display a-nostyle a-spacing-base" data-testid="">
                            <div class="displayAddressDiv">
                                <ul class="displayAddressUL">
                                    <li class="displayAddressLI displayAddressFullName"><b><%=u.getFullname()%></b></li>
                                    <li class="displayAddressLI displayAddressAddressLine1"><%=u.getMobilenumber()%> <%=u.getPincode()%></li>
                                    <li class="displayAddressLI displayAddressAddressLine2"><%=u.getPincode()%> <%=u.getCity()%></li>
                                    <li class="displayAddressLI displayAddressCityStateOrRegionPostalCode"><%=u.getStreetAddress()%></li>
                                    <li class="displayAddressLI displayAddressCountryName"><%=u.getLandmark()%></li>
                                </ul>
                            </div>


                        </div>

                        <div class="ship-to-this-address a-button a-button-primary a-button-span12 a-spacing-medium ">
                            <%--<input type="hidden" value="<%=u.getId()%>" id='addressId' class='h_v'>--%>
                            <span class="a-button-inner">
            <a id="deliver<%=u.getId()%>" data-action="page-spinner-show" class="a-declarative a-button-text " href="#" data-testid="" >
              Deliver to this address<span class="screenreader-context-span screenreader-address-span"></span>
                                </a>
                                </span>
                        </div>
                        <div class="a-row row-of-edit-and-delete-button">
                            <div class="a-span6 a-column">
                                <div class="a-button a-button-small a-button-span12">
                                        <span class="a-button-inner">
              <a id="edit" data-action="page-spinner-show" class="a-declarative a-button-text " href="/gp/buy/addressselect/handlers/continue.html/ref=ox_shipaddress_edit_addr_1?ie=UTF8&action=edit&addressID=onpptwoplip&enableDeliveryPreferences=1&fromAnywhere=0&numberOfDistinctItems=1&purchaseId=404-1367193-3314737&requestToken=&skipHeader=0" data-testid="">
                Edit<span class="screenreader-context-span"> address <span class="aok-offscreen screenreader-context-span screenreader-address-span"></span></span>
                                        </a>
                                        </span>
                                </div>
                            </div>
                            <div class="a-span6 a-column a-span-last">
                                <div data-action="checkout-delete-address" class="a-declarative a-button a-button-small a-button-span12  deletebutton">
                                        <span class="a-button-inner">
                <a id="delete<%=u.getId()%>" class="a-button-text" href="#" data-testid="">
                  Delete<span class="screenreader-context-span"> address <span class="aok-offscreen screenreader-context-span screenreader-address-span"></span></span>
                                        </a>
                                        </span>
                                </div>
                            </div>
                        </div>


                    </div>

                    <%}%>


                </div>




                <div class="cl"></div>

        </form>

        <div class="a-divider a-divider-section">
            <div class="a-divider-inner">
            </div>
        </div>


        <!-- bottom part with two form -->
        <div class="a-row">
            <div class="a-span6 a-column">
                <div class="a-spacing-double-large a-row on-imb-scroll-here" id="newShippingAddressFormFromIdentity">
                    <div class="a-span10 a-column">
                        <div class="enter-address-form " data-taxid-opt="0">
                            <h2 data-testid="">
                                <a name="new-address"></a> Add a new address
                            </h2>
                            Be sure to click &quot;Deliver to this address&quot; when you've finished.

                            <form action="#" id ="addAddressForm" method="post" class="a-nostyle a-declarative  ">

                                <noscript>
                                    <input type="hidden" name="javascriptEnabled" value="0" />
                                </noscript>
                                <!-- new address html-->
                                <div id="identity-add-new-address">

                                    <div id="enterAddressFullNameContainer" class="a-row a-spacing-base one-new-address-form-field  identity-protect-from-errant ">
                                        <div class="a-span12" data-testid="">





                                            <label for="enterAddressFullName" class="">
                                                <b>Full name:&nbsp;</b>
                                            </label>



                                            <input type="text" name="fullname" id="enterAddressFullName" class="enterAddressFormField" size="50" maxlength=50 />

                                        </div>
                                    </div>
                                    <div id="enterAddressPhoneNumberContainer" class="a-row a-spacing-base one-new-address-form-field   ">
                                        <div class="a-span12" data-testid="">

                                            <label for="enterAddressPhoneNumber" class="">
                                                <b>Mobile number:&nbsp;</b>
                                                <span class="learn-more">(<a data-action="a-popover" class="a-declarative" data-a-popover='{"type":"html", "header": "<b>Mobile number:&nbsp;</b>", "content": "Please enter a valid 10-digit mobile number without any prefixes (+91 or 0), spaces and dashes. This number will be used to assist with scheduling delivery.", "position": "triggerRight", "alone": false, "name":""}' data-testid="">Learn more</a>)</span>
                                            </label>



                                            <input type="number" min="1000000000" max="9999999999" name="mobileNumber" id="enterAddressPhoneNumber" class="enterAddressFormField" size="15" maxlength=20 />

                                        </div>
                                    </div>
                                    <div id="enterAddressPostalCodeContainer" class="a-row a-spacing-base one-new-address-form-field + identity-protect-from-errant ">
                                        <div class="a-span12" data-testid="">





                                            <label for="enterAddressPostalCode" class="">
                                                <b>Pincode:&nbsp;</b>
                                                <span class="learn-more">(<a data-action="a-popover" class="a-declarative" data-a-popover='{"type":"html", "header": "<b>Postal code:&nbsp;</b>", "content": "Please enter a valid 6 digits [0-9] zip code.", "position": "triggerRight", "alone": false, "name":""}' data-testid="">Learn more</a>)</span>
                                            </label>



                                            <input type="number" name="pincode" id="enterAddressPostalCode" min="100000" max="999999" class="enterAddressFormField" size="6" maxlength=6 />

                                        </div>
                                    </div>
                                    <div id="enterAddressAddressLine2Container" class="a-row a-spacing-base one-new-address-form-field  identity-protect-from-errant ">
                                        <div class="a-span12" data-testid="">

                                            <label for="enterAddressAddressLine2" class="">
                                                <b>Street Address:&nbsp;</b>
                                            </label>



                                            <input type="text" name="streetAddressLine2" id="enterAddressAddressLine2" class="enterAddressFormField" size="50" maxlength=60 />

                                        </div>
                                    </div>
                                    <div id="enterAddressLandmarkContainer" class="a-row a-spacing-base one-new-address-form-field   ">
                                        <div class="a-span12" data-testid="">

                                            <label for="enterAddressLandmark" class="">
                                                <b>Landmark e.g. near apollo hospital:&nbsp;</b>
                                            </label>





                                            <input type="text" name="landmark" id="enterAddressLandmark" class="enterAddressFormField" size="26" maxlength=60 />
                                            <span id="enterAddressLandmarkDescription" class="a-color-secondary a-size-small a-text-italic field-description"><br /><span class="tiny">E.g. Near AIIMS Flyover, Behind Regal Cinema, etc.</span> </span>
                                        </div>
                                    </div>
                                    <div id="enterAddressCityContainer" class="a-row a-spacing-base one-new-address-form-field  identity-protect-from-errant ">
                                        <div class="a-span12" data-testid="">

                                            <label for="enterAddressCity" class="">
                                                <b>Town/City:&nbsp;</b>
                                            </label>



                                            <input type="text" name="city" id="enterAddressCity" class="enterAddressFormField" size="25" maxlength=50 />

                                        </div>
                                    </div>
                                    <div id="enterAddressStateOrRegionContainer" class="a-row a-spacing-base one-new-address-form-field  identity-protect-from-errant ">
                                        <div class="a-span12" data-testid="">

                                            <label for="enterAddressStateOrRegion" class="">
                                                <b>State:&nbsp;</b>
                                            </label>
                                            <input type="text" name="state" id="enterAddressStateOrRegion" class="enterAddressFormField" size="15" maxlength=50 />

                                        </div>
                                    </div>
                               </div>
                                <div class="a-form-actions">
                                        <span class="a-button a-button-primary a-padding-none ">
        <span class="a-button-inner">
          <input type="submit" class="a-button-text submit-button-with-name" name="shipToThisAddress" value="Add a new Address" data-testid="">
        </span>
                                        </span>
                                </div>



                            </form>

                        </div>
                        <img src="/gp/checkoutonebyone/pagetype-checkout.html" style="display:none" />
                    </div>
                    <style>
                        body {
                            margin: 0 !important;
                        }
                    </style>
                </div>
            </div>
        </div>
</body>

</html>
