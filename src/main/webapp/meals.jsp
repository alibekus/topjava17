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
    <table border="1" cellpadding="2" cellspacing="0">
        <tr>
            <th>#</th>
            <th>Description</th>
            <th>Date</th>
            <th>Time</th>
            <th>Calories</th>
            <th>Excess</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="excessMeal" items="${excessMeals}">
            <jsp:useBean id="excessMeal" type="ru.javawebinar.topjava.model.MealTo"/>
            ${excessMeal.excess ? "<tr class=\"excess\">" : "<tr class='normal'>"}
                <td>${excessMeal.id}</td>
                <td>${excessMeal.description}</td>
                <td>${excessMeal.dateTime.toLocalDate()}</td>
                <td>${excessMeal.dateTime.toLocalTime()}</td>
                <td>${excessMeal.calories}</td>
                <td>${excessMeal.excess ? "Yes" : "No"}</td>
                <td><a href="mealServlet?action=edit&mealId=<c:out value="${excessMeal.id}"/>"><img src="img/pencil.png" alt="edit"></a></td>
                <td><a href="mealServlet?action=delete&mealId=<c:out value="${excessMeal.id}"/>"><img src="img/delete.png" alt="delete"></a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
