<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <title>Java Enterprise (Topjava)</title>
</head>
<body>
<h3>Проект <a href="https://github.com/JavaWebinar/topjava" target="_blank">Java Enterprise (Topjava)</a></h3>
<hr>
<form id="user-select-form" name="user-select-form" action="users" method="post">
    <input type="hidden" name="action" value="user-auth"/>
    <div id="user-auth">
        <p><strong>Authorization</strong></p>
        <section>
            <label>Choose user:</label>
            <select id="user-id" name="user-id" size="1">
                <c:set var="userIds" value="<%=new int[]{1,2}%>"/>
                <%--<c:set var="users" value="<%=UsersUtil.USERS%>"/>--%>
                <c:forEach var="userId" items="${userIds}">
                    <%--<c:forEach var="user" items="${users}">--%>
                    <%--<jsp:useBean id="user" type="ru.javawebinar.topjava.model.User"/>--%>
                    <%--<option id="user-id" name="user-id" value="${user.id}" selected="${user.id == 1}">${user.name}</option>--%>
                    <%--<option value="${user.id}">${user.name}</option>--%>
                    <option value="${userId}">${userId}</option>
                </c:forEach>
            </select>
            <%--<label for="password" value="Введите пароль"/>
            <input type="password" id="password" name="password"/>--%>
        </section>
        <br>
        <button type="submit">Login</button>
        <button><a href="users?action=create">Register New User</a></button>
    </div>
</form>
<div id="categories" <%--hidden="<%=request.getAttribute("categoriesVisibility")%>"--%>>
    <ul>
        <li><a href="users">Users</a></li>
        <li><a href="meals">Meals</a></li>
    </ul>
</div>
</body>
