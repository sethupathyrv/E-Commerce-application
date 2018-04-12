<%--
  Created by IntelliJ IDEA.
  User: chakri
  Date: 07-Apr-18
  Time: 5:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
    <meta charset="utf-8">
    <title>Amazon Pay</title>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
    <link rel="stylesheet" href="../css/amazon1.css"/>
    <link rel="stylesheet" href="../css/amazon2.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/index.css">
</head>
<body>
<script>
    $(document).ready(function(){

        $('#update').click(function () {
            var balance = $('#balance').val();
            var formData = {
                'balance':balance,
            };
            console.log(balance);
            $.ajax({
                url:'/api/user/updatebalance',
                type:'POST',
                data: JSON.stringify(formData),
                cache: false,
                headers:{
                    'authToken':$.cookie('authToken')
                },
                contentType: "text/plain",
                processData: false,
                success: updatebalanceresponse
            });
        });
        function updatebalanceresponse(data){
            window.location.reload(true);
            // console.log(data);
        }
    });

</script>

<img height="1" width="1" style='display:none;visibility:hidden;'
     src='http://fls-eu.amazon.com/1/batch/1/OP/A21TJRUUN4KGV:262-1154870-0296601:P69ZD323SGSG31TA4FXQ$uedata=s:%2Fap%2Fuedata%3Fstaticb%26id%3DP69ZD323SGSG31TA4FXQ:0'
     alt=""/>


<div id="a-page">
    <div class="a-section a-padding-medium auth-workflow">
        <div class="a-section a-spacing-none auth-navbar">


            <div class="a-section a-spacing-medium a-text-center">


                <%--<a class="a-link-nav-icon" tabindex="-1" href="https://www.amazon.in/ref=ap_frn_logo">--%>
                <a class="a-link-nav-icon" tabindex="-1" href="/">


                    <i class="a-icon a-icon-logo" aria-label="Amazon"><span class="a-icon-alt">Amazon</span></i>


                    <i class="a-icon a-icon-domain-in a-icon-domain"></i>


                </a>


            </div>


        </div>


        <div class="a-section a-spacing-base auth-pagelet-container">

            <div class="a-section auth-pagelet-container">


                <!-- show a warning modal dialog when the third party account is connected with Amazon -->


                <div class="a-section auth-pagelet-container">

                        <div class="a-box a-spacing-extra-large">
                            <div class="a-box-inner">
                                <h2 class="a-spacing-small moa_desktop_signup">
                                    Add money to your Wallet
                                </h2>

                                <div class="a-row a-spacing-base">
                                    <b>Balance: </b><span><%=request.getAttribute("balance")%></span>
                                </div>
                                <br>


                                <div class="a-row a-spacing-base">

                                    <label for="balance" class="a-form-label">
                                        Enter Amount
                                    </label>

                                    <input  type="number" maxlength="50" id="balance" autocomplete="off"
                                            name="balance" tabindex="1"
                                            class="a-input-text a-span12 auth-autofocus auth-required-field">
                                </div>


                                <div class="a-section a-spacing-extra-large ap_mobile_number_fields">
                                    <%--<button id="update">Add Balance</button>--%>
                                    <span id="auth-continue"
                                          class="a-button a-button-span12 a-button-primary auth-requires-verify-modal"><span
                                            class="a-button-inner"><input id="update" tabindex="8"
                                                                          class="a-button-input" type="submit"
                                                                          aria-labelledby="auth-continue-announce"><span
                                            id="auth-continue-announce" class="a-button-text" aria-hidden="true">
          Add Balance
        </span></span>
                                    </span>
                                </div>



                            </div>
                        </div>

                </div>


            </div>

        </div>

    </div>
    </div>
</body>
</html>
