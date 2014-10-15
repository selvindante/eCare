<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 14.10.2014
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Option</title>
</head>
<body>
<header>Option: <a href='<%=request.getContextPath()%>option?id=${option.id}&action=deleteOption'>(delete)</a></header>

<hr>

<p>
    Option ID: ${option.id}
</p>
<p>
    Title: ${option.title}
</p>
<p>
    Price: ${option.price}
</p>
<p>
    Cost of connection: ${option.costOfConnection}
</p>

<hr>

<p>
    List of dependent options: <a href='<%=request.getContextPath()%>option?action=removeAllDependentOptions'>(clear list)</a>
</p>

<c:choose>
    <c:when test="${option.getDependentOptions().size() != 0}">
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
            </tr>
            <c:forEach var="option" items="${option.getDependentOptions()}">
                <tr>
                    <td>
                        <a href='<%=request.getContextPath()%>option?id=${option.id}&action=viewOption'>${option.id}</a>
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
                        <a href='<%=request.getContextPath()%>option?id=${option.id}&action=removeDependentOption'>(remove)</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        This option has no dependent options yet.
    </c:otherwise>
</c:choose>

<hr>

<p>
    List of incompatible options: <a href='<%=request.getContextPath()%>option?action=removeAllIncompatibleOptions'>(clear list)</a>
</p>
<c:choose>
    <c:when test="${option.getIncompatibleOptions().size() != 0}">
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
            </tr>
            <c:forEach var="option" items="${option.getDependentOptions()}">
                <tr>
                    <td>
                        <a href='<%=request.getContextPath()%>option?id=${option.id}&action=viewOption'>${option.id}</a>
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
                        <a href='<%=request.getContextPath()%>option?id=${option.id}&action=removeIncompatibleOption'>(remove)</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        This option has no incompatible options yet.
    </c:otherwise>
</c:choose>

</body>
</html>
