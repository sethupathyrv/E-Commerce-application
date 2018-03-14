$(document).ready(function() {
    $("#addAddressForm").submit(function (event) {
        event.preventDefault();
        var jsonData ={
            'fullName':$('#fullName').val(),
            'mobileNumber':$("#mobileNumber").val(),
            'pincode':$("#pincode").val(),
            'streetAddress':$("#streetAddress").val(),

            'landmark':$("#landmark").val(),
            'city':$("#city").val(),
            'state':$("#state").val()


        };
        // formData.append('json',JSON.stringify(jsonData));
        $.ajax({
            url:'/api/user/address',
            type:'POST',
            data: JSON.stringify(jsonData),
            cache: false,
            headers:{
                'authToken':$.cookie('authToken')
            },
            contentType: "text/plain",
            processData: false,
            success: addAddressResponse
        });
    });

    function addAddressResponse(response) {
        if(response.status ===201){
            alert("Address Created");
        }else if(response.status === 401){
            alert("Not authorized");
            window.location("/login");
        }
        console.log(response);
    }
});