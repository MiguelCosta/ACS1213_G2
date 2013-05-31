<%-- 
    Document   : register
    Created on : 30/Mai/2013, 23:22:39
    Author     : miltonnunes52
--%>

<h1>Adicionar Meio de Transporte</h1>

<form action="<%= request.getContextPath()%>/TipoMeioTransporte/add" method=post class="form-horizontal">
 
    <div class="control-group">
        <label class="control-label" for="inputNome">Nome</label>
        <div class="controls">
            <input type="text" id="inputNome" name="nome" placeholder="Nome" required="required">
        </div>   
    </div>
    
    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Registar</button>
        <button type="button" class="btn">Limpar</button>
    </div>

</form>