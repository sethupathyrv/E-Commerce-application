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
function updateURLParameter(url, param, paramVal){
    var newAdditionalURL = "";
    var tempArray = url.split("?");
    var baseURL = tempArray[0];
    var additionalURL = tempArray[1];
    var temp = "";
    if (additionalURL) {
        tempArray = additionalURL.split("&");
        for (var i=0; i<tempArray.length; i++){
            if(tempArray[i].split('=')[0] != param){
                newAdditionalURL += temp + tempArray[i];
                temp = "&";
            }
        }
    }

    var rows_txt = temp + "" + param + "=" + paramVal;
    return baseURL + "?" + newAdditionalURL + rows_txt;
}
var resp = new Object();
function sortResults(prop, asc) {
    resp.items = resp.items.sort(function(a, b) {
        if (asc) {
            return (a[prop] > b[prop]) ? 1 : ((a[prop] < b[prop]) ? -1 : 0);
        } else {
            return (b[prop] > a[prop]) ? 1 : ((b[prop] < a[prop]) ? -1 : 0);
        }
    });
}

function sort(){
    if($('#sort').val()!="000") {
        if($('#sort').val()=="price:asc"){
            sortResults("price",true);
        }else if($('#sort').val()=="price:dec"){sortResults("price",false);}

    displayItemCategory(resp);
    }

}
$(document).ready(function() {
    var category = getUrlParameter('category');
    var subcategory = getUrlParameter('subcategory');
    var sortby = getUrlParameter('sortby');

    console.log(category);
    console.log(subcategory);
    console.log(sortby);

    $.ajax({
        type:'GET',
        url:'/api/item/'+category+'/'+subcategory+'/'+sortby,
        dataType:"json",
        contentType:"text/plain",
        cache: false,
        success: displayItemCategory
    });

});

var orig = new Object();
function displayItemCategory(response) {
    console.log(response);
    orig = response;
    resp = response;
    $('#items').empty();
    for (var i = 0; i < response.items.length; i++) {
        $('#items').append('<img id = "prodImage" width="150"  src="'+response.items[i].url+'" height="150" class = "img-responsive" alt="ItemName">');
        $('#items').append('<a href="#"><span id="productTitle" class="btn-link">'+response.items[i].name+'</span></a>'+' by '+'<a href="#" id="sellerName">'+response.items[i].seller.username+'</a>'
            +'<div id="price">&#2352;<span id="currentPrice">'+response.items[i].price+'</span></div>'+'<div id="description>"><span>'+response.items[i].description+'</span></div>');
        if (response.items[i].quantity >= 1){
            $('#items').append('<div id="availability"><span id="avail">'+'In stock'+'</span></div>');
        }
        else{
            $('#items').append('<div id="availability"><span id="avail">'+'Out of stock'+'</span></div>');
        }
        $('#items').append('<hr>')
    }
}

function checkAndSubmit() {
    if ((document.getElementById('minimum').value > 0) && (document.getElementById('maximum').value > 0)) {
        alert('Filter Applied');
        var formData = {
            'min': $("#minimum").val(),
            'max': $("#maximum").val(),
            'data': resp
        };

        $.ajax({
            type: 'POST',
            url: '/api/item/pricefilter',
            data: JSON.stringify(formData),
            cache: false,
            contentType: "text/plain",
            processData: false,
            success: displayItemCategoryFilter
        })
    }
}

function displayItemCategoryFilter(response) {
    console.log(response);
    resp=response;
    $('#items').empty();
    for (var i = 0; i < response.items.length; i++) {
        $('#items').append('<img id = "prodImage" width="150"  src="'+response.items[i].url+'" height="150" class = "img-responsive" alt="ItemName">');
        $('#items').append('<a href="#"><span id="productTitle" class="btn-link">'+response.items[i].name+'</span></a>'+' by '+'<a href="#" id="sellerName">'+response.items[i].seller.username+'</a>'
            +'<div id="price">&#2352;<span id="currentPrice">'+response.items[i].price+'</span></div>'+'<div id="description>"><span>'+response.items[i].description+'</span></div>');
        if (response.items[i].quantity >= 1){
            $('#items').append('<div id="availability"><span id="avail">'+'In stock'+'</span></div>');
        }
        else{
            $('#items').append('<div id="availability"><span id="avail">'+'Out of stock'+'</span></div>');
        }
        $('#items').append('<hr>')
    }
}

function clearFilter() {

    $('#items').empty();
    for (var i = 0; i < orig.items.length; i++) {
        $('#items').append('<img id = "prodImage" width="150"  src="'+orig.items[i].url+'" height="150" class = "img-responsive" alt="ItemName">');
        $('#items').append('<a href="#"><span id="productTitle" class="btn-link">'+orig.items[i].name+'</span></a>'+' by '+'<a href="#" id="sellerName">'+orig.items[i].seller.username+'</a>'
            +'<div id="price">&#2352;<span id="currentPrice">'+orig.items[i].price+'</span></div>'+'<div id="description>"><span>'+orig.items[i].description+'</span></div>');
        if (orig.items[i].quantity >= 1){
            $('#items').append('<div id="availability"><span id="avail">'+'In stock'+'</span></div>');
        }
        else{
            $('#items').append('<div id="availability"><span id="avail">'+'Out of stock'+'</span></div>');
        }
        $('#items').append('<hr>')
    }
}

