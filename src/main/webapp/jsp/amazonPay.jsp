<%--
  Created by IntelliJ IDEA.
  User: chakri
  Date: 07-Apr-18
  Time: 5:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Amazon Pay</title>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
</head>
<body>
<script>
    $(document).ready(function(){

        $('#update').click(function () {
            var balance = $('#balance').val();
            var formData = {
                'balance':balance,
            };
            console.log(balance);
            $.ajax({
                url:'/api/user/updatebalance',
                type:'POST',
                data: JSON.stringify(formData),
                cache: false,
                headers:{
                    'authToken':$.cookie('authToken')
                },
                contentType: "text/plain",
                processData: false,
                success: updatebalanceresponse
            });
        });
        function updatebalanceresponse(data){
            window.location.reload(true);
            // console.log(data);
        }
    });

</script>

Balance: <span><%=request.getAttribute("balance")%></span>
<br>
Enter Amount: <input id="balance" type="number">
<button id="update">Add Balance</button>

</body>
</html>
