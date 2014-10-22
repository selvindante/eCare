<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Step 2. Choose options</title>
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
        <input type="hidden" name="action" value="changeTariff">
        <input type="hidden" name="sessionRole" value=${session.role}>
        <input type="hidden" name="sessionStatus" value=${session.isOn()}>
    </form>

    <h3>
        LOGO
        Available options:
        <a href="#" onclick="document.getElementById('formId1').submit()" class="h3-link">Exit</a>
        <a href="#" onclick="document.getElementById('formId2').submit()" class="h3-link">Back to tariffs</a>
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
            Contract ID: ${contract.id}
        </p>
        <p>
            Contract number: ${contract.number}
        </p>
        <p>
            Chosen tariff id: ${tariff.id}
        </p>
        <p>
            Chosen tariff title: ${tariff.title}
        </p>
    </div>

    <div class="inner-wrapper">
        <p>
            Step 2. Choose options for tariff.
        </p>
        <br>
        <p>
            List of available options:
            <c:choose>
            <c:when test="${options.size() != 0}">
        </p>
        <br>
            <form method="post" action="contract" enctype="application/x-www-form-urlencoded">
                <input type="hidden" name="id" value=${contract.id}>
                <input type="hidden" name="action" value="setNewTariff">
                <input type="hidden" name="tariffId" value=${tariff.id}>
                <input type="hidden" name="sessionRole" value=${session.role}>
                <input type="hidden" name="sessionStatus" value=${session.isOn()}>
                <table>
                    <tr>
                        <td>
                            Option title
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
                    <c:forEach var="dependentOption" items="${options}">

                        <%--<script language="javascript">
                            function autocheck(boxId) {
                                if (document.getElementById(boxId).checked) {
                                    for(Option o: ${Option.getDependentOptions()}) {

                                    }
                                } else {

                                }
                            }
                        </script>--%>

                        <tr>
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
                                <input type="checkbox" id="box${dependentOption.id}" name="options" value="${dependentOption.id}">
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <button type="submit" class="modern">Save</button>
            </form>
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
