$(document).ready(function () {

});

function itemDelivered(orderItemId){
    $.ajax({
        url:"/api/item/delivered/"+orderItemId,
        type:'GET',
        headers:{
            'authToken':$.cookie('authToken')
        },
        success:function (data) {
            // window.location.reload(true);
            console.log(data)
        }
    });
}

function sendRating(orderItemId){
    test = $('#sellerRating'+orderItemId).val();
    console.log(test);
    if(test > 0 && test <=5){
        var formData = {
            "rating":test
        };
        console.log("sending rest Request");
        $.ajax({
            url:'/api/item/rating/'+orderItemId,
            type:'POST',
            data: JSON.stringify(formData),
            dataType:"json",
            contentType:"text/plain",
            headers:{
                'authToken':$.cookie('authToken')
            },
            success:function(data){
                console.log(data);
            }

        })
    }
}