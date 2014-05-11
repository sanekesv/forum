<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setCharacterEncoding("UTF-8");%>

<%--<%response.sendRedirect("index");%>--%>
<html>
<head>
    <title>Autorization</title>
    <%--<link href="css/bootstrap.css" type="text/css" rel="stylesheet">--%>
    <link href="css/login.css" type="text/css" rel="stylesheet">

</head>
<body>
<div class="block">
    <form method="post" action="/login" class="slick-login">
        <input type="text" name="username" class="placeholder"
               placeholder="admin@example.com">
        <input type="password" name="password" class="placeholder"
               placeholder="Сложный пароль...">
        <input type="submit" value="Войти">
    </form>


    <form method="post" action="/registr" class="slick-login">
        <input type="text" name="username" placeholder="admin@example.com">
        <input type="password" name="password" placeholder="Пароль">
        <input type="password" name="repassword" placeholder="Повтор пароля">
        <input type="submit" value="Зарегистрироваться">
    </form>
</div>
</body>
</html>