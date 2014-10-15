<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 15.10.2014
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Step 1. Choose tariff</title>
</head>
<body>
<header>Step 1. Choose new tariff.</header>
<hr>
<p>
    Basket.
</p>
<p>
    Contract ID: ${contract.id}
</p>
<p>
    Contract number: ${contract.number}
</p>
<hr>
<p>
    List of available tariffs:
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
                ${tariff.id}
            </td>
            <td>
                ${tariff.title}
            </td>
            <td>
                ${tariff.price}
            </td>
            <td>
                <a href='<%=request.getContextPath()%>chooseTariff?contractId=${contract.id}&tariffId=${tariff.id}&action=chooseTariff'>select</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
