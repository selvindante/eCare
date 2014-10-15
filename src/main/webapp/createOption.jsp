<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 14.10.2014
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creating of option</title>
</head>
<body>
<header>Creating of new option:</header>

<hr>

<form id="option" method="post" action="option" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="id" value="${tariff.id}">
    <p>
        Title: <input type="text" name="title" size=25 value="">
    </p>
    <p>
        Price: <input type="text" name="price" size=25 value="">
    </p>
    <p>
        Cost of connection: <input type="text" name="costOfConnection" size=25 value="">
    </p>

    <hr>

    <p>
        incompatibility / dependency
    </p>
    <button type="submit">Create</button>
</form>
</body>
</html>
