<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 14.10.2014
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creating of tariff</title>
</head>
<body>
<header>Creating of new tariff.</header>

<hr>

<form id="tariff" method="post" action="tariff" enctype="application/x-www-form-urlencoded">
    <p>
        Title: <input type="text" name="title" size=25 value="">
    </p>
    <p>
        Price: <input type="text" name="price" size=25 value="">
    </p>
    <button type="submit">Create</button>
</form>

</body>
</html>
