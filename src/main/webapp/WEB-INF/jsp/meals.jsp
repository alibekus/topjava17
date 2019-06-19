<%--
  Created by IntelliJ IDEA.
  User: Alibek Akbarov
  Date: 09.06.2019
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="css/meal.css" rel="stylesheet">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<h3>Limit day calories: ${dayCalories}</h3>
<p><button><a href="meals?mode=create">Добавить еду</a></button></p>
<table border="1" cellpadding="2" cellspacing="0">
    <tr>
        <th>Description</th>
        <th>Date&Time</th>
        <th>Calories</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="excessMeal" items="${excessMeals}">
        <jsp:useBean id="excessMeal" type="ru.javawebinar.topjava.model.MealTo"/>
        ${excessMeal.excess ? "<tr class=\"excess\">" : "<tr class='normal'>"}
        <td align="center">${excessMeal.description}</td>
        <td align="center">${excessMeal.dateTimeFormatted}</td>
        <td align="center">${excessMeal.calories}</td>
        <td><a href="meals?mode=edit&mealId=<c:out value="${excessMeal.id}"/>"><img src="img/pencil.png"
                                                                                      alt="edit"></a></td>
        <td><a href="meals?mode=delete&mealId=<c:out value="${excessMeal.id}"/>"><img src="img/delete.png"
                                                                                        alt="delete"></a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
