<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign in page</title>
    </head>

    <body>
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
                <td><input type="submit" value="Sign in"/>&nbsp;<a href="sign_up.jsp">Sign up</a></td>
            </tr>
        </table>
        <%
            String ex = request.getParameter("exception");
            if (ex != null) { %>
            <p style="color: crimson"><% out.print(ex); %></p>
            <% } %>
    </body>
</html>