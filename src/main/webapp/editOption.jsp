<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <title>Edit option</title>
</head>

<body>

<div class="outer-wrapper clearfix">

    <%--<form id="formId1" method="post" action="login" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="logout">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="formId2" method="post" action="option" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value=${option.id}>
        <input type="hidden" name="tariffId" value=${tariff.id}>
        <input type="hidden" name="action" value="viewOption">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <h3>
        <div class="h3-logo"></div>
        Edit option:
        <a href="#" onclick="document.getElementById('formId1').submit()" class="h3-link">Exit</a>
        <a href="#" onclick="document.getElementById('formId2').submit()" class="h3-link">To option</a>
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

    </div>

    <div class="inner-wrapper">

        <form id="formId3" method="post" action="option" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="id" value="${option.id}">
            <input type="hidden" name="tariffId" value="${tariff.id}">
            <input type="hidden" name="action" value="updateOption">
            <input type="hidden" name="sessionRole" value=${session.role}>
            <input type="hidden" name="sessionStatus" value=${session.isOn()}>
            <p>
                Choose dependent options:
                <c:choose>
                    <c:when test="${tariff.getOptions().size() != 0}">
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
                                    Check
                                </td>
                            </tr>
                            <c:forEach var="dependentOption" items="${tariff.getOptions()}">
                                <c:if test="${option.id != dependentOption.id}">
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
                                        <c:choose>
                                            <c:when test="${option.getDependentOptions().contains(dependentOption)}">
                                                <td>
                                                    <input type="checkbox" id="box1${dependentOption.id}" name="dependentOptions" value="${dependentOption.id}" checked="checked">
                                                </td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>
                                                    <input type="checkbox" id="box1${dependentOption.id}" name="dependentOptions" value="${dependentOption.id}">
                                                </td>
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        empty.
                        </p>
                        <br>
                    </c:otherwise>
                </c:choose>

            <hr>

            <br>

            <p>
                Choose incompatible options:
                <c:choose>
                <c:when test="${tariff.getOptions().size() != 0}">
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
                        Check
                    </td>
                </tr>
                <c:forEach var="incompatibleOption" items="${tariff.getOptions()}">
                    <c:if test="${option.id != incompatibleOption.id}">
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
                            <c:choose>
                                <c:when test="${option.getIncompatibleOptions().contains(incompatibleOption)}">
                                    <td>
                                        <input type="checkbox" id="box2${incompatibleOption.id}" name="incompatibleOptions" value="${incompatibleOption.id}" checked="checked">
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <input type="checkbox" id="box2${incompatibleOption.id}" name="incompatibleOptions" value="${incompatibleOption.id}">
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
            </c:when>
            <c:otherwise>
                empty.
                </p>
                <br>
            </c:otherwise>
            </c:choose>

            <button type="submit" class="modern">Save</button>
        </form>
    </div>

    <jsp:include page="footer.jsp"></jsp:include>

</div>

</body>
</html>
