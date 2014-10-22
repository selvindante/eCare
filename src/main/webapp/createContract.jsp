<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>New contract</title>
</head>
<body>

<div class="outer-wrapper clearfix">

    <form id="formId1" method="post" action="login" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="logout">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="formId3" method="post" action="client" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value=${client.id}>
        <input type="hidden" name="action" value="viewClient">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <h3>
        LOGO
        New contract:
        <a href="#" onclick="document.getElementById('formId1').submit()" class="h3-link">Exit</a>
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
        <p>
            Creating of new contract for client: ${client.email}
        </p>
        <br>

        <form id="formId2" method="post" action="createContract" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="id" value="${client.id}">
            <input type="hidden" name="sessionRole" value=${session.role}>
            <input type="hidden" name="sessionStatus" value=${session.isOn()}>
            <p>
                Telephone number (must be unique): <input type="text" placeholder="telephone number" class="simple-input" name="number" size=25 value="">
            </p>
            <button type="submit" class="modern">Create</button>
            <a class="inline-link" href="#" onclick="document.getElementById('formId3').submit()">Back</a>
        </form>

    </div>

</div>

</body>
</html>
