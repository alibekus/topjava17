<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }
    </style>
    <script>
        function requestUrl(id1){
            // console.log(document.getElementById(id1));
            console.log(id1);
        }
    </script>
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>Meals</h2>
    <hr/>
        <form action="meals" method="get">
        <label for="date-time-filter"><strong>Фильтр еды</strong></label>
        <input type="hidden" name="action" value="filter"/>
        <div id="date-time-filter" name="date-time-filter">
            <table>
                <thead>
                <tr>
                    <th>От даты</th>
                    <th>От времени</th>
                    <th>До даты</th>
                    <th>До времени</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <input type="date" id="date-from" name="date-from"
                               value="<%=request.getAttribute("dateFrom")%>">
                    </td>
                    <td align="center" width="40">
                        <input type="time" id="time-from" name="time-from"
                               value="<%=request.getAttribute("timeFrom")%>">
                    </td>
                    <td>
                        <input type="date" id="date-to" name="date-to"
                               value="<%=request.getAttribute("dateTo")%>">
                    </td>
                    <td align="center" width="40">
                        <input type="time" id="time-to" name="time-to"
                               value="<%=request.getAttribute("timeTo")%>">
                    </td>
                </tr>
                </tbody>
            </table>
            <br>
            <button type="submit">Отфильтровать</button>
            <button id="cancel-filter-button">
                <a href="meals?action=all">Сбросить</a></button>
        </div>
    </form>
    <hr/>
    <p><button><a href="meals?action=create">Add Meal</a></button></p>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>id</th>
            <th>User id</th>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>${meal.id}</td>
                <td align="center">${meal.userId}</td>
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}"><img src="img/pencil.png" alt="edit"></a></td>
                <td><a href="meals?action=delete&id=${meal.id}"><img src="img/delete.png" alt="delete"></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>