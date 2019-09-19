<%@ page import="ru.javawebinar.topjava.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>User</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create user' : 'Edit user'}</h2>
    <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User" scope="request"/>
    <form method="post" action="users">
        <input type="hidden" name="user-id" value="${user.id}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" name="name" value="${user.name}" size="40" required></dd>
        </dl>
        <dl>
            <dt>Email:</dt>
            <dd><input type="email" name="email" value="${user.email}" size=40 required></dd>
        </dl>
        <dl>
            <dt>Role:</dt>
            <c:set var="roles" value="<%=Role.values()%>"/>
            <c:set var="userRoles" value="<%=user.getRoles()%>"/>
            <c:set var="rolesSize" value="<%=Role.values().length%>"/>
            <dd><select name="roles" size="${rolesSize}" type="text" multiple required>
                <c:forEach items="${roles}" var="role">
                    <c:set var="selected" value=""/>
                    <c:forEach items="${userRoles}" var="userRole">
                        <c:if test="${userRole.name().equals(role.name())}">
                            ${selected="selected"}
                        </c:if>
                    </c:forEach>
                    <option name="role" value="${role.name()}" ${selected}>${role.title}</option>
                </c:forEach>
            </select>
            </dd>
        </dl>
        <dl>
            <dt>Calories per day</dt>
            <dd><input type="number" name="calories-per-day" value="${user.caloriesPerDay}" size="20" required></dd>
        </dl>
        <dl>
            <dt>Password:</dt>
            <dd><input type="password" name="password" value="${user.password}" size="20" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
