<%--
  Created by IntelliJ IDEA.
  User: shug
  Date: 2024/8/27
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="site.shug.spring.mvc.model.User" %>
<% User user = (User) request.getAttribute("user"); %>
<html>
<head>
    <title>User</title>
</head>
<body>
<h1> Hello <%= user.name %></h1>
</body>
</html>
