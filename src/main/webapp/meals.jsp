<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
<style>
.green { color: green }
.red { color: red }
 td {text-align:center}
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
                        <tr class="${meal.isExcess() ? 'red' : 'green'}">
                            <td><javatime:format pattern="dd-MM-yyyy HH:mm" value="${meal.getDateTime()}" /></td>
                            <td><c:out value="${meal.getDescription()}" /></td>
                            <td><c:out value="${meal.getCalories()}" /></td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>

</body>
</html>