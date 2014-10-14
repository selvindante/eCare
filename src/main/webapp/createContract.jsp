<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 14.10.2014
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Creating of contract</title>
</head>
<body>
<header>Creating of new contract for client ${client.email}.</header>
<form id="createContract" method="post" action="createContract" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="id" value="${client.id}">
    <p>
        Number (must be unique): <input type="text" name="number" size=25 value="">
    </p>
    <button type="submit">Create</button>
</form>

</body>
</html>
