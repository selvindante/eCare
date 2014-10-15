<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 15.10.2014
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contract</title>
</head>
<body>
<header>Contract</header>
<hr>
<p>
    Contract ID: ${contract.id}
</p>
<p>
    Number: ${contract.number}
</p>
<p>
    Tariff title: ${contract.tariff.title}
</p>
<p>
    Is blocked by operator: ${contract.isBlockedByOperator()}
</p>
<p>
    Is blocked by client: ${contract.isBlockedByClient()}
</p>

<c:if test="${contract.isBlockedByOperator() == false && contract.isBlockedByClient() == false}">
    <p>
        <a href='<%=request.getContextPath()%>contract?id=${contract.id}&action=changeTariff'>Change tariff</a>
    </p>
</c:if>

<hr>

<p>
    List of connected options:
</p>
<c:choose>
    <c:when test="${contract.getOptions().size() != 0}">
        <table>
            <tr>
                <td>
                    Option ID
                </td>
                <td>
                    Title
                </td>
                <td>
                    Price
                </td>
                <td>
                    Cost of connection
                </td>
            </tr>
            <c:forEach var="option" items="${contract.getOptions()}">
                <tr>
                    <td>
                        ${option.id}
                    </td>
                    <td>
                        ${option.title}
                    </td>
                    <td>
                        ${option.price}
                    </td>
                    <td>
                        ${option.costOfConnection}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        This contract has no connected options yet.
    </c:otherwise>
</c:choose>

</body>
</html>
