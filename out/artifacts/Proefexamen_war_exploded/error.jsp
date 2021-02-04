<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/sample.css"/>
    <title>Veggie</title>
</head>
<body>
<jsp:include page="header.jsp"/>


<main>

    <article>
        <h1>Error : ERROR</h1>
        <p>Er is iets misgelopen, geen zorgen. <br>Keer maar terug naar de hoofdpagina.</p>
        <p>${pageContext.exception}</p>
    </article>
</main>
</body>
</html>