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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Operator's dashboard</title>
</head>
<body>
    <header>Operator's Dashboard:</header>
</body>

<hr>

<p>
    Role(temporary): ${role}
</p>

<hr>

<p>
    Search client by phone number:
</p>
<form method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
    <p>
        Number: <input type="text" placeholder="telephone number" class="simple-input"  name="number" size=20 value="">
        <button type="submit" class="modern">Search</button>
    </p>
</form>

<hr>

<p>
    <a href='<%=request.getContextPath()%>dashboard?&action=viewAllTariffs' class="inline-link">View all tariffs</a>
</p>

<hr>

<p>
    List of clients. <a href='<%=request.getContextPath()%>dashboard?action=deleteAllClients' class="inline-link">(clear list)</a>
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
            <a href='<%=request.getContextPath()%>dashboard?id=${client.id}&action=viewClient' class="inline-link">${client.id}</a>
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
        <td>
            <a href='<%=request.getContextPath()%>dashboard?id=${client.id}&action=deleteClient' class="inline-link">delete</a>
        </td>
    </tr>
    </c:forEach>
</table>

</html>
