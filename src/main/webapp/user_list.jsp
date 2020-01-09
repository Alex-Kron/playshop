<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.playshop.entity.*" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Users - Playshop</title>
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
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Username</th>
            <th>Password</th>
            <th>Role</th>
        </tr>

        <jsp:useBean id="users" scope="request" type="java.util.List"/>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" type="com.playshop.entity.Person"/>
            <tr>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td><a href="users?name=${user.username}&action=delete">Delete</a></td>
                <td><a href="users?name=${user.username}&action=change">Change role</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>