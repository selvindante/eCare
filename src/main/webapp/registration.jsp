<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 14.10.2014
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<header>Registration of client:</header>
<form id="client" method="post" action="registration" enctype="application/x-www-form-urlencoded">
    <p>
        Name: <input type="text" name="name" size=25 value="">
    </p>
    <p>
        Lastname: <input type="text" name="lastname" size=25 value="">
    </p>
    <p>
        Birth date (yyyy-mm-dd): <input type="text" name="birthdate" size=25 value="">
    </p>
    <p>
        Passport: <input type="text" name="passport" size=25 value="">
    </p>
    <p>
        Address: <input type="text" name="address" size=25 value="">
    </p>
    <p>
        E-mail (login): <input type="text" name="email" size=25 value="">
    </p>
    <p>
        Password: <input type="text" name="password" size=25 value="">
    </p>
    <button type="submit">Save</button>
</form>

</body>
</html>
