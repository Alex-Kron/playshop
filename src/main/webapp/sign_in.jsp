<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign in page</title>
    </head>

    <body>
        <form method="post" action="signin">
        <table>
            <tr>
                <td colspan="2">Sign in to Playshop application:</td>
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
        </form>
        <%
            if (request.getAttribute("exception") != null) {
            out.println("<p style=\"color: crimson\">" + request.getAttribute("exception") + "</p>");
            }
        %>
    </body>
</html>