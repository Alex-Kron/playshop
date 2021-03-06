<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.playshop.entity.*" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Items - Playshop</title>
</head>
<body>
<jsp:useBean id="person" scope="session" type="com.playshop.entity.Person"/>
<c:set var="name" scope="request" value="${person.username}"/>
<p>
    Welcome, ${name}&nbsp;<a href="logout">(logout)</a>
</p>
<hr/>

<section>
    <c:set var="role" scope="request" value="${person.role}"/>
    <c:if test="${role == 'admin'}">
        [Items]&nbsp;[<a href="purchases">Purchases</a>] [<a href="users">User list</a>]<br/>
        <hr>
        <a href="items?action=add">Add item</a>
        <br/><br/>
    </c:if>

    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Item</th>
            <th>Price</th>
            <th>Amount</th>
            <th></th>
            <th></th>
        </tr>
        <jsp:useBean id="items" scope="request" type="java.util.List"/>
        <c:forEach items="${items}" var="item">
            <jsp:useBean id="item" type="com.playshop.entity.Item"/>
            <tr>
                <td><a href="items?id=${item.id}&action=view">${item.name}</a></td>
                <td><%=NumberFormat.getCurrencyInstance().format(item.getCost())%></td>
                <td>${item.quantity}</td>
                <c:if test="${role == 'admin'}">
                    <td><a href="items?id=${item.id}&action=delete">Delete</a></td>
                    <td><a href="items?id=${item.id}&action=edit">Edit</a></td>
                </c:if>
                <c:if test="${role == 'user'}">
                    <td><a href="items?id=${item.id}&action=buy">Buy</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</section>

</body>
</html>