<%--
  ~ Created by Shravan on  15/2/18 12:14 AM. 
  ~ Copyright (c) 2018. All rights reserved. 
  --%>

<%--
  Created by IntelliJ IDEA.
  User: shravan
  Date: 15/2/18
  Time: 12:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>


<html class="a-no-js" data-19ax5a9jf="dingo">

<!-- Mirrored from www.amazon.in/ap/register?openid.pape.max_auth_age=0&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&pageId=inflex&ignoreAuthState=1&openid.return_to=https%3A%2F%2Fwww.amazon.in%2F%3Fref_%3Dnav_ya_signin&prevRID=BGCFEH3S0N4SV7D4H9AD&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&prepopulatedLoginId=&failedSignInCount=0&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0 by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 08 Feb 2018 10:27:53 GMT -->
<!-- Added by HTTrack -->
<meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
<!-- /Added by HTTrack -->

<head>
    <meta charset="utf-8">
    <title dir="ltr">Amazon Seller Registration</title>


    <link rel="stylesheet" href="../css/amazon1.css"/>
    <link rel="stylesheet" href="../css/amazon2.css"/>
</head>
<body class="ap-locale-en_IN a-aui_51744-c a-aui_57326-c a-aui_72554-c a-aui_83815-c a-aui_86171-c a-aui_96511-c a-aui_accessibility_49860-c a-aui_attr_validations_1_51371-c a-aui_bolt_62845-c a-aui_noopener_84118-c a-aui_ux_102912-c a-aui_ux_59374-c a-aui_ux_60000-c a-aui_ux_92006-c a-aui_ux_98513-c a-dex_92889-c">
<img height="1" width="1" style='display:none;visibility:hidden;'
     src='http://fls-eu.amazon.com/1/batch/1/OP/A21TJRUUN4KGV:262-1154870-0296601:P69ZD323SGSG31TA4FXQ$uedata=s:%2Fap%2Fuedata%3Fstaticb%26id%3DP69ZD323SGSG31TA4FXQ:0'
     alt=""/>


<div id="a-page">
    <div class="a-section a-padding-medium auth-workflow">
        <div class="a-section a-spacing-none auth-navbar">


            <div class="a-section a-spacing-medium a-text-center">


                <a class="a-link-nav-icon" tabindex="-1" href="/">


                    <i class="a-icon a-icon-logo" aria-label="Amazon"><span class="a-icon-alt">Amazon</span></i>


                    <i class="a-icon a-icon-domain-in a-icon-domain"></i>


                </a>


            </div>


        </div>


        <div class="a-section a-spacing-base auth-pagelet-container">


            <div id="auth-cookie-warning-message" class="a-box a-alert a-alert-warning">
                <div class="a-box-inner a-alert-container">
                    <h4 class="a-alert-heading">Please Enable Cookies to Continue</h4><i
                        class="a-icon a-icon-alert"></i>
                    <div class="a-alert-content">
                        <p>
                            <a class="a-link-normal"
                               href="https://www.amazon.in/gp/help/customer/display.html/ref=ap_cookie_error_help?">

                            </a>
                        </p>
                    </div>
                </div>
            </div>


        </div>

        <div class="a-section auth-pagelet-container">


            <!-- show a warning modal dialog when the third party account is connected with Amazon -->


            <div class="a-section auth-pagelet-container">


                <form id="ap_register_form" name="register" method="post" action='sellerregister'
                      class="ap_ango_default auth-validate-form-moa auth-real-time-validation">


                    <div class="a-box a-spacing-extra-large">
                        <div class="a-box-inner">
                            <h1 class="a-spacing-small moa_desktop_signup">
                                Create Seller Account
                            </h1>


                            <div class="a-row a-spacing-base">

                                <label for="ap_customer_name" class="a-form-label">
                                    Your name
                                </label>

                                <input type="text" maxlength="50" id="ap_customer_name" autocomplete="off"
                                       name="username" tabindex="1"
                                       class="a-input-text a-span12 auth-autofocus auth-required-field">
                            </div>
                            <div class="a-section a-spacing-base ap_email_fields">
                                <div class="a-row a-spacing-micro">
                                    <label for="ap_email" class="a-form-label">
                                        Email address
                                    </label>
                                    <input type="email" maxlength="64" id="ap_email" name="email" tabindex="4"
                                           class="a-input-text a-span12 auth-require-email-validaton"
                                           data-validation-id="email">
                                </div>
                            </div>


                            <div class="auth-require-fields-match-group">


                                <div class="a-row a-spacing-base">


                                    <label for="ap_password" class="a-form-label">
                                        Password
                                    </label>
                                    <input type="password" maxlength="1024" id="ap_password" autocomplete="off"
                                           placeholder="At least 6 characters" name="psword" tabindex="5"
                                           class="a-input-text a-span12 auth-required-field auth-require-fields-match  auth-require-password-validation">

                                </div>

                            </div>

                            <hr>
                            <%--<div class="a-section a-spacing-top-large a-text-left ap_mobile_number_fields">
                                <div class="a-row">
                                    We will send you a text to verify your phone.
                                </div>
                                <div class="a-row">
                                    Message and Data rates may apply.
                                </div>
                            </div>--%>

                            <h1 class="a-spacing-small">Tell us about your business</h1>

                            <div class="a-row a-spacing-base">

                                <label for="storeName" class="a-form-label">Store Name</label>

                                <input type="text" maxlength="128" id="storeName" name="storename" tabindex="1"
                                       class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off">


                                <%--<label for="sel" class="a-form-label">Select Product Category</label>

                                <select id="sel" name="productCategory" onchange="show(this)">
                                    <option value="">-- Select --</option>
                                </select>
                                <p id="msg"></p>--%>

                                <label for="mobileNumber" class="a-form-label">Mobile Number</label>

                                <input type="number" maxlength="128" id="mobileNumber" name="mobilenumber" tabindex="1"
                                       class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off">



                                <div class="a-divider a-divider-break"></div>

                                <h3>Enter your address</h3>

                                <label for="streetAddress" class="a-form-label">Street Address</label>

                                <input type="text" maxlength="128" id="streetAddress" name="streetaddress" tabindex="1"
                                       class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off">


                                <label for="landmark" class="a-form-label">Landmark</label>

                                <input type="text" maxlength="128" id="landmark" name="landmark" tabindex="1"
                                       class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off">


                                <label for="city" class="a-form-label">City</label>

                                <input type="text" maxlength="128" id="city" name="city" tabindex="1"
                                       class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off">


                                <label for="state" class="a-form-label">State</label>

                                <input type="text" maxlength="128" id="state" name="state" tabindex="1"
                                       class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off">

                                <label for="pincode" class="a-form-label">Pincode</label>

                                <input type="number" maxlength="128" id="pincode" name="pincode" tabindex="1"
                                       class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off">


                                <label for="country" class="a-form-label">Country</label>

                                <input type="text" maxlength="128" id="country" name="country" tabindex="1"
                                       class="a-input-text a-span12 auth-autofocus auth-required-field" autocomplete="off">

                            </div>


                            <div class="a-section a-spacing-extra-large ap_mobile_number_fields">
                                    <span id="auth-continue"
                                          class="a-button a-button-span12 a-button-primary auth-requires-verify-modal"><span
                                            class="a-button-inner"><input id="continue" tabindex="8"
                                                                          class="a-button-input" type="submit"
                                                                          aria-labelledby="auth-continue-announce"><span
                                            id="auth-continue-announce" class="a-button-text" aria-hidden="true">
          Continue
        </span></span>
                                    </span>
                            </div>


                            <div class="a-divider a-divider-section">
                                <div class="a-divider-inner"></div>
                            </div>


                            <div class="a-row">
                                Already have an account?
                                <a class="a-link-emphasis" href="/sellerlogin">
                                    Sign in
                                </a>
                            </div>


                        </div>
                    </div>
                </form>
            </div>


        </div>


        <div id="right-2">
        </div>

        <div class="a-section a-spacing-top-extra-large auth-footer">


            <div class="a-divider a-divider-section">
                <div class="a-divider-inner"></div>
            </div>
            <div class="a-section a-spacing-small a-text-center a-size-mini">
                <span class="auth-footer-seperator"></span>


                <a class="a-link-normal" target="_blank" rel="noopener noreferrer"
                   href="https://www.amazon.in/gp/help/customer/display.html/ref=ap_desktop_footer_cou?ie=UTF8&amp;nodeId=200545940">
                    Conditions of Use
                </a>
                <span class="auth-footer-seperator"></span>


                <a class="a-link-normal" target="_blank" rel="noopener noreferrer"
                   href="https://www.amazon.in/gp/help/customer/display.html/ref=ap_desktop_footer_privacy_notice?ie=UTF8&amp;nodeId=200534380">
                    Privacy Notice
                </a>
                <span class="auth-footer-seperator"></span>


                <a class="a-link-normal" target="_blank" rel="noopener noreferrer" href="https://www.amazon.in/help">
                    Help
                </a>
                <span class="auth-footer-seperator"></span>


            </div>

            <div class="a-section a-spacing-none a-text-center">

                    <span class="a-size-mini a-color-secondary">
    © 1996-2018, Amazon.com, Inc. or its affiliates
  </span>
            </div>

        </div>
    </div>

    <div id="auth-external-javascript" class="auth-external-javascript" data-external-javascripts="">
    </div>


    <!-- cache slot rendered -->

</div>
<div id='be' style="display:none;visibility:hidden;">
    <form name='ue_backdetect' action="https://www.amazon.in/ap/get"><input type="hidden" name='ue_back' value='1'/>
    </form>


</div>

<noscript>
    <img height="1" width="1" style='display:none;visibility:hidden;'
         src='http://fls-eu.amazon.com/1/batch/1/OP/A21TJRUUN4KGV:262-1154870-0296601:P69ZD323SGSG31TA4FXQ$uedata=s:%2Fap%2Fuedata%3Fnoscript%26id%3DP69ZD323SGSG31TA4FXQ:0'
         alt=""/>
</noscript>
</body>


</html>