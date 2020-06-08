<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit meal</title>
</head>
<body>

<form method="POST" action="meals" name="formEditMeal">
    <label for="dateMeal">Date/Time :</label>
    <input type="datetime-local" id="dateMeal" name="dateMeal"
    value="<c:out value="${meal.dateTime}" />" /> <br/>
    <label for="description">Description:</label>
    <input type="text" id="description" name="description"
    value="<c:out value="${meal.description}" />" /> <br/>
    <label for="calories">Calories :</label>
    <input type="number" id="calories" name="calories"
    value="<c:out value="${meal.calories}" />" /> <br/><br/>
    <input type="submit" value="Submit"/>
</form>

</body>
</html>