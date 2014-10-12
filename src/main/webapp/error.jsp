<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 12.10.2014
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = (String) (request.getAttribute("message"));
%>
<html>
<head>
    <title>Error</title>
</head>
<body>
<header>Error:</header>
<p>
    <%= message %>
</p>

</body>
</html>
