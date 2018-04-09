$(document).ready(function () {

    $('#placeorder').click(function () {
        console.log($("#orderId").val());
        var wallet;
        if($('#wallet').is(":checked"))
            wallet=true
        else
            wallet=false;
        var formData = {
            'orderId':$("#orderId").val(),
            'wallet':wallet
        };
        $.ajax({
            type:'POST',
            url:'/api/order/transaction',
            data: JSON.stringify(formData),
            dataType:"json",
            contentType:"text/plain",
            cache: false,
            headers:{
                'authToken':$.cookie('authToken')
            },
            success: TransactionResponse
        });
    });

});

function TransactionResponse(response) {
    console.log(response);
    $('#toggle').trigger("click");
    if(response.transaction.status==1) {
        $('#status').text("transaction is successful");
        $('#close').click(function () {
            window.location.replace("/");
        });
    }
    else
        $('#status').text("Transaction declined!!Money insufficient");

}




