<%-- 
    Document   : view
    Created on : 2/Jun/2013, 2:08:53
    Author     : miltonnunes52
--%>

<div class="container" style="max-width: 390px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">


<h1>${atividade.getNome()}</h1>
<hr>

<form action="<%= request.getContextPath()%>/Atividade/update" method=post class="form-horizontal">

    <input type="hidden" name ="id" value="${atividade.getId()}">

    <div class="control-group">
        <label class="control-label" for="inputNome">Nome:</label>
        <div class="controls">
            <input type="text" id="inputNome" name="nome" placeholder="Nome" required="required" value="${atividade.getNome()}">
        </div>   
    </div>
    <div class="control-group">
        <label class="control-label" for="inputDescricao">Descrição:</label>        
        <div class="controls" id="descricao">              
            <TEXTAREA id="inputDescricao" name="descricao" ROWS=3 COLS=30 required="required" >${atividade.getDescricao()}</TEXTAREA>
        </div>   
    </div>
    

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Guardar</button>
        <a class="btn" href="<%= request.getContextPath()%>/Atividade/index">Cancelar</a>

    </div>

</form>

</div>