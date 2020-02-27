<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Resume database</title>
</head>
<body>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>UUID</th>
        <th>Full Name</th>
    </tr>
    </thead>
    <c:forEach items="${resumeList}" var="resume">
        <tr>
            <td><c:out value="${resume.uuid}"/></td>
            <td><c:out value="${resume.fullName}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
