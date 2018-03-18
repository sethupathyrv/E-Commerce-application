$(document).ready(function () {
   /* $('#infoSeller').click(function () {

        $.ajax({
            type:'POST',
            url:'/api/seller/displayinfo',
            //data: JSON.stringify(formData),
            dataType:"json",
            contentType:"text/plain",
            cache: false,
            headers:{
                'authToken':$.cookie('authToken')
            },
            success: addInfoResponse
        });

    });

    function addInfoResponse(response) {
        if(response.status ===201){
            alert("Info Created");
            window.location("/seller");
        }else if(response.status === 401){
            alert("Not authorized");
            window.location("/sellerlogin");
        }
        console.log(response);
    }*/

    $('#sellerLogout').click(function () {
        $.removeCookie("sellerAuthToken",{path:'/'});
        window.location.replace("/");
    });
});