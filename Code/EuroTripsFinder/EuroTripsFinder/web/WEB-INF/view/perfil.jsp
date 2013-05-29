<%-- 
    Document   : perfil
    Created on : 29/Mai/2013, 16:01:42
    Author     : miltonnunes52
--%>



<h1></h1>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%=((Utilizador) session.getAttribute("user")).getNome() %></h1>
    </body>
</html>
