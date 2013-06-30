<%-- 
    Document   : register
    Created on : 30/Mai/2013, 23:22:39
    Author     : miltonnunes52
--%>

<div class="container" style="max-width: 390px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">

<h1>Adicionar Tipo de Meio de Transporte</h1>

<form action="<%= request.getContextPath()%>/TipoMeioTransporte/add" method=post class="form-horizontal">
 
    <div class="control-group">
        <label class="control-label" for="inputNome">Nome</label>
        <div class="controls">
            <input type="text" id="inputNome" name="nome" placeholder="Nome" required="required">
        </div>   
    </div>
    
    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Registar</button>
        <button type="reset" class="btn">Limpar</button>
        <a class="btn" href="<%= request.getContextPath()%>/TipoMeioTransporte/index">Cancelar</a>
    </div>

</form>

</div>