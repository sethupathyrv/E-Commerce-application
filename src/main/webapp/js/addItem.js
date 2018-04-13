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
    $('#offer').on('change',function () {
        var offerSelect = $(this).val();
        if(offerSelect == 201) {
            $("#discountOfferDiv").show();
            $("#priceOfferDiv").hide();
            $("#bundleOfferDiv").hide();
        } else if(offerSelect == 202){
            $("#priceOfferDiv").show();
            $("#discountOfferDiv").hide();
            $("#bundleOfferDiv").hide();
        } else if(offerSelect == 203){
            $("#bundleOfferDiv").show();
            $("#priceOfferDiv").hide();
            $("#discountOfferDiv").hide();
        }

        if(offerSelect!=-1){
            $("#date").show();
        }else{
            $("#date").hide();
        }

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
                    'offerType':$("#offer").val(),
                    'discountPercentage':$('#discountOffer').val(),
                    'priceOffer':$("#priceOffer").val(),
                    'subCategoryId':$("#subCategory").val(),
                    'itemBarcode':$('#itemBarcode').val(),
                    'itemColour':$('#itemColour').val(),
                    'bundleOfferX':$('#bundleOfferX').val(),
                    'bundleOfferY':$('#bundleOfferY').val(),
                    'startDate':$('#startDate').val(),
                    'endDate':$('#endDate').val()

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
            } else {
                alert("Please select an image");
            }
        });
    });

    function addItemResponse(response) {
        if(response.status ===201){
            alert("Item Created");
            window.location.replace("/seller");
        }else if(response.status === 401){
            alert("Not authorized");
            window.location.replace("/sellerlogin");
        }
        console.log(response);
    }
    $('#sellerLogout').click(function () {
        $.removeCookie("sellerAuthToken",{path:'/'});
        window.location.replace("/");
    });
});