<%--
  Created by IntelliJ IDEA.
  User: obarbagiannidou
  Date: 10/3/2024
  Time: 8:45 PM
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
    <h1>Επιτυχής Διαγραφή</h1>
    <p>Κωδικός: ${requestScope.id}</p>
</div>

<div>
    <a href="${pageContext.request.contextPath}/teachers">Επιστροφή</a>
</div>
</body>
</html>
