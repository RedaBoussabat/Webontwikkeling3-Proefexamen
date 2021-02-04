<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<header>
	<h1>Mijn Veggie Website</h1>
	<nav>
		<ul>
			<li><a href="Controller?action=Home">Home</a></li>
			<c:if test="${user.role == 'ADMIN' || user.role == 'CUSTOMER'}">
				<li><a href="Controller?action=Overview">Products</a></li>
			</c:if>
			<li><a href="Controller?action=SignIn">Log In</a></li>
			<li><a href="javascript.html">Javascript</a></li>
			<li><a href="Controller?action=Veggie">Vegetarian Products</a></li>
			<li><a href="Controller?action=User">Users</a></li>
		</ul>
	</nav>
</header>