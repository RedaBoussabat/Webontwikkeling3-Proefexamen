<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    <h2>Home</h2>

    <p>Normaal zou hier random tekst moeten komen</p>
    <form action="Controller?action=Pic" method="post" novalidate="novalidate">
        <p>Do you wanna see some BS?</p>
        <p>
            <input type="radio" id="yes" name="pic" value="<c:out value='yes'/>"
                   <c:if test="${picKey.equals('yes')}">checked</c:if>>
            <label for="yes">Yes</label>

            <input type="radio" id="no" name="pic" value="<c:out value='no'/>"
                   <c:if test="${picKey.equals('no')}">checked</c:if>>
            <label for="no">No</label>
        </p>
        <p>
            <input type="submit" id="qoute" value="Send">
        </p>
    </form>

    <c:if test="${picKey.equals('yes')}">
        <img src="${pageContext.request.contextPath}/images/Z9yr9W1.jpg" alt="picture">
    </c:if>
</main>
</body>
</html>