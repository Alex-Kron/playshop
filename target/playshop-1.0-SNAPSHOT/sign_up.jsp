<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<html>
<head>
    <title>Sign up page</title>
</head>
<body>
<form method="post" action="signup">
    <table>
        <tr>
            <td colspan="2">Sign up to the Playshop:</td>
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
            <td>Retype password:</td>
            <td><label>
                <input required type="password" name="retype_password"/>
            </label></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Sign un"><a href="signin">Sign in</a></td>
        </tr>
    </table>
</form>
<%
    if (request.getAttribute("exception") != null) {
    out.println("<p style=\"color: crimson\">" + request.getAttribute("exception") + "</p>");
    }
%>
</body>
</html>