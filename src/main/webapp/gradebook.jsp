<%--
  Created by IntelliJ IDEA.
  User: denisandreev
  Date: 13.11.2020
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Gradebook</title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>GROUP NAME</th>
    </tr>
    <c:forEach items="${groups}" var="group" varStatus="status">
        <tr>
            <td>${group.getId()}</td>
            <td>${group.getName()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
