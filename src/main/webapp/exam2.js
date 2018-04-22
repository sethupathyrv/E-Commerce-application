var theTemplate;
var theTemplate2;
$(document).ready(function () {
    var theTemplateScript = $("#example-template1").html();
    var theTemplateScript2 =$("#example-template2").html();
    theTemplate = Handlebars.compile(theTemplateScript);
    theTemplate2 = Handlebars.compile(theTemplateScript2);
    $("#gui2Submit").click(function () {
        $.ajax({
            type: 'GET',
            url: "/api/item/seller/" + $('#sellerId').val(),
            success: function (data) {
                var compiledHtml = theTemplate(data);
                $('#gui2Content').html(compiledHtml);
            }
        });

    });
    $('#gui3Submit').click(function () {
        $.ajax({
            type: 'GET',
            url: "/api/item/filter/quantity/"+$('#sellerId').val()+"/"
                    +$("#from").val()+"/"+$("#to").val(),
            success: function (data) {
                var comHtml = theTemplate2(data);
                $('#gui3Content').html(comHtml);
            }
        });
    })

});