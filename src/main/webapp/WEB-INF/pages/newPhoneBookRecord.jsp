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

<h2>New phone book record</h2>
<form:form method="POST" commandName="phoneBookRecord">
    <table>
        <tr>
            <td><form:label path="userid">userid</form:label></td>
            <td><form:input path="userid" /></td>
        </tr>
        <tr>
            <td><form:label path="surname">surname</form:label></td>
            <td><form:input path="surname" /></td>
            <td><span class="error"><form:errors path="surname" /></span></td>
        </tr>
        <tr>
            <td><form:label path="name">name</form:label></td>
            <td><form:input path="name" /></td>
            <td><span class="error"><form:errors path="name" /></span></td>
        </tr>
        <tr>
            <td><form:label path="patronymic">patronymic</form:label></td>
            <td><form:input path="patronymic" /></td>
            <td><span class="error"><form:errors path="patronymic" /></span></td>
        </tr>
        <tr>
             <td><form:label path="mobilePhone">mobilePhone</form:label></td>
             <td><form:input path="mobilePhone" /></td>
             <td><span class="error"><form:errors path="mobilePhone" /></span></td>
        </tr>
        <tr>
             <td><form:label path="homePhone">homePhone</form:label></td>
             <td><form:input path="homePhone" /></td>
        </tr>
        <tr>
              <td><form:label path="adress">adress</form:label></td>
              <td><form:input path="adress" /></td>
         </tr>
         <tr>
               <td><form:label path="eMail">eMail</form:label></td>
               <td><form:input path="eMail" /></td>
               <td><span class="error"><form:errors path="eMail" /></span></td>
          </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>