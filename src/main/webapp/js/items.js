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
    displayRating();
}

function addInitialItemsResponse(data) {
    console.log(data);
    //For Deep Copy to avoid problems with references
    initData = JSON.parse(JSON.stringify(data));
    displayData = data;
    displayItems();
}

function applyPriceFilter() {
    var minPriceValue = $('#minPrice').val() == '' ? 0: $('#minPrice').val();
    var maxPriceValue = $('#maxPrice').val() == '' ? 0: $('#maxPrice').val();
    var colour = $('#color').val();
    // if (!(minPriceValue >0 && maxPriceValue > minPriceValue)) {
    //     alert("Invalid Filter");
    //     return
    // }
    var data = {
        'min': minPriceValue,
        'max': maxPriceValue,
        'colour':colour,
        'data': displayData
    };
    $.ajax({
        type: 'POST',
        url: '/api/item/filter',
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
    $('#minPrice').val('');
    $('#maxPrice').val('');
    $('#color').val('000')
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

function displayRating() {
    $(".rateYo").each(function(i,obj){
        console.log("1");
        console.log($(this).find("#sellerRating").text());
        $(this).rateYo({
            rating:$(this).find("#sellerRating").text(),
            readOnly:true,
            starWidth: "20px"
        })
    });
}