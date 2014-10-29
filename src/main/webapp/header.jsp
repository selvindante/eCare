<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

    <form id="h3ExitForm" method="post" action="login" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="logout">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="h3TariffsForm" method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="viewAllTariffs">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="h3DashboardForm" method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="viewDashboard">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="h3ClientForm" method="post" action="client" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value=${client.id}>
        <input type="hidden" name="action" value="viewClient">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <h3>
        <div class="h3-logo"></div>
            <a href="#" onclick="document.getElementById('h3ExitForm').submit()" class="h3-link" style="border-right-color: rgb(234, 234, 234)">EXIT</a>
            <c:choose>
                <c:when test="${session.role == 'admin'}">
                    <a class="h3-link" href="#" onclick="document.getElementById('h3TariffsForm').submit()">TARIFFS</a>
                    <a class="h3-link" href="#" onclick="document.getElementById('h3DashboardForm').submit()">DASHBOARD</a>
                </c:when>
                <c:otherwise>
                    <a class="h3-link" href="#" onclick="document.getElementById('h3ClientForm').submit()">CLIENT</a>
                </c:otherwise>
            </c:choose>
    </h3>

    <div class="inner-wrapper-header">

        ${pagename}

        <c:if test="${session.role == 'admin'}">
            <form style="float: right" method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
                <input type="hidden" name="action" value="searchClientByNumber">
                <input type="hidden" name="sessionRole" value=${session.role}>
                <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                <input type="text" placeholder="client number" class="simple-input" name="number" size=20 value="">
                <button type="submit" class="modern">Search</button>
            </form>
        </c:if>

    </div>

    <c:if test="${errormessage != null}">
        <div class="inner-wrapper-error">
            <p>
                Error: ${errormessage}
            </p>
        </div>
    </c:if>

    <c:if test="${successmessage != null}">
        <div class="inner-wrapper-success">
            <p>
                Success: ${successmessage}
            </p>
        </div>
    </c:if>

</body>
</html>
