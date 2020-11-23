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
        <button>
            <a type="button" href="#">Add new group</a>
        </button>
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
    </table>


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
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.getId()}</td>
                <td>${student.getLastName()}</td>
                <td>${student.getFirstName()}</td>
                <td>${student.getGroup()}</td>
                <td><a href="/student?id=${student.getId()}">Edit</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
