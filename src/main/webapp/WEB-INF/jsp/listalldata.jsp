<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Содержимое базы</title>
        <link href="<c:url value='/main.css'/>" rel="stylesheet" type="text/css">
    </head>
    <body>
        <ul>
            <li><a href="init.do"/>Заполнить заново</a></li>
            <li><a href="addcat.do"/>Добавить кошку</a></li>
            <li><a href="list.do"/>Просто посмотреть</a></li>
            <li>
                <form action="showCat.do">  <input type="submit" value="Показать кошку по ID: "/> <input name="id"/> </form>
            </li>
            <li>
                <form action="deleteCat.do">  <input type="submit" value="Удалить кошку по ID: "/> <input name="id"/> </form>
            </li>
            <li>
                <form action="deletePerson.do">  <input type="submit" value="Удалить человека по ID: "/> <input name="id"/> </form>
            </li>
            <li>
                <form action="changeOwner.do">  Отдать кошку c ID= <input name="cid"/>  человеку c ID=<input name="pid"/><input type="submit" value="OK"> </form>
            </li>
            
                    
        </ul>
        
        <h1>Кошки:</h1>
        <table><tbody>
        <c:forEach var="x" items="${cats}">
            <tr>
                <td>${x.id}</td>
                <td>${x.name}</td>
                <td>${x.weight}</td>
                <td>${x.owner.id}</td>
                <td>${x.owner.name}</td>
            </tr>
        </c:forEach>
        <tbody></table>
        
        <h1>Люди:</h1>
        <table><tbody>
        <c:forEach var="x" items="${persons}">
            <tr>
                <td>${x.id}</td>
                <td>${x.name}</td>
                <td>${x.catIds}</td>
                <td>${x.catNames}</td>
            </tr>
        </c:forEach>
        <tbody></table>
        
        <div class="status">${status}</div>
    </body>
</html>
