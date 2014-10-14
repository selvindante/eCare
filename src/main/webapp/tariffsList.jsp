<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 14.10.2014
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of all tariffs</title>
</head>
<body>
<header>List of all tariffs.</header>
<p>
    <a href='<%=request.getContextPath()%>tariffsList?&action=createTariff'>Create tariff</a>
</p>
<table>
    <tr>
        <td>
            Tariff ID
        </td>
        <td>
            Title
        </td>
        <td>
            Price
        </td>
    </tr>
    <c:forEach var="tariff" items="${tariffs}">
        <tr>
            <td>
                <a href='<%=request.getContextPath()%>tariff?id=${tariff.id}&action=view'>${tariff.id}</a>
            </td>
            <td>
                ${tariff.title}
            </td>
            <td>
                ${tariff.price}
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
