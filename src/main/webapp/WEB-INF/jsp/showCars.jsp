<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 21.05.2019
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Cars</title>
</head>
<body>
<h1>Авто:</h1>
<table><tbody>
<c:forEach var="x" items="${cars}">
<tr>
    <td>${x.id}</td>
    <td>${x.name}</td>
    <td>${x.model}</td>
    <td>${x.korobka}</td>
    <td>${x.year}</td>
</tr>
</c:forEach>
    <tbody></table>
</body>
</html>
