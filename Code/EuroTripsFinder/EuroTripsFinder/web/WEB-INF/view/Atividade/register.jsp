<%-- 
    Document   : register
    Created on : 5/Jun/2013, 1:07:29
    Author     : JorgeMaia
--%>

<h1>Adicionar nova atividade:</h1>


<form action="<%= request.getContextPath()%>/Atividade/add" method=post class="form-horizontal">



    <div class="control-group">
        <label class="control-label" for="inputNomeAtividade">Nome da Atividade:</label>        
        <div class="controls" id="nomeAtividade">                   
            <input type="text" id="inputNomeAtividade" name="nomeAtividade" required="required" placeholder="Nome da Atividade"/>       
        </div>   
    </div>
    <div class="control-group">
        <label class="control-label" for="inputDescricao">Descrição:</label>        
        <div class="controls" id="descricao">              
            <TEXTAREA id="inputDescricao" NAME="descricao" ROWS=3 COLS=30 required="required" placeholder="Descrição"></textarea>
        </div>   
    </div>


    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Registar</button>
        <button type="button" class="btn">Limpar</button>
    </div>


</form>
