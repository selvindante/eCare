<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Edit client info</title>
</head>
<body>

<div class="outer-wrapper clearfix">

    <form id="formId1" method="post" action="login" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="logout">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="formId2" method="post" action="client" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value=${client.id}>
        <input type="hidden" name="action" value="viewClient">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="formId3" method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="viewDashboard">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <h3>
        LOGO
        Edit client:
        <a href="#" onclick="document.getElementById('formId1').submit()" class="h3-link">Exit</a>
        <a href="#" onclick="document.getElementById('formId2').submit()" class="h3-link">Client page</a>
        <c:if test="${session.role == 'admin'}">
            <a href="#" onclick="document.getElementById('formId3').submit()" class="h3-link">To dashboard</a>
        </c:if>
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
            Role(temporary): ${session.role}
        </p>
        <p>
            Session(temporary): ${session.isOn()}
        </p>
        <br>

        <form method="post" action="client" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="id" value=${client.id}>
            <input type="hidden" name="action" value="updateClient">
            <input type="hidden" name="sessionRole" value=${session.role}>
            <input type="hidden" name="sessionStatus" value=${session.isOn()}>
            <div id="center">
                <p>
                    Name:
                    <br>
                    <input type="text" class="simple-input" name="name" size=25 value="${client.name}">
                </p>
                <br>
                <p>
                    Lastname:
                    <br>
                    <input type="text" class="simple-input" name="lastname" size=25 value="${client.lastname}">
                </p>
                <br>
                <p>
                    Birth date:
                    <br>
                    <input type="text" class="simple-input" name="birthdate" size=25 value="${client.getBirthDateToString()}">
                </p>
                <br>
                <p>
                    Passport:
                    <br>
                    <input type="text" class="simple-input"  name="passport" size=25 value="${client.passport}">
                </p>
                <br>
                <p>
                    Address:
                    <br>
                    <input type="text" class="simple-input" name="address" size=25 value="${client.address}">
                </p>
                <br>
                <button type="submit" class="modern">Save</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
