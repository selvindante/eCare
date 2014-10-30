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

    <form id="h3TariffForm" method="post" action="tariff" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value=${tariff.id}>
        <input type="hidden" name="action" value="viewTariff">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="h3OptionForm" method="post" action="option" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value=${option.id}>
        <input type="hidden" name="tariffId" value=${tariff.id}>
        <input type="hidden" name="action" value="viewOption">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="h3ContractForm" method="post" action="contract" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value=${contract.id}>
        <input type="hidden" name="action" value="viewContract">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <h3>
        <div class="h3-logo"></div>
        <a href="#" onclick="document.getElementById('h3ExitForm').submit()" class="h3-link" style="border-right-color: rgb(234, 234, 234)">EXIT</a>

        <c:if test="${session.role == 'admin'}">

            <a class="h3-link" href="#" onclick="document.getElementById('h3DashboardForm').submit()">DASHBOARD</a>
            <a class="h3-link" href="#" onclick="document.getElementById('h3TariffsForm').submit()">TARIFFS</a>

            <c:if test="${pagename == 'New contract'}">
                <a class="h3-link" href="#" onclick="document.getElementById('h3ClientForm').submit()">CLIENT</a>
            </c:if>

            <c:if test="${pagename == 'Option' || pagename == 'New option'}">
                <a class="h3-link" href="#" onclick="document.getElementById('h3TariffForm').submit()">TARIFF</a>
            </c:if>

            <c:if test="${pagename == 'Option settings'}">
                <a class="h3-link href="#" onclick="document.getElementById('h3TariffForm').submit()"">TARIFF</a>
                <a class="h3-link" href="#" onclick="document.getElementById('formId2').submit()">OPTION</a>
            </c:if>

        </c:if>

        <c:if test="${pagename == 'Contract' || pagename == 'Edit client'}">
            <a class="h3-link" href="#" onclick="document.getElementById('h3ClientForm').submit()">CLIENT</a>
        </c:if>

        <c:if test="${pagename == 'Choose tariff' || pagename == 'Choose options'}">
            <a class="h3-link" href="#" onclick="document.getElementById('h3ContractForm').submit()">CONTRACT</a>
        </c:if>

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
