<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>



<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
    <title>Step 2. Choose options</title>
</head>

<body>

<div class="outer-wrapper clearfix">

    <%--<form id="formId1" method="post" action="login" enctype="application/x-www-form-urlencoded">
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
        <div class="h3-logo"></div>
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
    </c:if>--%>

    <jsp:include page="header.jsp"></jsp:include>

    <div class="inner-wrapper">

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
        <p>
            Chosen tariff title: ${tariff.title}
        </p>
        <p>
            Chosen tariff price: ${tariff.price}
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

    <c:if test="${options.size() != 0}">

        <div class="inner-wrapper-info">

            <p>
                Dependencies of options:
            </p>
            <br>

            <c:forEach var="currentOption1" items="${options}">

                <c:if test="${currentOption1.getDependentOptions().size() != 0}">

                    <p>
                        Option ${currentOption1.title} will be enabled with options:

                        <c:forEach var="depOption" items="${currentOption1.getDependentOptions()}">

                            ${depOption.title};
                            &nbsp;

                        </c:forEach>

                    </p>

                </c:if>

            </c:forEach>

        </div>

        <div class="inner-wrapper-info">

            <p>
                Incompatibilities of options:
            </p>
            <br>

            <c:forEach var="currentOption2" items="${options}">

                <c:if test="${currentOption2.getIncompatibleOptions().size() != 0}">

                    <p>
                        Option ${currentOption2.title} is incompatible with options:

                        <c:forEach var="incOption" items="${currentOption2.getIncompatibleOptions()}">

                            ${incOption.title};&nbsp

                        </c:forEach>

                    </p>

                </c:if>

            </c:forEach>

        </div>

    </c:if>

    <jsp:include page="footer.jsp"></jsp:include>

</div>

</body>
</html>
