var theTemplate;
var initData;
var displayData;
$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();
    var theTemplateScript = $("#example-template").html();
    theTemplate = Handlebars.compile(theTemplateScript);
    var urlQuery = url('?');
    if (urlQuery == null) {
        $.ajax({
            type: 'GET',
            url: "/api/item/lastfive",
            success: addInitialItemsResponse
        });
    } else {
        var category = urlQuery.category;
        var subCategory = urlQuery.subcategory;
        $.ajax({
            type: 'GET',
            url: "/api/item/" + category + "/" + subCategory,
            success: addInitialItemsResponse
        })
    }

});

function displayItems() {
    var compiledHtml = theTemplate(displayData);
    $('#items').html(compiledHtml);
}

function addInitialItemsResponse(data) {
    //For Deep Copy to avoid problems with references
    initData = JSON.parse(JSON.stringify(data));
    displayData = data;
    displayItems();
}

function applyPriceFilter() {
    var minPriceValue = $('#minPrice').val();
    var maxPriceValue = $('#maxPrice').val();
    if (!(minPriceValue >0 && maxPriceValue > minPriceValue)) {
        alert("Invalid Filter");
        return
    }
    var data = {
        'min': minPriceValue,
        'max': maxPriceValue,
        'data': displayData
    };
    $.ajax({
        type: 'POST',
        url: '/api/item/pricefilter',
        data: JSON.stringify(data),
        cache: false,
        contentType: "text/plain",
        processData: false,
        success: function (data) {
            displayData = data;
            displayItems();
        }
    })
}

function clearFilter() {
    displayData = JSON.parse(JSON.stringify(initData));
    displayItems();
    $('#sort').val("000");
}

function sortResults(prop, asc) {
    displayData.items = displayData.items.sort(function(a, b) {
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
        }else if($('#sort').val()=="price:dec"){
            sortResults("price",false);
        }
        displayItems();
    }
}

