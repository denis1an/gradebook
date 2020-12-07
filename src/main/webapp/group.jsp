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
<h3>
    Group
    <p>Name: ${group.getName()}</p>
</h3>
<button>
    <a href="${pageContext.request.contextPath}/home">Back</a>
</button>
<p>Add student:</p>
<form method="post" action="${pageContext.request.contextPath}/group">
    <input type="text" name="firstname" placeholder="Enter a firstname"></label>
    <input type="text" name="lastname" placeholder="Enter a lastname"></p>
    <input type="hidden" name="groupId" value="${group.getId()}">
    <input type="submit" name="submit" value="Add">
</form>

<p>Students:</p>

<button>Create new task</button>
<c:if test="${!students.isEmpty()}">
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Lastname</th>
        <th>Firstname</th>
        <c:forEach items="${students.get(0).getTasks()}" var="task" >
            <td>${task.getName()}</td>
        </c:forEach>
        <td>
            New mark
        </td>
    </tr>
    </thead>
    <tbody>

        <c:forEach items="${students}" var="student" >
            <tr>
                <td>${student.getId()}</td>
                <td>${student.getFirstName()}</td>
                <td>${student.getLastName()}</td>
                <c:forEach items="${student.getTasks()}" var="task" >
                    <td>
                            ${task.getMark()}
                    </td>
                </c:forEach>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/group">
                        <input type="hidden" name="studentId" value="${student.getId()}">
                        <input type="hidden" name="studentId" value="${student.getId()}">
                        <input type="text" name="taskName" placeholder="Enter name">
                        <p>
                            <input type="text" name="mark" placeholder="Enter mark">
                        </p>
                        <p>
                            <input type="submit" name="submit" value="Mark">
                        </p>
                    </form>

                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/group">
                        <input type="hidden" name="studentId" value="${student.getId()}">
                        <input type="hidden" name="groupId" value="${group.getId()}">
                        <input type="submit" name="submit" value="Delete">
                    </form>
                </td>
                <td><a href="#">Edit</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</c:if>
</body>
</html>
