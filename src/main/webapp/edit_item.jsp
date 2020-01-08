<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="com.playshop.entity.Item" %>
<%@ page import="com.playshop.entity.Person" %>
<%@ page import="java.util.Currency" %>
<%@ page import="java.util.Locale" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%Item item = (Item)request.getAttribute("item");%>
    <style>
        .currencyinput {
            border: 1px inset #ccc;
        }
        .currencyinput input {
            border: 0;
        }
    </style>
    <title><%out.print(item.getName());%> - Playshop</title>
</head>
<body>
<%Person person = (Person)session.getAttribute("person");%>
<%String name = person.getUsername();%>
<p>
    Welcome, <%out.print(name);%><a href="logout">(logout)</a>
</p>
<section>
    <%String role = person.getRole();%>
        <%if (role.equals("admin")) {%>
        <form method="post" action="items" enctype="application/x-www-form-urlencoded">
            <table cellpadding="2">
                <tr>
                    <td><strong>Title</strong><br/> (max 150 symbols)</td>
                    <td><label>
                        <input required type="text" name="title" size=30 maxlength="150" value="<%out.print(item.getName());%>">
                    </label></td>
                </tr>
                <tr>
                    <td><strong>Description</strong><br/> (max 1000 symbols)</td>
                    <td><label>
                        <textarea name="description" cols="30" rows="10" maxlength="1000"><%out.print(item.getDescription());%></textarea>
                    </label>
                    </td>
                </tr>
                <tr>
                    <td><strong>Price</strong></td>
                    <td><span class="currencyinput"><%=Currency.getInstance(Locale.ENGLISH)%><label>
<input required type="number" name="price" size=30 value="<%out.print(item.getCost());%>" step="0.01" min="0">
</label></span></td>
                </tr>
                <tr>
                    <td><strong>Amount</strong></td>
                    <td><label>
                        <input required type="number" name="amount" size=30 value="<%out.print(item.getQuantity());%>" min="0">
                    </label></td>
                </tr>
            </table>
            <hr/>
            <button type="submit">Save</button>
            <button type="button" onclick="window.history.back()">Cancel</button>
        </form>
    <%
        if (request.getAttribute("exception") != null) {
            out.println("<p style=\"color: crimson\">" + request.getAttribute("exception") + "</p>");
        }
    %>
    <%}%>
</section>
</body>
</html>
