<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Submitted Phone Record</h2>
<table>
    <tr>
        <td>Userid</td>
        <td>${userid}</td>
    </tr>
    <tr>
        <td>Surname</td>
        <td>${surname}</td>
    </tr>
    <tr>
        <td>Name</td>
        <td>${name}</td>
    </tr>
    <tr>
        <td>Patronymic</td>
        <td>${patronymic}</td>
    </tr>
    <tr>
        <td>MobilePhone</td>
        <td>${mobilePhone}</td>
    </tr>
    <tr>
      <td>homePhone</td>
      <td>${homePhone}</td>
    </tr>
    <tr>
      <td>adress</td>
      <td>${adress}</td>
    </tr>
     <tr>
          <td>email</td>
          <td>${email}</td>
     </tr>
</table>
</body>
</html>