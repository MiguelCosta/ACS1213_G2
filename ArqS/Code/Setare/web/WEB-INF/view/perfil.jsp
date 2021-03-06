<%-- 
    Document   : perfil
    Created on : 29/Mai/2013, 16:01:42
    Author     : miltonnunes52
--%>


<div class="container" style="max-width: 390px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">

<h1>Perfil de ${user.getNome()}</h1>
<hr>
<form action="<%= request.getContextPath()%>/Utilizador/update" method=post class="form-horizontal">

    <div class="control-group">
        <label class="control-label" for="inputEmail">Email</label>
        <div class="controls">
            <input type="email" id="inputEmail" name="email" placeholder="email" required="required" value="${user.getEmail()}">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputUsername">Username</label>
        <div class="controls">
            <input type="text" id="inputUsername" name="nome" placeholder="Username" required="required" readonly="readonly" value="${user.getNome()}">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputPassword">Password</label>
        <div class="controls">
            <input type="password" id="inputPassword" name="password" placeholder="Password" required="required" value="${user.getPassword()}">
        </div>   
    </div>

  

    <% if (session.getAttribute("erro") != null) {%>
    <div class="alert alert-error">
        <%= session.getAttribute("erro")%>
    </div>
    <% session.setAttribute("erro", null);
        }%>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Update</button>
        <a class="btn" href="<%= request.getContextPath()%>/">Cancelar</a>
    </div>

</form>
</div>