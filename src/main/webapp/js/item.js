$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();

    $("#rateYo").rateYo({
        rating: $('#sellerRatingHidden').val(),
        readOnly:true,
        starWidth: "20px"
    });
    $('#directBuy').click(function () {
        var formData = {
            'itemId':$("#itemId").val(),
            'quantity':$("#quantity").val()
        };
        $.ajax({
            type:'POST',
            url:'/api/item/directbuy',
            data: JSON.stringify(formData),
            dataType:"json",
            contentType:"text/plain",
            cache: false,
            headers:{
                'authToken':$.cookie('authToken')
            },
            success:directBuy
        });


    });
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

    $('#moveToList').click(function () {
        var formData = {
            'itemId':$('#itemId').val()
        };
        $.ajax({
            type:'POST',
            url:'/api/item/list',
            data:JSON.stringify(formData),
            dataType: "json",
            contentType:'text/plain',
            cache:false,
            headers:{
                'authToken':$.cookie('authToken')
            },
            success:function (resp) {
                if(resp.status == 401){
                    window.location.replace('/login');
                }
            }
        })
    });


});

function addItemtoCartResponse(response) {
    if(response.status ===200){
        $("#cart-contents").text(response.cart.cartItems.length);
    }else if(response.status === 401){
        window.location.replace("/login");
    }else if(response.status === 400){
        alert(JSON.stringify(response.errors));
    }
    console.log(response);
}

function directBuy(response) {
    if(response.status == 200){
        window.location.replace("/checkout");
    }else{
        alert(response.error);
    }
}

