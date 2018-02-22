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
                  'price':$("#price").val(),
                  'description':$("#itemDescription").val(),
                  'quantity':$("#quantity").val()
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
            location.reload();
        } else{
            alert("Item creation failed We shall fix this later");
            location.reload();
        }
    }
    $('#sellerLogout').click(function () {
        $.removeCookie("sellerAuthToken",{path:'/'});
        window.location.replace("/");
    });
});