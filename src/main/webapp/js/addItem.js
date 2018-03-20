$(document).ready(function() {
    $.ajax({
        url:'/api/item/getcategories',
        type:'GET',
        cache: false,
        contentType: false,
        processData: false,
        success: getCategoriesResponse
    });

    function getCategoriesResponse(response) {
        $.each(response.categories, function (index, value) {
            $("#category").append('<option value="'+value.id+'">'+value.name+'</option>');
        });

        $('#category').on('change', function(){
            console.log($(this).val());
            $('#subCategory').html('<option value="000">-Select subCategory-</option>');
            for(var i = 0; i < response.categories.length; i++)
            {
                if(response.categories[i].id == $(this).val())
                {
                    // console.log("came before");
                    $.each(response.categories[i].subcategories, function (index, value) {
                        $("#subCategory").append('<option value="'+value.id+'">'+value.name+'</option>');
                    });
                } else if($(this).val()=="000") {
                    // console.log("came");
                    $('#subCategory').html('<option value="000">-Select subCategory-</option>');
                }

            }
        });

    }
    $('#sellerLogout').click(function () {
            $.removeCookie("sellerAuthToken", {path: '/'});
            window.location.replace("/");
        });


    $("#addItemForm").submit(function (event) {
        event.preventDefault();
        $("#itemImage").each(function (index,value) {
            var file = value.files[0];
            if(file){
                var formData = new FormData();
                formData.append('file',file);
                var jsonData ={
                  'name':$('#name').val(),
                  'price':$("#itemPrice").val(),
                  'description':$("#itemDescription").val(),
                  'quantity':$("#quantity").val(),
                    'brand':$("#brand").val(),
                    'height':$("#itemHeight").val(),
                    'width':$("#itemWidth").val(),
                    'itemDetails':$("#itemDetails").val(),
                    'subCategoryId':$("#subCategory").val()
                };
                formData.append('json',JSON.stringify(jsonData));
                $.ajax({
                    url:'/api/item/add',
                    type:'POST',
                    data: formData,
                    cache: false,
                    headers:{
                        'authToken':$.cookie('sellerAuthToken')
                    },
                    contentType: false,
                    processData: false,
                    success: addItemResponse
                });
            }
        });
    });

    function addItemResponse(response) {
        if(response.status ===201){
            alert("Item Created");
            window.location("/seller");
        }else if(response.status === 401){
            alert("Not authorized");
            window.location("/sellerlogin");
        }
        console.log(response);
    }
    $('#sellerLogout').click(function () {
        $.removeCookie("sellerAuthToken",{path:'/'});
        window.location.replace("/");
    });
});