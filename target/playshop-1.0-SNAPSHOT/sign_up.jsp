<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign in page</title>
    </head>

    <body>
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
                <td><input type="submit" value="Sign up"/>&nbsp;<a href="sign_in.jsp">Sign in</a></td>
            </tr>
        </table>
        <%String ex = request.getParameter("exception");
        if (ex != null) { %>
            <p style="color: crimson">
                <% out.print(ex); %>
            </p>
        <%}%>
    </body>
</html>