<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ru.tsystems.tsproject.ecare.entities.Client" %>
<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 12.10.2014
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client page</title>
</head>
<body>
<header>Client:</header>
<p>
    Role(temporary): ${role}
</p>
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
<p>
    <c:if test="${role == 'admin'}">
        <a href='<%=request.getContextPath()%>client?id=${client.id}&action=createContract'>Create Contract</a>
    </c:if>
</p>

<p>
    List of contracts:
</p>
    <c:choose>
        <c:when test="${client.getContracts().size() != 0}">
            <table>
                <tr>
                    <td>
                        Contract ID
                    </td>
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
                </tr>
                <c:forEach var="contract" items="${client.getContracts()}">
                <tr>
                    <td>
                        ${contract.id}
                    </td>
                    <td>
                        ${contract.number}
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
                </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            This client has no contracts yet.
        </c:otherwise>
    </c:choose>
</body>
</html>
