$(document).ready(function() {
    $("#updateInfoSeller").submit(function (event) {
        event.preventDefault();

                var formData ={
                    'storeName':$('#storeName').val(),
                    'mobileNumber':$("#mobileNumber").val(),
                    'streetAddress':$("#streetAddress").val(),
                    'landmark':$("#landmark").val(),
                    'city':$("#city").val(),
                    'state':$("#state").val(),
                    'pincode':$("#pincode").val(),
                    'country':$("#country").val()
                };

                $.ajax({
                    url:'/api/seller/updateinfo',
                    dataType: "json",
                    type:'PUT',
                    data: JSON.stringify(formData),
                    cache: false,
                    headers:{
                        'sellerAuthToken':$.cookie('sellerAuthToken')
                    },
                    contentType: "text/plain",
                    processData: false,
                    success: updateInfoResponse
                });
        });

    function updateInfoResponse(response) {
        if(response.status ===200){
            alert("Info Updated");
            window.location.replace("/infoseller");
        }else if(response.status === 401){
            alert("Not authorized");
            window.location.replace("/sellerlogin");
        }
        //console.log(response);
        alert("Not Updated");
    }

    $('#sellerLogout').click(function () {
        $.removeCookie("sellerAuthToken",{path:'/'});
        window.location.replace("/");
    });
});