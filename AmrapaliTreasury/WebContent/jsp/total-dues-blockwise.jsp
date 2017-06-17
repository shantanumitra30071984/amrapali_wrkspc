<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color:#EFFAF5; ">


<form:form action="totalDuesBlockWise.html" method="POST" commandName="amrapaliBean">
<div style="width: 80%; margin: auto">
<fieldset >
<legend>Select Block And Year</legend>
<table><tr><td>
<!-- Block:<form:select path="block" id="block">
<form:option value="A">A</form:option>
<form:option value="B">B</form:option>
<form:option value="C">C</form:option>
<form:option value="D">D</form:option>
</form:select>-->
Select Year:<form:select path="year" id="year">
<form:options items="${yearMap}" />
</form:select>
</td></tr></table>
</fieldset>
</div>
<div style="width: 80%; margin: auto">
<table style="width: 50%;margin: auto">
<tr><td><input type="submit" id="show" name="show" value="Show"/></td></tr>
</table>
</div>
<div style="width: 80%; margin: auto">
<table style="width: 50%;border: 1px;color: black;border-style: solid; margin: auto" id="dueTable">
<tr><th>Year</th>
<th>Block</th>
<th>Total</th>
<c:forEach var="listValue" items="${list}" varStatus="status">
<tr><td style="text-align:center">${listValue.year}</td><td style="text-align:center">${listValue.block}</td><td style="text-align:center" >${listValue.amountToBePaid}</td>
</c:forEach>
</table>
</div>
</form:form>
</body>
</html>