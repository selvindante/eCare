<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 15.10.2014
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Step 2. Choose options</title>
</head>
<body>
<header>Step 2. Choose options for tariff id: ${tariff.id}</header>
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
<p>
    Chosen tariff id: ${tariff.id}
</p>
<p>
    Chosen tariff title: ${tariff.title}
</p>
<hr>
<p>
    List of available options for chosen tariff:
</p>
<c:choose>
    <c:when test="${contract.getOptions().size() != 0}">
        <table>
            <tr>
                <td>
                    Option ID
                </td>
                <td>
                    Option title
                </td>
                <td>
                    Price
                </td>
                <td>
                    Cost of connection
                </td>
            </tr>
            <c:forEach var="option" items="${options}">
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
