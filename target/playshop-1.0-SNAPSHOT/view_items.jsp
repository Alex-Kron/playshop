<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page errorPage="error.jsp" %>
<%@ page import="com.playshop.entity.Person" %>
<%@ page import="com.playshop.entity.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="com.playshop.dao.ItemDAO" %>
<%@ page import="java.text.NumberFormat" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Items - Playshop</title>
</head>
<body>
<%Person person = (Person)session.getAttribute("person");%>
<%String name = person.getUsername();%>
<p>
    Welcome, <%=out.print(name)%><a href="logout">(logout)</a>
</p>
<hr/>

<section>
    <%String role = person.getRole();%>
    <% if (role.equals("admin")) {%>
        [Items]&nbsp;[<a href="purchases">Purchases</a>]<br/>
        <hr>
        <a href="items?action=add">Add item</a>
        <br/><br/>
    <%}%>

    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Item</th>
            <th>Price</th>
            <% if (role.equals("admin")) {%>
                <th>Amount</th>
                <th></th>
            <%}%>
            <th></th>
        </tr>
        <%List<Item> items = (List)request.getAttribute("items");%>
        <%for (Item item: items) {%>
            <tr>
                <% int id = new ItemDAO().getId(item);%>
                <td><a href="items?id=<%out.print(id);%>&action=view"><%=item.getName()%></a></td>
                <td><%=NumberFormat.getCurrencyInstance().format(item.getCost())%></td>
                <% if (role.equals("admin")) {%>
                    <td><%=item.getQuantity()%></td>
                    <td><a href="item?id=<%=id%>&action=delete">Delete</a></td>
                    <td><a href="item?id=<%=id%>&action=edit">Edit</a></td>
                <%}%>
                <% if (role.equals("user")) {%>
                    <td><a href="item?id=<%=id%>%>&action=buy">Buy</a></td>
                <%}%>
            </tr>
        <%}%>
    </table>
</section>
</body>
</html>