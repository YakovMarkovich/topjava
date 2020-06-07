<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <style>
        .green {
            color: green
        }

        .red {
            color: red
        }

        td {
            text-align: center
        }
    </style>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border=1>
    <thead>
    <tr>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meal">
        <tr class="${meal.excess ? 'red' : 'green'}">
            <td><javatime:format pattern="yyyy-MM-dd HH:mm" value="${meal.dateTime}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>

        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>