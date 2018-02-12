<%--
  Created by IntelliJ IDEA.
  User: chakri
  Date: 06-Feb-18
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome <%=session.getAttribute("username")%>
    </title>
</head>
<body>
<%
    //If User is not logged in redirect to home page
    if (session != null) {
        if (session.getAttribute("username") == null)
            response.sendRedirect("index.jsp");
    }
%>
<!-- Page Header -->
<div class="top-header"><span class="home-logo"><a href="/JSPLogin"></a></span></div>

<div class="container">
    <!-- Use session variables to print username and email -->
    <div class="content welcome-text">
        <p><%=session.getAttribute("username")%> Your login was successful!:)</p>
        <p> You are registered under this email <%=session.getAttribute("email")%>
        </p>
        <br>

    </div>

</div>
<!-- Page Footer -->
<div class="footer">
    <div class="footer blk">
        <!-- Logout Form -->
        <form action="Logout" method="post">
            <input class="back-home-btn" type="submit" value="Logout">
        </form>
        <button class="back-home-btn" onclick="history.back()">Back</button>
    </div>
</div>
</body>
</html>