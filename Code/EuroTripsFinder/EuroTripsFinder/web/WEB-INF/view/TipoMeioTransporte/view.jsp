<%-- 
    Document   : view
    Created on : 30/Mai/2013, 23:41:59
    Author     : miltonnunes52
--%>


<h1>Perfil de ${tipo.getNome()}</h1>

<form action="<%= request.getContextPath()%>/TipoMeioTransporte/update" method=post class="form-horizontal">

   
    <div class="control-group">
        <label class="control-label" for="inputNome">Nome</label>
        <div class="controls">
            <input type="text" id="inputNome" name="nome" placeholder="Nome" required="required" value="${tipo.getNome()}">
        </div>   
    </div>

    

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Update</button>
        <button type="button" class="btn">Limpar</button>
    </div>

</form>
