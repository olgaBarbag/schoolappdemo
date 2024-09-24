<%--
  Created by IntelliJ IDEA.
  User: obarbagiannidou
  Date: 9/23/2024
  Time: 10:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<%@ include file="header.jsp"%>

<div class="container-fluid">
  <div class="container">
    <div class="row">
      <h1 class="text-grey">Login</h1>
    </div>

    <%--Conditional area: If this condition is true error message shows up, else it is empty area.--%>
    <div class=container>
      <%--Conditional: if validation is error or any other error in login process,
      controller will send that with request back to login page--%>
      <%--.isError is an attribute--%>
      <c:if test="${requestScope.isError eq 'true'}">
        <p style="color: red">Login Error</p>
      </c:if>
    </div>
    <%--Here is a form area. Method: POST, which means user's data passed through request to controller--%>
    <%--Action: The address where this request, including data, will be send after submit. --%>
    <%--Controller reads the credentials, that user send with POST, from "name" attribute --%>
    <form method="POST" action="${pageContext.request.contextPath}/login">
      <div class="row">
        <input type="email" name="username" required placeholder="E-mail">
        <span></span>
      </div>
      <div class="row">
        <input type="password" name="password" required placeholder="Password">
        <span></span>
      </div>
      <div class="row">
        <button type="submit">Sign In</button>
      </div>
    </form>
    <div class="row text-grey">
      <a href="#">Lost your password?</a>
    </div>
  </div>

  <%--Sign up: register endpoint: UserRegisterController--%>
  <div class="row">
    <p>Don't have an account? <a href="${pageContext.request.contextPath}/users/register">Sign up here!</a></p>
  </div>
</div>

<%@include file="footer.jsp"%>
</body>
</html>
