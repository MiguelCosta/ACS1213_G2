<%-- 
    Document   : login
    Created on : 27/Mai/2013, 23:02:30
    Author     : Miguel
--%>

<%@page import="entity.Utilizador"%>



<div class="container" style="max-width: 300px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">

      <form class="form-signin">
        <h1 class="form-signin-heading">Login</h1>
        <hr>
        <label for="xlInput">Username </label>
        <input type="text" class="input-block-level" name="username" placeholder="Username">
        <label for="xlInput">Password </label>        
        <input type="password" class="input-block-level" name="password" placeholder="Password">
        <button class="btn btn-large btn-primary" type="submit">Login</button>
        <a class="btn btn-large" href="<%= request.getContextPath()%>/">Cancelar</a>

      </form>

</div> <!-- /container -->
