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
    <p>Add group</p>
    <form method="post" name="${pageContext.request.contextPath}/groups}">
        <input type="text" name="name" placeholder="Enter a name">
        <button type="submit">Create</button>
    </form>
    <p>Groups: </p>
    <div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Group name</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${groups}" var="group" varStatus="status">
                    <tr>
                        <td>${group.getId()}</td>
                        <td>${group.getName()}</td>
                        <td><a href="/group?id=${group.getId()}">More</a></td>
                    </tr>
                </c:forEach>
            </tbody>
            </table>
        </div>
</body>
</html>
