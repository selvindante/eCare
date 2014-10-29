<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <title>Creating of option</title>
</head>

<body>

<div class="outer-wrapper clearfix">

    <%--<form id="formId1" method="post" action="login" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="logout">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="formId2" method="post" action="tariff" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value=${tariff.id}>
        <input type="hidden" name="action" value="viewTariff">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <h3>
        <div class="h3-logo"></div>
        Creating of option:
        <a href="#" onclick="document.getElementById('formId1').submit()" class="h3-link">Exit</a>
        <a href="#" onclick="document.getElementById('formId2').submit()" class="h3-link">To tariff</a>
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

        <form id="formId3" method="post" action="option" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="tariffId" value="${tariff.id}">
            <input type="hidden" name="action" value="createOption">
            <input type="hidden" name="sessionRole" value=${session.role}>
            <input type="hidden" name="sessionStatus" value=${session.isOn()}>
            <p>
                Title:
                <br>
                <input type="text" placeholder="title" class="simple-input" name="title" size=20 value=""> *
            </p>
            <br>
            <p>
                Price:
                <br>
                <input type="text" placeholder="price" class="simple-input" name="price" size=10 value=""> *
            </p>
            <br>
            <p>
                Cost of connection:
                <br>
                <input type="text" placeholder="cost of connection" class="simple-input" name="costOfConnection" size=10 value=""> *
            </p>
            <br>
            <p>
                (*) - required fields.
            </p>
            <br>

            <hr>

            <br>

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
                                        <input type="checkbox" id="box1${dependentOption.id}" name="dependentOptions" value="${dependentOption.id}">
                                    </td>
                                </tr>
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
                            <input type="checkbox" id="box2${incompatibleOption.id}" name="incompatibleOptions" value="${incompatibleOption.id}">
                        </td>
                    </tr>
                </c:forEach>
            </table>
            </c:when>
            <c:otherwise>
                empty.
                </p>
                <br>
            </c:otherwise>
            </c:choose>

            <button type="submit" class="modern">Create</button>
        </form>

    </div>

</div>



</body>
</html>
