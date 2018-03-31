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
    initData = data;
    displayData = data;
    displayItems();
}

function applyPriceFilter() {
    var minPriceValue = $('#minPrice').val();
    var maxPriceValue = $('#maxPrice').val();
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
    displayData = initData;
    displayItems();
}


