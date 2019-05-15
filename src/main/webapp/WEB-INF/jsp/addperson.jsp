<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11.05.2019
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addPerson</title>
</head>
<body>
<h3> Create Person</h3>
<br>
<form action="http://localhost:8090/JPAsample_war_exploded/add/person" method="post">
    <h5>Fam</h5><input title="fam" type="text" name="fam" value="Balagutdinov"><br>
    <h5>Nam</h5><input title="name" type="text" name="name" value="Ilgiz"><br>
    <h5>Ot</h5><input title="ot" type="text" name="ot" value="Faritovich"><br>
    <h5>DR</h5><input title="dr" type="date" name="dr" value="25.12.1986"><br>
    <input type="submit" name="OK" value="OK">
</form>
</body>
</html>
