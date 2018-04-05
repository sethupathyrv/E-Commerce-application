/*
 * Created by Sandeep Tadepalli on 13/02/18 15:49
 * Copyright (c) 2018. All rights reserved.
 */
function loginResponse(response) {
    if(response.status === 200){
        $.cookie("authToken",response.token);
        $.cookie("email",response.user.email);
        window.location.replace("/");
    } else if(response.status === 400){
        if ("email" in response.errors){
            $("#emailError").html(response.errors.email);
        }
        if("psword" in response.errors){
            $("#passwordError").html(response.errors.psword);
        }
        if("verification" in response.errors){
            $('#verificationError').html(response.errors.verification);
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
            url:'/api/login',
            data: JSON.stringify(formData),
            dataType:"json",
            contentType:"text/plain",
            success: loginResponse
        });

    });
});
