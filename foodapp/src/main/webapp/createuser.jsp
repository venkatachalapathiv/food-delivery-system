<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form action="saveuser"  modelAttribute="user">
enter name:<form:input path="name"/><br>
enter email:<form:input path="email"/><br>
enter password:<form:input path="password"/><br>
enter mobile:<form:input path="mobile"/><br>

<input type="submit">
</form:form>

</body>
</html>