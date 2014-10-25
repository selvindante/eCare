<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <title>Registration</title>
</head>
<body>

<div class="outer-wrapper clearfix">

    <h3>
        LOGO
        Registration:
    </h3>

    <c:if test="${errormessage != null}">
        <div class="inner-wrapper-error">
            <p>
                Error: ${errormessage}
            </p>
        </div>
    </c:if>

    <div class="inner-wrapper">
        <p>
            Registration form:
        </p>
        <br>
        <form method="post" action="registration" enctype="application/x-www-form-urlencoded">
            <div id="center">
                <input type="hidden" name="action" value="registration">
                <p>
                    Name:
                    <br>
                    <input type="text" placeholder="name" class="simple-input" name="name" size=25 value="${name}"> *
                </p>
                <br>
                <p>
                    Lastname:
                    <br>
                    <input type="text" placeholder="lastname" class="simple-input" name="lastname" size=25 value="${lastname}">
                </p>
                <br>
                <p>
                    Birth date:
                    <br>
                    <input type="text" placeholder="yyyy-mm-dd" class="simple-input" name="birthdate" size=25 value="${birthdate}"> *
                </p>
                <br>
                <p>
                    Passport:
                    <br>
                    <input type="text" placeholder="passport series and number" class="simple-input"  name="passport" size=25 value="${passport}"> *
                </p>
                <br>
                <p>
                    Address:
                    <br>
                    <input type="text" placeholder="address" class="simple-input" name="address" size=25 value="${address}">
                </p>
                <br>
                <p>
                    E-mail (login):
                    <br>
                    <input type="text" placeholder="e-mail" class="simple-input" name="email" size=25 value="${email}"> *
                </p>
                <br>
                <p>
                    Password:
                    <br>
                    <input type="password" placeholder="password" class="simple-input" name="password1" size=25 value=""> *
                </p>
                <br>
                <p>
                    Repeat password:
                    <br>
                    <input type="password" placeholder="password" class="simple-input" name="password2" size=25 value=""> *
                </p>
                <br>
                <p>
                    (*) - required fields.
                </p>
                <br>
                <button type="submit" class="modern">Save</button>
                <a href="login.jsp" class="inline-link">Back</a>
            </div>
        </form>
    </div>
</div>

</body>
</html>
