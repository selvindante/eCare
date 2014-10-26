<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <title>Step 1. Choose tariff</title>
</head>
<body>

<div class="outer-wrapper clearfix">

    <form id="formId1" method="post" action="login" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="action" value="logout">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <form id="formId2" method="post" action="contract" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value=${contract.id}>
        <input type="hidden" name="action" value="viewContract">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <h3>
        <div class="h3-logo"></div>
        Available tariffs:
        <a href="#" onclick="document.getElementById('formId1').submit()" class="h3-link">Exit</a>
        <a href="#" onclick="document.getElementById('formId2').submit()" class="h3-link">Contract page</a>
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
            Basket:
        </p>
        <br>
        <p>
            Contract number: ${contract.number}
        </p>
        <p>
            Client balance: ${contract.getClient().amount}
        </p>
    </div>

    <div class="inner-wrapper">
        <p>
            Step 1. Choose new tariff.
        </p>
        <br>
        <p>
            List of available tariffs:
                <c:choose>
                    <c:when test="${tariffs.size() != 0}">
                        </p>
                        <br>
                        <table>
                            <tr>
                                <td>
                                    Title
                                </td>
                                <td>
                                    Price
                                </td>
                            </tr>
                            <c:forEach var="tariff" items="${tariffs}">
                                <tr>
                                    <td>
                                        <form id="formId3${tariff.id}" method="post" action="chooseTariff" enctype="application/x-www-form-urlencoded">
                                            <input type="hidden" name="contractId" value=${contract.id}>
                                            <input type="hidden" name="tariffId" value=${tariff.id}>
                                            <input type="hidden" name="action" value="chooseTariff">
                                            <input type="hidden" name="sessionRole" value=${session.role}>
                                            <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                                            <a class="inline-link" href="#" onclick="document.getElementById('formId3${tariff.id}').submit()">${tariff.title}</a>
                                        </form>
                                    </td>
                                    <td>
                                        ${tariff.price}
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

</body>
</html>
