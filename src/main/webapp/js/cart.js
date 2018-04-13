    $(document).ready(function () {
    var span = $('.Quantity');
    for (var i = 0;i<span.length ; i++){
        if (span[i].contentEditable) {
            span[i].onblur = function () {
                var text = this.innerHTML;
                text = text.replace(/&/g, "&amp").replace(/</g, "&lt;");
                console.log(text);
                console.log(this.id);
                var formData = {
                    'cartItemId': this.id,
                    'quantity': text
                };
                $.ajax({
                    type: 'PUT',
                    url: '/api/item/updatecart',
                    data: JSON.stringify(formData),
                    dataType: "json",
                    contentType: "text/plain",
                    cache: false,
                    headers: {
                        'authToken': $.cookie('authToken')
                    },
                    success: refreshpage
                });
            };
        }
    }
    function refreshpage(response) {
        window.location.reload(true);
    }
});
