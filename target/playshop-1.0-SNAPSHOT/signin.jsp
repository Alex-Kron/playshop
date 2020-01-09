<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Sign in - Playshop</title>
</head>
<body>
<form method="POST" action="">
    <table>
        <tr>
            <td colspan="2">Sign in to the Playshop application:</td>
        </tr>
        <tr>
            <td>Login:</td>
            <td><label>
                <input required type="text" name="login"/>
            </label></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><label>
                <input required type="password" name="password"/>
            </label></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Sign in"/>&nbsp;<a href="signup">Sign up</a></td>
        </tr>
    </table>
    <%--@elvariable id="exception" type="exception"--%>
    <c:if test="${not empty exception}">

        <p style="color:red;">
                ${exception}
        </p>
    </c:if>
    <hr/>
</form>
</body>
</html>
