<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <title>Tariff</title>
</head>

<body>

<div class="outer-wrapper clearfix">

    <%--<form id="formId1" method="post" action="login" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="logout">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="formId2" method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="viewDashboard">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="formId3" method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="viewAllTariffs">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <h3>
        <div class="h3-logo"></div>
        Tariff:
        <a href="#" onclick="document.getElementById('formId1').submit()" class="h3-link">Exit</a>
        <a href="#" onclick="document.getElementById('formId2').submit()" class="h3-link">To dashboard</a>
        <a href="#" onclick="document.getElementById('formId3').submit()" class="h3-link">To tariffs list</a>
    </h3>--%>

    <jsp:include page="header.jsp"></jsp:include>

    <c:if test="${errormessage != null}">
        <div class="inner-wrapper-error">
            <p>
                Error: ${errormessage}
            </p>
        </div>
    </c:if>

    <div class="inner-wrapper">

        <%--<p>
            Role(temporary): ${session.role}
        </p>
        <p>
            Session(temporary): ${session.isOn()}
        </p>
        <br>--%>

        <p>
            Tariff ID: ${tariff.id}
        </p>
        <p>
            Title: ${tariff.title}
        </p>
        <p>
            Price: ${tariff.price}
        </p>

    </div>

    <div class="inner-wrapper">

        <p>

            <form id="formId4" method="post" action="tariff" enctype="application/x-www-form-urlencoded">
                <input type="hidden" name="id" value="${tariff.id}">
                <input type="hidden" name="action" value="createOption">
                <input type="hidden" name="sessionRole" value=${session.role}>
                <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                <a class="inline-link" href="#" onclick="document.getElementById('formId4').submit()">Create new option</a>
            </form>

        </p>
        <br>
        <p>
            List of available options:
            <c:choose>
                <c:when test="${tariff.getOptions().size() != 0}">

                    <%--<a class="inline-link" href="#" onclick="document.getElementById('formId5').submit()">(clear list)</a>

                    <form id="formId5" method="post" action="tariff" enctype="application/x-www-form-urlencoded">
                        <input type="hidden" name="id" value="${tariff.id}">
                        <input type="hidden" name="action" value="deleteAllOptions">
                        <input type="hidden" name="sessionRole" value=${session.role}>
                        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                    </form>--%>

                    </p>
                    <br>
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
                            <td>
                                    ${HtmlUtil.EMPTY_TD}
                            </td>
                        </tr>
                        <c:forEach var="option" items="${tariff.getOptions()}">
                            <tr>
                                <td>

                                    <form id="formId6${option.id}" method="post" action="option" enctype="application/x-www-form-urlencoded">
                                        <input type="hidden" name="id" value=${option.id}>
                                        <input type="hidden" name="tariffId" value=${tariff.id}>
                                        <input type="hidden" name="action" value="viewOption">
                                        <input type="hidden" name="sessionRole" value=${session.role}>
                                        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                        <a class="inline-link" href="#" onclick="document.getElementById('formId6${option.id}').submit()">${option.id}</a>
                                    </form>

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

                                    <form id="formId7${option.id}" method="post" action="option" enctype="application/x-www-form-urlencoded">
                                        <input type="hidden" name="id" value=${option.id}>
                                        <input type="hidden" name="action" value="deleteOption">
                                        <input type="hidden" name="tariffId" value=${tariff.id}>
                                        <input type="hidden" name="sessionRole" value=${session.role}>
                                        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                        <a class="inline-link-delete" title="Delete option" href="#" onclick="document.getElementById('formId7${option.id}').submit()"></a>
                                    </form>

                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    empty.
                </c:otherwise>
            </c:choose>

    </div>

</div>

</body>
</html>
