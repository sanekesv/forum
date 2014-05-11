<%@ page import="serv.mesage.StructMessage" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 01.04.14
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title></title>
    <link href="css/bootstrap.css" type="text/css" rel="stylesheet">
    <link href="css/style.css" type="text/css" rel="stylesheet">

</head>
<body>
<%ArrayList<StructMessage> list = (ArrayList<StructMessage>) request.getAttribute("list");%>
<%int flag=1;%>

<div class="center">
    <%--<div class="">--%>
    <table class="table-responsive table-striped table-bordered table_test" border="1">
        <caption>Posts on the forum</caption>
        <tr>
            <th>Username<p>date</p></th>
            <th>Message</th>
        </tr>
        <%for (StructMessage i : list) {%>
        <tr>
            <td><%=i.username%><p><%=i.date%>
            </p></td>
            <td><%=i.message%>
            </td>
            <%flag++;%>
        </tr>
        <%}%>

    </table>
    <%--
    </div>
    --%>


        <form action="/add" method="post">

        <input type="text" name="username" placeholder="Enter your name, please">

        <p><textarea name="post" cols="45" rows="3" placeholder="Enter yout message here" required></textarea></p>

        <p><input type="submit" value="Add a post"></p>
    </form>
</div>


</body>
</html>
