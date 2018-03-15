$(document).ready(function () {
   /* $("#deliver").click(function (event) {
        event.preventDefault();
        console.log($("#addressId").val());
        var formData = {
            /'addressId':$("#addressId").val(),
        };*/
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
                success: addItemtoCartResponse
            });
        }

    });


        



});

function addItemtoCartResponse(response) {
    console.log(response);
    if(response.status ===200){
        alert("Item added");
        console.log(response.cart.length);
        $("#cart-contents").text(response.cart.length);
    }else if(response.status === 401){
        window.location("/login");
    }
    console.log(response);
}


