<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Registration</title>
</head>
<body>

<div class="outer-wrapper clearfix">
    <h3>
        <%--<a href="login.jsp"><img src="images/logo.png" width="160" height="80" alt="eCare"></a>--%>
        LOGO
        Registration:
    </h3>
    <div class="inner-wrapper">
        <form method="post" action="registration" enctype="application/x-www-form-urlencoded">
            <div id="center">
                <p>
                    Name:
                    <br>
                    <input type="text" placeholder="name" class="simple-input" name="name" size=25 value="">
                </p>
                <br>
                <p>
                    Lastname:
                    <br>
                    <input type="text" placeholder="lastname" class="simple-input" name="lastname" size=25 value="">
                </p>
                <br>
                <p>
                    Birth date:
                    <br>
                    <input type="text" placeholder="yyyy-mm-dd" class="simple-input" name="birthdate" size=25 value="">
                </p>
                <br>
                <p>
                    Passport:
                    <br>
                    <input type="text" placeholder="passport series and number" class="simple-input"  name="passport" size=25 value="">
                </p>
                <br>
                <p>
                    Address:
                    <br>
                    <input type="text" placeholder="address" class="simple-input" name="address" size=25 value="">
                </p>
                <br>
                <p>
                    E-mail (login):
                    <br>
                    <input type="text" placeholder="e-mail" class="simple-input" name="email" size=25 value="">
                </p>
                <br>
                <p>
                    Password:
                    <br>
                    <input type="password" placeholder="password" class="simple-input" name="password" size=25 value="">
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
