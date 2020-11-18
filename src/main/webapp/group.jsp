<%--
  Created by IntelliJ IDEA.
  User: denisandreev
  Date: 18.11.2020
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button>
    <a href="#">Add new student</a>
</button>

<table>
    <tr>
        <th>ID</th>
        <th>LASTNAME</th>
        <th>FIRSTNAME</th>
    </tr>
    <tbody>
    <c:forEach items="${groups.getStudents()}" var="student" varStatus="status">
        <tr>
            <td>${student.getId()}</td>
            <td>${student.getFirstName()}</td>
            <td>${student.getLastName()}</td>
            <td><a href="#">Edit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
