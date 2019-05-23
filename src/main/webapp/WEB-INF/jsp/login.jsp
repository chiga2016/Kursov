<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.05.2019
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>login</h1>

<form action="login.do" method="POST" title="Форма авторизации пользователя">

    <input type="text" placeholder="имя" name="login" value="admin" />
    <input type="password" placeholder="пароль" name="pass" value="a"/>
    <input type="submit" value="Вход"/>

</form>

</body>
</html>
