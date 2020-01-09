<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.playshop.dao.PersonDAO" %>
<%@ page import="com.playshop.dao.ItemDAO" %>
<%@ page import="com.playshop.entity.*" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Purchases - Playshop</title>
</head>
<body>
<jsp:useBean id="person" scope="session" type="com.playshop.entity.Person"/>
<c:set var="name" scope="request" value="${person.username}"/>
<p>
    Welcome, ${name}&nbsp;<a href="logout">(logout)</a>
</p>

<section>
    <c:set var="role" scope="request" value="${person.role}"/>
    <c:if test="${role == 'admin'}">
        [<a href="items">Items</a>]&nbsp;[Purchases]<br/>
        <hr>
        <table border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>Buyer</th>
                <th>Item</th>
                <th>Cost</th>
                <th>Amount</th>
            </tr>
            <jsp:useBean id="purchases" scope="request" type="java.util.List"/>
            <c:forEach items="${purchases}" var="purchase">
                <jsp:useBean id="purchase" type="com.playshop.entity.Purchase"/>
                <tr>
                    <%String username = new PersonDAO().get(purchase.getUserID()).getUsername();%>
                    <%String itemname = new ItemDAO().get(purchase.getItemID()).getName();%>
                    <%float cost = new ItemDAO().get(purchase.getItemID()).getCost();%>
                    <td><%out.print(username);%></td>
                    <td><a href="items?id=${purchase.itemID}&action=view"><%out.print(itemname);%></a></td>
                    <%--<td>${purchase.dateTime}</td>--%>
                    <%--<td>${purchase.cost}</td>--%>
                    <td><%=NumberFormat.getCurrencyInstance().format(purchase.getQuantity() * cost)%></td>
                    <td>${purchase.quantity}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</section>
</body>
</html>
