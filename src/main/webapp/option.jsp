<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <title>Option</title>
</head>

<body>

<div class="outer-wrapper clearfix">

    <form id="formId1" method="post" action="login" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="logout">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="formId2" method="post" action="dashboard" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="viewDashboard">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="formId3" method="post" action="tariff" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value=${tariff.id}>
        <input type="hidden" name="action" value="viewTariff">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <h3>
        <div class="h3-logo"></div>
        Option:
        <a href="#" onclick="document.getElementById('formId1').submit()" class="h3-link">Exit</a>
        <a href="#" onclick="document.getElementById('formId2').submit()" class="h3-link">To dashboard</a>
        <a href="#" onclick="document.getElementById('formId3').submit()" class="h3-link">To tariff</a>

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
            Tariff ID: ${tariff.id}
        </p>
        <p>
            Tariff title: ${tariff.title}
        </p>
        <br>
        <p>
            Option ID: ${option.id}
        </p>
        <p>
            Option title: ${option.title}
        </p>
        <p>
            Option  price: ${option.price}
        </p>
        <p>
            Cost of connection: ${option.costOfConnection}
        </p>
        <br>
        <p>
        <form id="formId4" method="post" action="option" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="id" value="${option.id}">
            <input type="hidden" name="tariffId" value="${tariff.id}">
            <input type="hidden" name="action" value="editOption">
            <input type="hidden" name="sessionRole" value=${session.role}>
            <input type="hidden" name="sessionStatus" value=${session.isOn()}>
            <a class="inline-link" href="#" onclick="document.getElementById('formId4').submit()">Edit option dependencies</a>
        </form>
        </p>

    </div>

    <div class="inner-wrapper">

        <p>
            List of dependent options:
            <c:choose>
            <c:when test="${option.getDependentOptions().size() != 0}">
            <a class="inline-link-delete" title="Clear all dependencies" href="#" onclick="document.getElementById('formId5').submit()"></a>

            <form id="formId5" method="post" action="option" enctype="application/x-www-form-urlencoded">
                <input type="hidden" name="id" value="${option.id}">
                <input type="hidden" name="tariffId" value="${tariff.id}">
                <input type="hidden" name="action" value="removeAllDependentOptions">
                <input type="hidden" name="sessionRole" value=${session.role}>
                <input type="hidden" name="sessionStatus" value=${session.isOn()}>
            </form>

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
                    <c:forEach var="dependentOption" items="${option.getDependentOptions()}">
                        <tr>
                            <td>
                                ${dependentOption.id}
                            </td>
                            <td>
                                ${dependentOption.title}
                            </td>
                            <td>
                                ${dependentOption.price}
                            </td>
                            <td>
                                ${dependentOption.costOfConnection}
                            </td>
                            <td>
                                <form id="formId5${dependentOption.id}" method="post" action="option" enctype="application/x-www-form-urlencoded">
                                    <input type="hidden" name="id" value="${option.id}">
                                    <input type="hidden" name="dependentOptionId" value="${dependentOption.id}">
                                    <input type="hidden" name="tariffId" value="${tariff.id}">
                                    <input type="hidden" name="action" value="removeDependentOption">
                                    <input type="hidden" name="sessionRole" value=${session.role}>
                                    <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                    <a class="inline-link-delete" title="Remove dependency" href="#" onclick="document.getElementById('formId5${dependentOption.id}').submit()"></a>
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

    <div class="inner-wrapper">

        <p>
            List of incompatible options:
            <c:choose>
            <c:when test="${option.getIncompatibleOptions().size() != 0}">
            <a class="inline-link-delete" title="Clear all incompatibilities" href="#" onclick="document.getElementById('formId6').submit()"></a>

        <form id="formId6" method="post" action="option" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="id" value="${option.id}">
            <input type="hidden" name="tariffId" value="${tariff.id}">
            <input type="hidden" name="action" value="removeAllIncompatibleOptions">
            <input type="hidden" name="sessionRole" value=${session.role}>
            <input type="hidden" name="sessionStatus" value=${session.isOn()}>
        </form>

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
            <c:forEach var="incompatibleOption" items="${option.getIncompatibleOptions()}">
                <tr>
                    <td>
                            ${incompatibleOption.id}
                    </td>
                    <td>
                            ${incompatibleOption.title}
                    </td>
                    <td>
                            ${incompatibleOption.price}
                    </td>
                    <td>
                            ${incompatibleOption.costOfConnection}
                    </td>
                    <td>
                        <form id="formId6${incompatibleOption.id}" method="post" action="option" enctype="application/x-www-form-urlencoded">
                            <input type="hidden" name="id" value="${option.id}">
                            <input type="hidden" name="incompatibleOptionId" value="${incompatibleOption.id}">
                            <input type="hidden" name="tariffId" value="${tariff.id}">
                            <input type="hidden" name="action" value="removeIncompatibleOption">
                            <input type="hidden" name="sessionRole" value=${session.role}>
                            <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                            <a class="inline-link-delete" title="Remove incompatibility" href="#" onclick="document.getElementById('formId6${incompatibleOption.id}').submit()"></a>
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
