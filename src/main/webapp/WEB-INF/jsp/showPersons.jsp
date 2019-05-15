<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 14.05.2019
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ShowPersons</title>
</head>
<body>
<h1>Люди:</h1>
<table><tbody>
<c:forEach var="x" items="${persons}">
<tr>
    <td>${x.id}</td>
    <td>${x.fam}</td>
    <td>${x.name}</td>
    <td>${x.ot}</td>
    <td>${x.dr}</td>
    <td>${x.carIds}</td>
    <td>${x.carNames}</td>
</tr>
</c:forEach>
    <tbody></table>
</body>
</html>
