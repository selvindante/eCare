<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ru.tsystems.tsproject.ecare.entities.Client" %>
<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 12.10.2014
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Operator's dashboard</title>
</head>
<body>
    <header>Operator's Dashboard:</header>
</body>
<p>
    Role(temporary): ${role}
</p>
<p>
    Search client by phone number:
</p>
<form id="dashboard" method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
    <p>
        Number: <input type="text" name="number" size=25 value=""> <button type="submit">Search</button>
    </p>
</form>
<p>
    <a href='<%=request.getContextPath()%>dashboard?&action=viewAllTariffs'>View all tariffs</a>
</p>
<p>
    List of clients:
</p>
<table>
    <tr>
        <td>
            Client ID
        </td>
        <td>
            Name
        </td>
        <td>
            Passport
        </td>
        <td>
            E-mail
        </td>
    </tr>
    <c:forEach var="client" items="${clientsList}">
    <tr>
        <td>
            <a href='<%=request.getContextPath()%>dashboard?id=${client.id}&action=viewClient'>${client.id}</a>
        </td>
        <td>
            ${client.name}
        </td>
        <td>
            ${client.passport}
        </td>
        <td>
            ${client.email}
        </td>
    </tr>
    </c:forEach>
</table>

</html>
