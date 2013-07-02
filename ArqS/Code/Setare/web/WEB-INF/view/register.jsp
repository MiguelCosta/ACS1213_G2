<%-- 
    Document   : register
    Created on : 28/Mai/2013, 19:25:03
    Author     : Miguel
--%>



<div class="container" style="max-width: 325px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">

      <form  action="<%= request.getContextPath()%>/Utilizador/addUser" method=post class="form-signin">
        <h1 class="form-signin-heading">Registar</h1>
        <hr>
        <label for="xlInput">Username </label>
        <input type="text" class="input-block-level" name="nome" placeholder="Username">
        <label for="xlInput">Password </label>        
        <input type="password" class="input-block-level" name="password" placeholder="Password">      
        <label for="xlInput">Email </label>
        <input type="text" class="input-block-level" name="email" placeholder="Email">      
        
        <button class="btn btn-large btn-primary" type="submit">Registar</button>
        <button class="btn btn-large" type="reset">Limpar</button>
        <a class="btn btn-large" href="<%= request.getContextPath()%>/">Cancelar</a>
        

      </form>

</div> <!-- /container -->


