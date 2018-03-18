$(document).ready(function () {
   /*$('#update').click(function () {


    });*/


    $('#sellerLogout').click(function () {
        $.removeCookie("sellerAuthToken",{path:'/'});
        window.location.replace("/");
    });
});