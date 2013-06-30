<%-- 
    Document   : view
    Created on : 30/Mai/2013, 23:41:59
    Author     : miltonnunes52
--%>

<div class="container" style="max-width: 390px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">


<h1>Perfil de ${tipo.getNome()}</h1>

<form action="<%= request.getContextPath()%>/TipoMeioTransporte/update" method=post class="form-horizontal">

    <input type="hidden" name ="id" value="${tipo.getId()}">
   
    <div class="control-group">
        <label class="control-label" for="inputNome">Nome</label>
        <div class="controls">
            <input type="text" id="inputNome" name="nome" placeholder="Nome" required="required" value="${tipo.getNome()}">
        </div>   
    </div>

    

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Update</button>
        <a class="btn" href="<%= request.getContextPath()%>/TipoMeioTransporte/index">Cancelar</a>
    </div>

</form>

</div>