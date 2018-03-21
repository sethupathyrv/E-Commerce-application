$(document).ready(function () {
    var x =0;
    $('#addfield').click(function (event) {
        console.log("clicked");
        x++;
        event.preventDefault();
        $('#wrapper').append(' <label for="subcategory" class="a-form-label">\n' +
            '                                        Subcategory\n' +
            '                                    </label>\n' +
            '                                    <input type="text" maxlength="64" id="subcategory'+x+'"name="subcategory" tabindex="4"\n' +
            '                                           class="a-input-text a-span12 auth-require-email-validaton"\n' +
            '                                           >')
    });

    $('#submitform').submit(function (event) {
        event.preventDefault();
        var category = $("#category").val();
        var subcategories = [];
        for(var i=1;i<=x;i++) {
            subcategories.push($('#subcategory'+i).val());
        }
        // console.log(JSON.stringify(subcategories));
        var formData = {
            'category':category,
            'subcategory': {subcategory:subcategories}
        };
        $.ajax({
            type:'POST',
            url:'/api/item/addcategory',
            data: JSON.stringify(formData),
            dataType:"json",
            contentType:"text/plain",
            cache: false,
            headers:{
                'authToken':$.cookie('authToken')
            },
            success: alert("added category/subcategories")
        });
    });

});






