function removeFromList(wishListItemId) {
    $.ajax({
        type:'DELETE',
        headers:{
            'authToken':$.cookie('authToken')
        },
        url:'api/item/list/'+wishListItemId,
        success:function (resp) {
            alert(JSON.stringify(resp));
            window.location.reload();
        }
    })
}

function moveToCart(wishListItemId) {
    formData = {
        "wishListItemId":wishListItemId
    };
    $.ajax({
        type:'POST',
        headers:{
            'authToken':$.cookie('authToken')
        },
        data: JSON.stringify(formData),
        dataType:"json",
        contentType:"text/plain",
        cache: false,
        url:'/api/item/movetocart',
        success:function (resp) {
            alert(JSON.stringify(resp));
            window.location.reload();
        }
    })
}
