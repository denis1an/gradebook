<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<p>Id: ${group.getId()}</p>
<p>Name: ${group.getName()}</p>

<button>
    <a href="#">Add new student</a>
</button>

<p>Students:</p>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Lastname</th>
        <th>Firstname</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${group.getStudents()}" var="student" >
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
