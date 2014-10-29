<%@ page import="ru.tsystems.tsproject.ecare.Session" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <title>Login</title>
</head>

<body>

    <div class="outer-wrapper clearfix">

        <h3>
            <div class="h3-logo"></div>
        </h3>

        <c:if test="${errormessage != null}">
            <div class="inner-wrapper-error">
                <p>
                    Error: ${errormessage}
                </p>
            </div>
        </c:if>

        <div class="inner-wrapper">
                <form method="post" action="login" enctype="application/x-www-form-urlencoded">
                    <div id="center">
                        <input type="hidden" name="action" value="login">
                        <p>
                            Login:
                            &emsp;
                            &emsp;
                            <input type="text" placeholder="login" class="simple-input" name="login" size=20 value="">
                        </p>
                        <br>
                        <p>
                            Password:
                            &nbsp;
                            <input type="password" placeholder="password" class="simple-input" name="password" size=20 value="">
                        </p>
                        <br>
                        <button type="submit" class="modern">Enter</button>
                        <a href="#" onclick="document.getElementById('formId1').submit()" class="inline-link">Registration</a>
                    </div>
                </form>

            <form id="formId1" method="post" action="login" enctype="application/x-www-form-urlencoded">
                <input type="hidden" name="action" value="registration">
            </form>

        </div>

    </div>

</body>
</html>
