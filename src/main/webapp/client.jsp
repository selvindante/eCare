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
<%
    Client client = (Client) (request.getAttribute("client"));
%>
<head>
    <title>Client page</title>
</head>
<body>
<header>Client:</header>
<p>
    ID: <%=client.getId()%>
</p>
<p>
    Name: <%=client.getName()%>
</p>
<p>
    Lastname: <%=client.getLastname()%>
</p>
<p>
    E-mail: <%=client.getEmail()%>
</p>
<p>
    Adress: <%=client.getAddress()%>
</p>
<p>
    Passport: <%=client.getPassport()%>
</p>
</body>
</html>
