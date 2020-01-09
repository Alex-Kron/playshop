<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.playshop.entity.*" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:useBean id="item" scope="request" type="com.playshop.entity.Item"/>
    <title>${item.name} - Webstore</title>
</head>
<body>
<jsp:useBean id="person" scope="session" type="com.playshop.entity.Person"/>
<c:set var="name" scope="request" value="${person.username}"/>
<p>
    Welcome, ${name}&nbsp;<a href="logout">(logout)</a>
</p>
<section>
    <c:set var="role" scope="request" value="${person.role}"/>
    <h1>${item.name}</h1>
    <c:if test="${role == 'admin'}">
        <a href="items?id=${item.id}&action=edit">Edit</a>
        <br/>
    </c:if>
    <c:if test="${role == 'user'}">
        <a href="items?id=${item.id}&action=buy">Buy</a>
        <br/>
    </c:if>
    <hr/>
    <table cellpadding="2">
        <tr>
            <td><strong>Description</strong></td>
            <td>${item.description}</td>
        </tr>
        <tr>
            <td><strong>Price</strong></td>
            <td><%=NumberFormat.getCurrencyInstance().format(item.getCost())%>
            </td>
        </tr>
        <c:if test="${role == 'admin'}">
            <tr>
                <td><strong>Amount</strong></td>
                <td>${item.quantity}</td>
            </tr>
        </c:if>
    </table>
    <hr/>
    <button onclick="window.history.back()">Back</button>
</section>
</body>
</html>
