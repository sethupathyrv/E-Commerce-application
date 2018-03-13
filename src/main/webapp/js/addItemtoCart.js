$(document).ready(function () {
    $("#addtocart").click(function () {

        var formData = {
            'itemId':$("#itemId").val(),
            'quantity':$("#quantity").val()
        };
        $.ajax({
            type:'POST',
            url:'/api/item/addtocart',
            data: JSON.stringify(formData),
            dataType:"json",
            contentType:"text/plain",
            cache: false,
            headers:{
                'authToken':$.cookie('authToken')
            },
            success: addItemtoCartResponse
        });

    });
});

function addItemtoCartResponse(response) {
    if(response.status ===200){
        alert("Item added");
        console.log(response.cart.length);
        $("#cart-contents").text(response.cart.length);
    }else if(response.status === 401){
        window.location('/login');
    }
    console.log(response);
}


