<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ru.tsystems.tsproject.ecare.entities.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <title>Client page</title>
</head>
<body>

<div class="outer-wrapper clearfix">

    <form id="formId1" method="post" action="login" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="logout">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="formId2" method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="viewDashboard">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <h3>
        <div class="h3-logo"></div>
        Client page:
        <a href="#" onclick="document.getElementById('formId1').submit()" class="h3-link">Exit</a>
        <c:if test="${session.role == 'admin'}">
            <a href="#" onclick="document.getElementById('formId2').submit()" class="h3-link">To dashboard</a>
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
        <p>
            Personal info: <a href="#" onclick="document.getElementById('formId3').submit()" class="inline-link-edit" title="Edit client personal info"></a>
        </p>

        <form id="formId3" method="post" action="client" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="id" value=${client.id}>
            <input type="hidden" name="action" value="editClient">
            <input type="hidden" name="sessionRole" value=${session.role}>
            <input type="hidden" name="sessionStatus" value=${session.isOn()}>
        </form>

        <br>
        <p>
            ID: ${client.id}
        </p>
        <p>
            Name: ${client.name}
        </p>
        <p>
            Lastname: ${client.lastname}
        </p>
        <p>
            Birthdate: ${client.getBirthDateToString()}
        </p>
        <p>
            E-mail: ${client.email}
        </p>
        <p>
            Address: ${client.address}
        </p>
        <p>
            Passport: ${client.passport}
        </p>
        <p>
            Balance: ${client.amount}
        </p>
        <br>
        <form method="post" action="client" enctype="application/x-www-form-urlencoded">
            <p>
                <input type="hidden" name="id" value=${client.id}>
                <input type="hidden" name="action" value="addAmount">
                <input type="hidden" name="sessionRole" value=${session.role}>
                <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                <input type="text" placeholder="amount" class="simple-input" name="amount" size=10 value="">
                <button type="submit" class="modern">Add amount</button>
            </p>
        </form>
    </div>

    <div class="inner-wrapper">
        <c:if test="${session.role == 'admin'}">
            <p>
            <form id="formId4" method="post" action="client" enctype="application/x-www-form-urlencoded">
                <input type="hidden" name="id" value="${client.id}">
                <input type="hidden" name="action" value="createContract">
                <input type="hidden" name="sessionRole" value=${session.role}>
                <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                <a class="inline-link" href="#" onclick="document.getElementById('formId4').submit()">Add new contract</a>
            </form>
            </p>
            <br>
        </c:if>
        <p>
            List of contracts:
        <c:choose>
            <c:when test="${client.getContracts().size() != 0}">
            </p>
            <br>
                <table>
                    <tr>
                        <td>
                            Number
                        </td>
                        <td>
                            Tariff
                        </td>
                        <td>
                            Is blocked by client?
                        </td>
                        <td>
                            Is blocked by operator?
                        </td>
                        <c:if test="${session.role == 'admin'}">
                            <td>
                                ${HtmlUtil.EMPTY_TD}
                            </td>
                        </c:if>
                        <td>
                                ${HtmlUtil.EMPTY_TD}
                        </td>
                    </tr>
                    <c:forEach var="contract" items="${client.getContracts()}">
                        <tr>
                            <td>

                                <form id="formId1${contract.id}" method="post" action="contract" enctype="application/x-www-form-urlencoded">
                                    <input type="hidden" name="id" value=${contract.id}>
                                    <input type="hidden" name="action" value="viewContract">
                                    <input type="hidden" name="sessionRole" value=${session.role}>
                                    <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                    <a class="inline-link" href="#" onclick="document.getElementById('formId1${contract.id}').submit()">${contract.number}</a>
                                </form>

                            </td>
                            <td>
                                ${contract.tariff.title}
                            </td>
                            <td>
                                ${contract.isBlockedByClient()}
                            </td>
                            <td>
                                ${contract.isBlockedByOperator()}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${session.role == 'admin'}">

                                        <c:choose>
                                            <c:when test="${contract.isBlockedByOperator() == true}">

                                                <form id="formId3${contract.id}" method="post" action="contract" enctype="application/x-www-form-urlencoded">
                                                    <input type="hidden" name="id" value=${contract.id}>
                                                    <input type="hidden" name="action" value="unblockByOperator">
                                                    <input type="hidden" name="sessionRole" value=${session.role}>
                                                    <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                                    <a class="inline-link-unlock" title="Unblock contract" href="#" onclick="document.getElementById('formId3${contract.id}').submit()"></a>
                                                </form>

                                            </c:when>
                                            <c:otherwise>

                                                <form id="formId4${contract.id}" method="post" action="contract" enctype="application/x-www-form-urlencoded">
                                                    <input type="hidden" name="id" value=${contract.id}>
                                                    <input type="hidden" name="action" value="blockByOperator">
                                                    <input type="hidden" name="sessionRole" value=${session.role}>
                                                    <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                                    <a class="inline-link-lock" title="Block contract" href="#" onclick="document.getElementById('formId4${contract.id}').submit()"></a>
                                                </form>

                                            </c:otherwise>
                                        </c:choose>

                                        </td>

                                        <td>

                                            <form id="formId2${contract.id}" method="post" action="contract" enctype="application/x-www-form-urlencoded">
                                                <input type="hidden" name="id" value=${contract.id}>
                                                <input type="hidden" name="action" value="deleteContract">
                                                <input type="hidden" name="sessionRole" value=${session.role}>
                                                <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                                <a class="inline-link-delete" title="Delete contract" href="#" onclick="document.getElementById('formId2${contract.id}').submit()"></a>
                                            </form>

                                    </c:when>
                                    <c:when test="${session.role == 'client' && contract.isBlockedByOperator() == false}">
                                        <c:choose>
                                            <c:when test="${contract.isBlockedByClient() == true}">

                                                <form id="formId5${contract.id}" method="post" action="contract" enctype="application/x-www-form-urlencoded">
                                                    <input type="hidden" name="id" value=${contract.id}>
                                                    <input type="hidden" name="action" value="unblockByClient">
                                                    <input type="hidden" name="sessionRole" value=${session.role}>
                                                    <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                                    <a class="inline-link-unlock" title="Unblock contract" href="#" onclick="document.getElementById('formId5${contract.id}').submit()"></a>
                                                </form>

                                            </c:when>
                                            <c:otherwise>

                                                <form id="formId6${contract.id}" method="post" action="contract" enctype="application/x-www-form-urlencoded">
                                                    <input type="hidden" name="id" value=${contract.id}>
                                                    <input type="hidden" name="action" value="blockByClient">
                                                    <input type="hidden" name="sessionRole" value=${session.role}>
                                                    <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                                    <a class="inline-link-lock" title="Block contract" href="#" onclick="document.getElementById('formId6${contract.id}').submit()"></a>
                                                </form>

                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                 empty.
                </p>
            </c:otherwise>
        </c:choose>
    </div>

</div>

</body>
</html>
