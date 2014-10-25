<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="ru.tsystems.tsproject.ecare.entities.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <title>Operator's dashboard</title>
</head>

<div class="outer-wrapper clearfix">

    <form id="formId1" method="post" action="login" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="logout">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <h3>
        LOGO
        Dashboard:
        <a href="#" onclick="document.getElementById('formId1').submit()" class="h3-link">Exit</a>
    </h3>

    <c:if test="${errormessage != null}">
        <div class="inner-wrapper-error">
            <p>
                Error: ${errormessage}
            </p>
        </div>
    </c:if>

    <div class="inner-wrapper">

        <p>
            Role(temporary): ${session.role}
        </p>
        <p>
            Session(temporary): ${session.isOn()}
        </p>
        <br>
        <p>
            Search client by phone number:
        </p>
        <form method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
            <p>
                <input type="hidden" name="action" value="searchClientByNumber">
                <input type="hidden" name="sessionRole" value=${session.role}>
                <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                Number: <input type="text" placeholder="telephone number" class="simple-input" name="number" size=20 value="">
                <button type="submit" class="modern">Search</button>
            </p>
        </form>
        <br>
        <p>
            <form id="formId2" method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
                <input type="hidden" name="action" value="viewAllTariffs">
                <input type="hidden" name="sessionRole" value=${session.role}>
                <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                <a class="inline-link" href="#" onclick="document.getElementById('formId2').submit()">View all tariffs</a>
            </form>
        </p>

    </div>

    <div class="inner-wrapper">

        <p>
            List of clients: <a class="inline-link" href="#" onclick="document.getElementById('formId3').submit()">(clear list)</a>

            <form id="formId3" method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
                <input type="hidden" name="action" value="deleteAllClients">
                <input type="hidden" name="sessionRole" value=${session.role}>
                <input type="hidden" name="sessionStatus" value=${session.isOn()}>
            </form>

            <c:choose>
                <c:when test="${contract.getOptions().size() != 0}">
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
                                    <%--<a href='<%=request.getContextPath()%>dashboard?id=${client.id}&action=deleteClient' class="inline-link">delete</a>--%>

                                    <form id="formId3${client.id}" method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
                                        <input type="hidden" name="id" value=${client.id}>
                                        <input type="hidden" name="action" value="deleteClient">
                                        <input type="hidden" name="sessionRole" value=${session.role}>
                                        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                        <a class="inline-link" href="#" onclick="document.getElementById('formId3${client.id}').submit()">delete</a>
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

</div>

</html>
