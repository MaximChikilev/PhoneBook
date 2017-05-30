<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style>
#header{
   background-color:lightgray;
   color:white;
   text-align:center;
   padding:5px;
}
#nav{
       line-height:30px;
       background-color:lightgray;
       height:100%;
       width:auto;
       float:left;
       padding:5px;
}
#section {
    width:350px;
    float:left;
    padding:10px;
}
</style>
</head>

<body>

<div id="header">
    <h3>Personal Phone Book</h3>
    <h5 style="color:red;">${userlogin}</h5>
</div>

<div id="nav">
<form:form method="GET" action="/userPage">
        <table>
             <tr>
                <td colspan="2">
                    <input type="submit" value="Show All record"/>
                </td>
            </tr>

        </table>
     </form:form>
     <form:form method="GET" action="/userPage/phoneBookRecords/">
             <table>
                  <tr>
                     <td colspan="2">
                         <input type="submit" value="Find book record"/>
                     </td>
                 </tr>

             </table>
          </form:form>

    <form:form method="GET" action="/userPage/phoneBookRecords">
        <table>
             <tr>
                <td colspan="2">
                    <input type="submit" value="Add new record"/>
                </td>
            </tr>

        </table>
     </form:form>
     <form:form method="GET" action="/userPage/phoneBookRecords/edit">
                      <table>
                           <tr>
                             <td colspan="2">
                                  <input type="submit" value="Edit book record"/>
                              </td>
                          </tr>
                      </table>
     </form:form>
     <form:form method="POST" action="/logout">
                           <table>
                                <tr>
                                  <td colspan="2">
                                       <input type="submit" value="Sign out"/>
                                   </td>
                               </tr>
                           </table>
          </form:form>

</div>
<div id="section">
<table>
    <tr>
        <th>Record ID</th>
        <th>Surname</th>
        <th>Name</th>
        <th>Patronymic</th>
        <th>Mobile Phone</th>
        <th>Home Phone</th>
        <th>Adress</th>
        <th>eMail</th>
    </tr>
    <c:forEach items="${phoneBookRecordList}" var="theme" varStatus="status">
        <tr>
            <td>${theme.id}</td>
            <td>${theme.surname}</td>
            <td>${theme.name}</td>
            <td>${theme.patronymic}</td>
            <td>${theme.mobilePhone}</td>
            <td>${theme.homePhone}</td>
            <td>${theme.adress}</td>
            <td>${theme.eMail}</td>
            <c:url var="updateURL" value="/userPage/phoneBookRecords/edit">
               <c:param name="recordID" value="${theme.id}"/>
            </c:url>
            <td><a href="${updateURL}">Update</a></td>
            <c:url var="deleteURL" value="/userPage/phoneBookRecords/delete">
               <c:param name="recordID" value="${theme.id}"/>
            </c:url>
            <td><a href="${deleteURL}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>