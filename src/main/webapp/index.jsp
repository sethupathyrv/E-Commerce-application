<%@ page import="com.ooad.web.dao.ItemCategoryDao" %>
<%@ page import="com.ooad.web.model.ItemCategory" %><%--
  ~ Created by Sandeep Tadepalli on 04/02/18 03:44
  ~ Copyright (c) 2018. All rights reserved.
  --%>

<html>
<body>
<h2>Hello World!</h2>
<% final ItemCategoryDao itemCategoryDao = new ItemCategoryDao();
    for (ItemCategory itemCategory : itemCategoryDao.getAllCategories()) { %>
<%= itemCategory %> <br>
<% }%>
</body>
</html>
