<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page errorPage="error.jsp" %>
<%@ page import="com.playshop.entity.Person" %>
<%@ page import="com.playshop.entity.Purchase" %>
<%@ page import="java.util.List" %>
<%@ page import="com.playshop.dao.PersonDAO" %>
<%@ page import="com.playshop.dao.ItemDAO" %>
<%@ page import="java.text.NumberFormat" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Purchases - Playshop</title>
</head>
<body>
<%Person person = (Person)session.getAttribute("person");%>
<%String name = person.getUsername();%>
<p>
    Welcome, <%=name%><a href="logout">(logout)</a>
</p>

<section>
    <%String role = person.getRole();%>
    <%if (role.equals("admin")) {%>
        [<a href="items">Items</a>]&nbsp;[Purchases]<br/>
        <hr>
        <table border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>Buyer</th>
                <th>Item</th>
                <th>Date</th>
                <th>Cost</th>
                <th>Amount</th>
            </tr>
            <%List<Purchase> list = (List)request.getAttribute("purchases");%>
            <%for (Purchase purchase: list) {%>
                <tr>
                    <td><%=new PersonDAO().get(purchase.getUserID()).getUsername()%></td>
                    <td><a href="items?id=<%=purchase.getItemID()%>&action=view"><%=new ItemDAO().get(purchase.getItemID()).getName()%></a></td>
                    <td><%=NumberFormat.getCurrencyInstance().format(purchase.getQuantity() * new ItemDAO().get(purchase.getItemID()).getCost())%></td>
                    <td><%=purchase.getQuantity()%></td>
                </tr>
            <%}%>
        </table>
    <%}%>
</section>
</body>
</html>
