/*
 * Created by Sandeep Tadepalli on 13/02/18 15:49
 * Copyright (c) 2018. All rights reserved.
 */
function loginResponse(data) {
    if(data.status === 200){
        $.cookie("authToken",data.token);
        $.cookie("email",data.user.email);
        window.location.replace("/");
    } else if(data.status === 400){
        if ("email" in data.errors){
            $("#emailError").html(data.errors.email);
        }
        if("psword" in data.errors){
            $("#passwordError").html(data.errors.psword);
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
