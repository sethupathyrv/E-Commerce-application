$(document).ready(function () {
    jQuery('a').click(function (event) {
        var id = $(this).attr("id");
        // console.log(id[id.length-1]);
        if(id.indexOf("deliver")>=0){
            console.log(id[id.length-1]);
            var id = id[id.length-1];
            var formData = {
            'addressId':id,
            };
            $.ajax({
                type:'POST',
                url:'/api/order/addaddress',
                data: JSON.stringify(formData),
                dataType:"json",
                contentType:"text/plain",
                cache: false,
                headers:{
                    'authToken':$.cookie('authToken')
                },
                success: deliverAddressResponse
            });
        }
    });

    jQuery('a').click(function (event) {
        var id = $(this).attr("id");
        // console.log(id[id.length-1]);
        if(id.indexOf("delete")>=0){
            console.log(id[id.length-1]);
            var id = id[id.length-1];
            $.ajax({
                type:'DELETE',
                url:'/api/user/address',
                cache: false,
                headers:{
                    'authToken':$.cookie('authToken')
                },
                success: deleteAddressResponse
            });
        }
    });

    $("#addAddressForm").submit(function (event) {
        event.preventDefault();
        var jsonData ={
            'fullName':$('#enterAddressFullName').val(),
            'mobileNumber':$("#enterAddressPhoneNumber").val(),
            'pincode':$("#enterAddressPostalCode").val(),
            'streetAddress':$("#enterAddressAddressLine2").val(),

            'landmark':$("#enterAddressLandmark").val(),
            'city':$("#enterAddressCity").val(),
            'state':$("#enterAddressStateOrRegion").val()
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

function deliverAddressResponse(response) {
    console.log(response);
    if(response.status ===200){
        alert("Address added");

        var orderid = response.order.id;
        window.location.replace("/order?id="+orderid);
        $("#cart-contents").text(response.cart.length);
    }else if(response.status === 401){
        window.location("/login");
    }
    console.log(response);
}

function addAddressResponse(response) {
    if(response.status ===201){
        alert("Address Created please choose a delivery address");
        location.reload();
    }else if(response.status === 401){
        alert("Not authorized");
        window.location("/login");
    }
    console.log(response);
}

function deleteAddressResponse (response) {
    console.log(response);
    if(response.status ===200){
        alert("Address deleted");
        location.reload();
    }else if(response.status === 401){
        window.location("/login");
    }
    console.log(response);
}
});

