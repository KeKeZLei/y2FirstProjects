<%--
  Created by IntelliJ IDEA.
  User: q7240
  Date: 2001/1/1
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <select name="st">
        <option>--请选择--</option>
        <c:forEach items="${list}" var="list">
        <option>${list}</option>
        </c:forEach>
    </select>
    <select name="st1">
        <option>--请选择--</option>
        <c:forEach items="${list}" var="list">
            <optionsssss>${list}</optionsssss>
        </c:forEach>
    </select>
</div>

</body>
</html>
