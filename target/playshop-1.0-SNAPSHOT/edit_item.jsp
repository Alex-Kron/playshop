<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.playshop.entity.*" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:useBean id="item" scope="request" type="com.playshop.entity.Item"/>
    <style>
        .currencyinput {
            border: 1px inset #ccc;
        }
        .currencyinput input {
            border: 0;
        }
    </style>
    <title>${item.name} - Playshop</title>
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
        <form method="post" action="items" enctype="application/x-www-form-urlencoded">
            <table cellpadding="2">
                <tr>
                    <td><strong>Title</strong><br/> (max 150 symbols)</td>
                    <td><label>
                        <input required type="text" name="title" size=30 maxlength="150" value="${item.name}">
                    </label></td>
                </tr>
                <tr>
                    <td><strong>Description</strong><br/> (max 1000 symbols)</td>
                    <td><label>
                        <textarea name="description" cols="30" rows="10" maxlength="1000">${item.description}</textarea>
                    </label>
                    </td>
                </tr>
                <tr>
                    <td><strong>Price</strong></td>
                    <td><span class="currencyinput"><%=NumberFormat.getCurrencyInstance().getCurrency()%><label>
<input required type="number" name="price" size=30 value="${item.cost}" step="0.01" min="0">
</label></span></td>
                </tr>
                <tr>
                    <td><strong>Amount</strong></td>
                    <td><label>
                        <input required type="number" name="amount" size=30 value="${item.quantity}" min="0">
                    </label></td>
                </tr>
            </table>
            <hr/>
            <button type="submit">Save</button>
            <button type="button" onclick="window.history.back()">Cancel</button>
        </form>
        <%--@elvariable id="exception" type="exception"--%>
        <c:if test="${not empty exception}">
            <p style="color:red;">
                    ${exception}
            </p>
        </c:if>
    </c:if>
</section>
</body>
</html>
