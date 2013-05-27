<%-- 
    Document   : login
    Created on : 27/Mai/2013, 23:02:30
    Author     : Miguel
--%>

<%@page import="entity.Utilizador"%>
<h1>Login</h1>

<form action="Utilizador" method=post>
    <div id="loginBox">
        <p><strong>username:</strong>
            <input type="text" size="20" name="username"></p>

        <p><strong>password:</strong>
            <input type="password" size="20" name="password"></p>

        <p><input type="submit" value="submit"></p>
    </div>
</form>