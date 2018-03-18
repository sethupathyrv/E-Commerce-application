$(document).ready(function() {
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
                    'subCategoryId':2  //TODO change subcategoryId from 2
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