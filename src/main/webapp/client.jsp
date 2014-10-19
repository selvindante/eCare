<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ru.tsystems.tsproject.ecare.entities.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Client page</title>
</head>
<body>

<div class="outer-wrapper clearfix">

    <form id="formId" method="post" action="login" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="logout">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <h3>
        LOGO
        Client page:
        <a href="#" onclick="document.getElementById('formId').submit()" class="h3-link">Exit</a>
        <c:if test="${session.role} == 'admin'}">
            <a href='<%=request.getContextPath()%>client?id=${client.id}&action=deleteClient' class="h3-link">Delete client</a>
            <a href='<%=request.getContextPath()%>client?id=${client.id}&action=createContract' class="h3-link">Create contract</a>
        </c:if>
    </h3>
    <div class="inner-wrapper">
        <p>
            Role(temporary): ${session.role}
        </p>
        <p>
            Session(temporary): ${session.isOn()}
        </p>
        <br>
        <p>
            Personal info:
        </p>
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
            Birthdate: ${client.birthDate}
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
            Amount: ${client.amount}
        </p>
    </div>
    <div class="inner-wrapper">
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
                                Delete
                            </td>
                        </c:if>
                        <td>
                            Block/ublock
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

                                        <form id="formId2${contract.id}" method="post" action="contract" enctype="application/x-www-form-urlencoded">
                                            <input type="hidden" name="id" value=${contract.id}>
                                            <input type="hidden" name="action" value="deleteContract">
                                            <input type="hidden" name="sessionRole" value=${session.role}>
                                            <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                            <a class="inline-link" href="#" onclick="document.getElementById('formId2${contract.id}').submit()">delete</a>
                                        </form>

                                        <%--<a href='<%=request.getContextPath()%>contract?id=${contract.id}&action=deleteContract' class="inline-link">(delete)</a>--%>
                                        <c:choose>
                                            <c:when test="${contract.isBlockedByOperator() == true}">

                                                <form id="formId3${contract.id}" method="post" action="contract" enctype="application/x-www-form-urlencoded">
                                                    <input type="hidden" name="id" value=${contract.id}>
                                                    <input type="hidden" name="action" value="unblockByOperator">
                                                    <input type="hidden" name="sessionRole" value=${session.role}>
                                                    <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                                    <a class="inline-link" href="#" onclick="document.getElementById('formId3${contract.id}').submit()">unblock</a>
                                                </form>

                                                <%--<a href='<%=request.getContextPath()%>contract?id=${contract.id}&action=unblockByOperator' class="inline-link">(unblock)</a>--%>
                                            </c:when>
                                            <c:otherwise>

                                                <form id="formId4${contract.id}" method="post" action="contract" enctype="application/x-www-form-urlencoded">
                                                    <input type="hidden" name="id" value=${contract.id}>
                                                    <input type="hidden" name="action" value="blockByOperator">
                                                    <input type="hidden" name="sessionRole" value=${session.role}>
                                                    <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                                    <a class="inline-link" href="#" onclick="document.getElementById('formId4${contract.id}').submit()">block</a>
                                                </form>

                                                <%--<a href='<%=request.getContextPath()%>contract?id=${contract.id}&action=blockByOperator' class="inline-link">(block)</a>--%>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:when test="${session.role == 'client' && contract.isBlockedByOperator() == false}">
                                        <c:choose>
                                            <c:when test="${contract.isBlockedByClient() == true}">

                                                <form id="formId5${contract.id}" method="post" action="contract" enctype="application/x-www-form-urlencoded">
                                                    <input type="hidden" name="id" value=${contract.id}>
                                                    <input type="hidden" name="action" value="unblockByClient">
                                                    <input type="hidden" name="sessionRole" value=${session.role}>
                                                    <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                                    <a class="inline-link" href="#" onclick="document.getElementById('formId5${contract.id}').submit()">unblock</a>
                                                </form>

                                                <%--<a href='<%=request.getContextPath()%>contract?id=${contract.id}&action=unblockByClient' class="inline-link">(unblock)</a>--%>
                                            </c:when>
                                            <c:otherwise>

                                                <form id="formId6${contract.id}" method="post" action="contract" enctype="application/x-www-form-urlencoded">
                                                    <input type="hidden" name="id" value=${contract.id}>
                                                    <input type="hidden" name="action" value="blockByClient">
                                                    <input type="hidden" name="sessionRole" value=${session.role}>
                                                    <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                                    <a class="inline-link" href="#" onclick="document.getElementById('formId6${contract.id}').submit()">block</a>
                                                </form>

                                                <%--<a href='<%=request.getContextPath()%>contract?id=${contract.id}&action=blockByClient' class="inline-link">(block)</a>--%>
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

</body>
</html>
