<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.playshop.entity.Item" %>
<%@ page import="com.playshop.entity.Person" %>
<%@ page import="com.playshop.dao.ItemDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.text.*" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%Item item = (Item)request.getAttribute("item");%>
    <title><%=out.print(item.getName())%> - Playshop</title>
</head>
<body>
<%Person person = (Person)session.getAttribute("person");%>
<%String name = person.getUsername();%>
<p>
    Welcome, <%=out.print(name)%><a href="logout">(logout)</a>
</p>
<section>
    <%String role = person.getRole();%>
    <h1><%=out.print(role)%>;</h1>
    <%if (role.equals("admin")) {%>
        <a href="items?id=
        <%try {
            out.print(new ItemDAO().getId(item));
        } catch (SQLException e) {
            out.print(-1);
        }%>&action=edit">Edit</a>
        <br/>
    <%}%>
    <% if (role.equals("user")) {%>
        <a href="items?id=
        <% try {
                out.print(new ItemDAO().getId(item));
            } catch (SQLException e) {
                out.print(-1);
            }%>&action=buy">Buy</a>
        <br/>
    <%}%>
    <hr/>
    <table cellpadding="2">
        <tr>
            <td><strong>Description</strong></td>
            <td><%=out.print(item.getDescription())%></td>
        </tr>
        <tr>
            <td><strong>Price</strong></td>
            <td><%=out.print(NumberFormat.getCurrencyInstance().format(item.getCost()))%>
            </td>
        </tr>
        <% if (role.equals("admin")) {%>
            <tr>
                <td><strong>Amount</strong></td>
                <td><%=item.getQuantity()%></td>
            </tr>
        <%}%>
    </table>
    <hr/>
    <button onclick="window.history.back()">Back</button>
</section>
</body>
</html>
