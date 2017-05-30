<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Phone Book</title>
</head>
<body>

<h2>New user added</h2>
<form:form method="GET" action="/login">
<table>
    <tr>
        <td>FIO</td>
        <td>${FIO}</td>
    </tr>
    <tr>
        <td>User login</td>
        <td>${userlogin}</td>
    </tr>
    <tr>
        <td colspan="2">
        <input type="submit" value="Return to login page"/>
       </td>
    </tr>
</table>
</form:form>
</body>
</html>