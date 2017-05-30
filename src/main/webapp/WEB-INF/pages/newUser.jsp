<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
    <style type="text/css">
    span.error {
    	color: red;
    }
    </style>
</head>
<body>

<h2>New User</h2>
<form:form method="POST" commandName="user">
    <table>
        <tr>
            <td><form:label path="FIO">Enter FIO</form:label></td>
            <td><form:input path="FIO" /></td>
            <td><span class="error"><form:errors path="FIO" /></span></td>
        </tr>
        <tr>
            <td><form:label path="userLogin">Enter user login</form:label></td>
            <td><form:input path="userLogin" /></td>
            <td><span class="error"><form:errors path="userLogin" /></span></td>
        </tr>
        <tr>
            <td><form:label path="userPassword">Enter user password</form:label></td>
            <td><form:input path="userPassword" /></td>
            <td><span class="error"><form:errors path="userPassword" /></span></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Add new User"/>
            </td>
        </tr>
    </table>
</form:form>
<form:form method="GET" action="/home">
           <tr>
            <td colspan="2">
                <input type="submit" value="Return to home page"/>
            </td>
        </tr>
</form:form>
</body>
</html>