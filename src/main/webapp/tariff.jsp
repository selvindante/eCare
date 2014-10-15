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
    <header>Tariff: <a href='<%=request.getContextPath()%>tariff?id=${tariff.id}&action=deleteTariff'>(delete)</a></header>

    <hr>

    <p>
        Tariff ID: ${tariff.id}
    </p>
    <p>
        Title: ${tariff.title}
    </p>
    <p>
        Price: ${tariff.price}
    </p>

    <hr>

    <p>
        <a href='<%=request.getContextPath()%>tariff?id=${tariff.id}&action=createOption'>Create new option</a>
    </p>
    <p>
        List of available options: <a href='<%=request.getContextPath()%>tariff?action=deleteAllOptions'>(clear list)</a>
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
                            <a href='<%=request.getContextPath()%>option?id=${option.id}&action=viewOption'>${option.id}</a>
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
                        <td>
                            <a href='<%=request.getContextPath()%>option?id=${option.id}&action=deleteOption'>(delete)</a>
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
