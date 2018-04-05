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
            window.location.reload();
            console.log(data)
        }
    });
}