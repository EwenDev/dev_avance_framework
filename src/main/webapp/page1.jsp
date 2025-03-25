<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.example.dev_avance_framework.beans.UserBean" %>
<%
    UserBean user = (UserBean) session.getAttribute("user");
    if(user == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Accueil</title>
</head>
<body>
<h1>Bienvenue, <%= user.getUsername() %>!</h1>
<form action="ActionUn.do" method="post">
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
