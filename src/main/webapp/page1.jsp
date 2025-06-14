<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.example.dev_avance_framework.beans.UserBean" %>
<%
    UserBean user = (UserBean) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String error = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Accueil</title>
</head>
<body>
<h1>Bienvenue, <%= user.getUsername() %>!</h1>

<%
    if (error != null && !error.isEmpty()) {
%>
<script>
    alert('<%= error.replace("'", "\\'") %>');
</script>
<%
    }
%>

<form action="CheckAttributesAction.do" method="post">
    <label>Attribut 1 :</label>
    <input type="text" name="attribut1" value="<%= user.getAttribut1() %>"/><br/>
    <label>Attribut 2 :</label>
    <input type="text" name="attribut2" value="<%= user.getAttribut2() %>"/><br/>
    <input type="submit" value="Ok"/>
</form>

<form action="LogoutAction.do" method="post">
    <input type="submit" value="Déconnexion"/>
</form>
</body>
</html>
