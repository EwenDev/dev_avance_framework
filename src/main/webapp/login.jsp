<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
</head>
<body>
<form action="LoginAction.do" method="post">
  <label>Username :</label>
  <input type="text" name="username" required/><br/>
  <label>Password :</label>
  <input type="password" name="password" required/><br/>
  <input type="submit" value="Login"/>
</form>
</body>
</html>
