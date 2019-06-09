<%--
  Created by IntelliJ IDEA.
  User: Alibek
  Date: 09.06.2019
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
    <section>
        <form method="post" action="meal" enctype="application/x-www-form-urlencoded">
            <label for="desc">Description</label>
            <input id="desc" type="text" size="40">${meal.description}
            <label for="date">Date</label>
            <input id="date" type="date">${meal.dateTime.toLocalDate}
            <label for="time">Time</label>
            <input id="time" type="time">${meal.dateTime.toLocalTime}
            <label for="cals">Calories</label>
            <input id="cals" type="number">${meal.calories}
            <button id="submit" type="submit">Save</button>
            <button id="cancel" type="cancel">Cancel</button>
        </form>
    </section>

</body>
</html>
