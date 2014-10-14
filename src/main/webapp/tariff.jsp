<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 14.10.2014
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tariff</title>
</head>
<body>
    <header>Tariff:</header>
    <p>
        Tariff ID: ${tariff.id}
    </p>
    <p>
        Title: ${tariff.title}
    </p>
    <p>
        Price: ${tariff.price}
    </p>
    <p>
        <a href='<%=request.getContextPath()%>tariff?id=${tariff.id}&action=createOption'>Create option</a>
    </p>
    <p>
        List of available options:
    </p>
    <c:choose>
        <c:when test="${tariff.getOptions().size() != 0}">
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
                <c:forEach var="option" items="${tariff.getOptions()}">
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
            This tariff has no available options yet.
        </c:otherwise>
    </c:choose>
</body>
</html>
