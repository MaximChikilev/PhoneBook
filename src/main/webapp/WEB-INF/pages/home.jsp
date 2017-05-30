<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

    <head>

        <title>Spring Security Example - ProgrammingFree</title>

    </head>

    <body>

        <h1>Welcome to simple Phone Book</h1>

        <p>Click <a href="<spring:url value='/mainUserPage' />">here</a> to login.</p>
        <p>Click <a href="<spring:url value='/user' />">here</a> to create new User.</p>

    </body>

</html>