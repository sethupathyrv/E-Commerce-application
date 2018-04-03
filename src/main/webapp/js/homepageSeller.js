$(document).ready(function () {

});

function dispatchItem(orderItemId) {
    console.log(orderItemId);
    $.ajax({
        type:'GET',
        url:'/api/item/dispatchitem/'+orderItemId,
        headers:{
            'sellerAuthToken':$.cookie('sellerAuthToken')
        },
        dataType:"json",
        success: dispatchResponse
    });
}

function  dispatchResponse(data) {
    if(data.status == 200){
        alert("Item Dispatched");
        document.location.reload();
    }else {
        aleat(data.errors);
    }
}