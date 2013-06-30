<%-- 
    Document   : register
    Created on : 5/Jun/2013, 1:07:29
    Author     : JorgeMaia
--%>


<div class="container" style="max-width: 390px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">

<h1>Adicionar Atividade:</h1>


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
        <button type="reset" class="btn">Limpar</button>
        <a class="btn" href="<%= request.getContextPath()%>/Atividade/index">Cancelar</a>
    </div>


</form>

</div>
