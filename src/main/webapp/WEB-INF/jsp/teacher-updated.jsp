<%--
  Created by IntelliJ IDEA.
  User: obarbagiannidou
  Date: 10/3/2024
  Time: 7:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Επιτυχής Εισαγωγή</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/success.css">
</head>
<body>

<div class="success m-bottom">
  <h1>Επιτυχής Ενημέρωση</h1>
  <p>Κωδικός: ${requestScope.teacherInfo.id}</p>
  <p>Όνομα: ${requestScope.teacherInfo.firstname}</p>
  <p>Επώνυμο: ${requestScope.teacherInfo.lastname}</p>
</div>

<div>
  <a href="${pageContext.request.contextPath}/teachers">Επιστροφή</a>
</div>
</body>
</html>
