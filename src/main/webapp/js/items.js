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
    if(response.status ===200){
        $('#output').append(response);
    }else if(response.status === 401){
    }else if(response.status === 400){
    }
}
