<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Login</title>
</head>
<body>

    <div class="outer-wrapper clearfix">
        <h3>
            <%--<a href="login.jsp"><img src="images/logo.png" width="160" height="80" alt="eCare"></a>--%>
            LOGO
            Authorization
        </h3>
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
                        <a href="registration.jsp" class="inline-link">Registration</a>
                    </div>
                </form>
        </div>
    </div>

</body>
</html>
