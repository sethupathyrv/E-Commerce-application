/*
 * Created by Shravan on  14/2/18 11:59 PM.
 * Copyright (c) 2018. All rights reserved.
 */
function loginResponse(response) {
    if(response.status === 200){
        $.cookie("sellerAuthToken",response.token);
        window.location.replace("/seller");
    } else if(response.status === 400){
        if ("email" in response.errors){
            $("#emailError").html(response.errors.email);
        }
        if("psword" in response.errors){
            $("#passwordError").html(response.errors.psword);
        }

    }
}
$(document).ready(function () {
    $("#signIn").submit(function (event) {
        event.preventDefault();
        $("#emailError").html("");
        $("#passwordError").html("");
        var formData = {
            'email':$("#ap_email").val(),
            'psword':$("#ap_password").val()
        };
        $.ajax({
            type:'POST',
            url:'/api/seller/login',
            data: JSON.stringify(formData),
            dataType:"json",
            contentType:"text/plain",
            success: loginResponse
        });

    });
});

