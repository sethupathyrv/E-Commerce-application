$(document).ready(function() {
    $("#infoSeller").submit(function (event) {
        event.preventDefault();

                var jsonData ={
                    'storeName':$('#storeName').val(),
                    'mobileNumber':$("#mobileNumber").val(),
                    'streetAddress':$("#streetAddress").val(),
                    'landmark':$("#landmark").val(),
                    'city':$("#city").val(),
                    'state':$("#state").val(),
                    'pincode':$("#pincode").val(),
                    'country':$("#country").val()
                };

        $("#submit").click(function () {
                $.ajax({
                    url:'/api/seller/updateinfo',
                    type:'PUT',
                    data: JSON.stringify(jsonData),
                    cache: false,
                    headers:{
                        'sellerAuthToken':$.cookie('sellerAuthToken')
                    },
                    contentType: false,
                    processData: false,
                    success: updateInfoResponse
                });
            });
        });

    function updateInfoResponse(response) {
        if(response.status ===201){
            alert("Info Updated");
            console.log(jsonData);
            window.location("/infoseller");
        }else if(response.status === 401){
            alert("Not authorized");
            window.location("/sellerlogin");
        }
        console.log(response);
    }

    $('#sellerLogout').click(function () {
        $.removeCookie("sellerAuthToken",{path:'/'});
        window.location.replace("/");
    });
});