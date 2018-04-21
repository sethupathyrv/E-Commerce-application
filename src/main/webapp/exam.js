var theTemplate;
$(document).ready(function () {
    var theTemplateScript = $("#example-template").html();
    theTemplate = Handlebars.compile(theTemplateScript);
    $("#submitGui2").click(function () {
        $.ajax({
            type: 'GET',
            url: "/api/item/compare/" + $("#item1").val() + "/" + $("#item2").val(),
            success: function (data) {
                var compiledHtml = theTemplate(data);
                $('#compareItems').html(compiledHtml);
                $("#gui2_2_button").show();
            }
        });
    });

    $("#q2").click(function () {
        $("#gui1").hide();
        $("#gui2").show();
        $("#compareItems").html("");
        $("#gui2_2_button").hide();
    });
    $("#prevGui2").click(function () {
        $("#gui2").hide();
        $("#gui1").show();
    });
    $("#submitGui2").click(function () {
        $("#gui1").hide();
        $("#gui2_1").hide();
        $("#gui2_2_button").show();
    });
    $("#gui2_2_button").click(function () {
       $("#gui1").hide();
       $("#gui2").show();
       $("#gui2_1").show();
       $("#compareItems").html("");
       $("#gui2_2_button").hide();
    });
    $("#q3").click(function () {
        $("#gui1").hide();
        $("#gui2").hide();
        $("#gui3").show();
    })
    $("#gui3_prev").click(function () {
        $("#gui3").hide();
        $("#gui1").show();
    })

});
