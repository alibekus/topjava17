<%--
  Created by IntelliJ IDEA.
  User: Alibek
  Date: 09.06.2019
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <jsp:useBean id="meal" class="ru.javawebinar.topjava.model.Meal" type="ru.javawebinar.topjava.model.Meal"
                 scope="request"/>
    <script>
        function cancelModeBack() {
            document.getElementById("mode").value = "cancel";
            window.history.back();
        }
    </script>
    <title>Meal</title>
</head>
<body>
<input type="hidden" id="mode" name="mode" value="edit"/>
<section>
    <form method="post" action="meals" enctype="application/x-www-form-urlencoded">
        <input type="hidden" id="mealId" name="mealId" value="${meal.id}"/></dd>
        <dl>
            <dt><label for="desc">Description</label></dt>
            <dd><input id="desc" name="desc" type="text" size="40" value="${meal.description}"></dd>
        </dl>
        <dl>
            <dt><label for="date">Date</label></dt>
            <dd><input id="date" name="date" type="date" value="${meal.date}"></dd>
        </dl>
        <dl>
            <dt><label for="time">Time</label></dt>
            <dd><input id="time" name="time" type="time" value="${meal.time}"></dd>
        </dl>
        <dl>
            <dt><label for="cals">Calories</label></dt>
            <dd><input id="cals" name="cals" type="number" value="${meal.calories}"></dd>
        </dl>
        <button id="submit" type="submit">Save</button>
        <button onclick="cancelModeBack()">Cancel</button>
    </form>
</section>
</body>
</html>
