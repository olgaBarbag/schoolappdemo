<%--
  Created by IntelliJ IDEA.
  User: obarbagiannidou
  Date: 9/23/2024
  Time: 11:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Εγγραφή Χρήστη</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-register.css">
</head>
<body>
<%@ include file="header.jsp"%>
<div class="main-content">

  <%--Registration form--%>
  <div class="form m-bottom">

    <%--<form method="POST" action="${pageContext.request.contextPath}/users/register">--%>
    <%--requestScope/sessionScope are the data canal from controllers to jsp pages--%>
    <%--value="${requestScope.userRegisterDTO.xxxx}" : this value is defined by the saved data in DTO. --%>
    <form method="POST" action="">

      <%--Username--%>
      <div class="row m-bottom">


        <%--Input username--%>
        <input class="m-bottom" type="email" name="username" value="${requestScope.userRegisterDTO.username}" required placeholder="Username">

        <%--Error message:
        if validation from Validator Util Class failed due to this input
        will return a HashMap key = usernameMessage and value = "message"
        It works like conditional (.getOrDefault), if passes the validation is null if not an error message will show up--%>
        <p class="validation-error">${requestScope.usernameMessage}</p>
      </div>


      <%--Password--%>
      <div class="row m-bottom">
        <%--Input username--%>
        <input class="m-bottom" type="password" name="password" value="${requestScope.userRegisterDTO.password}" required placeholder="Password">

        <%--Error message:--%>
        <p class="validation-error">${requestScope.passwordMessage}</p>
      </div>


      <%--Confirm Password--%>
      <div class="row m-bottom">
        <%--Input Confirm Password--%>
        <input class="m-bottom" type="password" name="confirmPassword" value="${requestScope.userRegisterDTO.confirmPassword}" required placeholder="Confirm Password">

        <%--Error message:--%>
        <p class="validation-error">${requestScope.confirmPasswordMessage}</p>
      </div>

      <%--Submit--%>
      <div class="row">
        <button type="submit">Εγγραφή</button>
      </div>
    </form>
  </div>

  <%--Return--%>
  <div class="m-bottom">
    <a href="${pageContext.request.contextPath}/login">Επιστροφή</a>
  </div>


  <div>
    ${requestScope.errorMessage}
  </div>
</div>

<%@ include file="footer.jsp"%>
</body>
</html>
