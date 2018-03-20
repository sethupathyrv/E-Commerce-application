var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

$(document).ready(function() {
    var category = getUrlParameter('category');
    var subcategory = getUrlParameter('subcategory');
    console.log(category);
    console.log(subcategory);

    $.ajax({
        type:'GET',
        url:'/api/item/'+category+'/'+subcategory,
        dataType:"json",
        contentType:"text/plain",
        cache: false,
        success: displayItemCategory
    });

});

function displayItemCategory(response) {
    console.log(response);
    for (var i = 0; i < response.items.length; i++) {
        // $('#items').append('<div class="col-lg-4"><div id="itemImage">');
        $('#items').append('<img id = "prodImage" width="150"  src="'+response.items[i].url+'" height="150" class = "img-responsive" alt="ItemName">');
        // $('#items').append('</div></div>');
        // $('#items').append('<div class="col-lg-8"><div id="itemDetails">');
        $('#items').append('<a href="#"><span id="productTitle" class="btn-link">'+response.items[i].name+'</span></a>'+' by '+'<a href="#" id="sellerName">'+response.items[i].seller.username+'</a>'
            +'<div id="price">&#2352;<span id="currentPrice">'+response.items[i].price+'</span></div>'+'<div id="description>"><span>'+response.items[i].description+'</span></div>');
        if (response.items[i].quantity >= 1){
            $('#items').append('<div id="availability"><span id="avail">'+'In stock'+'</span></div>');
        }
        else{
            $('#items').append('<div id="availability"><span id="avail">'+'Out of stock'+'</span></div>');
        }
        // $('#items').append('</div></div>');
        $('#items').append('<hr>')
    }
    // console.log(response.items[0].name);
    // if(response.status ===200){
    //     $('#output').append(response);
    // }else if(response.status === 401){
    // }else if(response.status === 400){
    // }
}
