<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ru.tsystems.tsproject.ecare.entities.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <title>Operator's dashboard</title>
</head>
<body>

<div class="outer-wrapper clearfix">

    <%--<form id="h3ExitForm" method="post" action="login" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="logout">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="h3TariffsForm" method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="viewAllTariffs">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="h3DashboardForm" method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="viewDashboard">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="h3ClientForm" method="post" action="client" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value=${client.id}>
        <input type="hidden" name="action" value="viewClient">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <h3>
        <div class="h3-logo"></div>
        <a href="#" onclick="document.getElementById('h3ExitForm').submit()" class="h3-link">EXIT</a>
        <c:choose>
            <c:when test="${session.role == 'admin'}">
                <a class="h3-link" href="#" onclick="document.getElementById('h3TariffsForm').submit()">TARIFFS</a>
                <a class="h3-link" href="#" onclick="document.getElementById('h3DashboardForm').submit()">DASHBOARD</a>
            </c:when>
            <c:otherwise>
                <a class="h3-link" href="#" onclick="document.getElementById('h3ClientForm').submit()">CLIENT</a>
            </c:otherwise>
        </c:choose>
    </h3>--%>

    <jsp:include page="header.jsp"></jsp:include>

    <div class="inner-wrapper">

        <p>
            List of clients: <%--<a class="inline-link" href="#" onclick="document.getElementById('formId3').submit()">(clear list)</a>

            <form id="formId3" method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
                <input type="hidden" name="action" value="deleteAllClients">
                <input type="hidden" name="sessionRole" value=${session.role}>
                <input type="hidden" name="sessionStatus" value=${session.isOn()}>
            </form>--%>

            <c:choose>
                <c:when test="${clientsList.size() != 0}">
                </p>
                <br>
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
                            <td>
                                    ${HtmlUtil.EMPTY_TD}
                            </td>
                        </tr>
                        <c:forEach var="client" items="${clientsList}">
                            <tr>
                                <td>

                                    <form id="formId2${client.id}" method="post" action="client" enctype="application/x-www-form-urlencoded">
                                        <input type="hidden" name="id" value=${client.id}>
                                        <input type="hidden" name="action" value="viewClient">
                                        <input type="hidden" name="sessionRole" value=${session.role}>
                                        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                        <a class="inline-link" href="#" onclick="document.getElementById('formId2${client.id}').submit()">${client.id}</a>
                                    </form>

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

                                    <form id="formId3${client.id}" method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
                                        <input type="hidden" name="id" value=${client.id}>
                                        <input type="hidden" name="action" value="deleteClient">
                                        <input type="hidden" name="sessionRole" value=${session.role}>
                                        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                        <a class="inline-link-delete" title="Delete client" href="#" onclick="document.getElementById('formId3${client.id}').submit()"></a>
                                    </form>

                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    empty.
                    </p>
                </c:otherwise>
            </c:choose>

    </div>

    <jsp:include page="footer.jsp"></jsp:include>

</div>

</body>

</html>
