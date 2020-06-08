<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add meal</title>
</head>
<body>

<form method="POST" action="meals" name="formAddMeal">
    <label for="dateMeal">Date/Time :</label>
    <input type="datetime-local" id="dateMeal" name="dateMeal" required /> <br/>
    <label for="description">Description:</label>
    <input type="text" id="description" name="description" required /> <br/>
    <label for="calories">Calories :</label>
    <input type="number" id="calories" name="calories" required /> <br/><br/>
    <input type="submit" value="Submit"/>
</form>

</body>
</html>
