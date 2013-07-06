<%-- 
    Document   : register
    Created on : 5/Jul/2013, 16:26:30
    Author     : miltonnunes52
--%>

<%-- 
    Document   : insertCoordinates
    Created on : 30/Mai/2013, 15:04:57
    Author     : JorgeMaia
--%>

<div class="container" style="max-width: 445px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">

<h1>Inserir Categoria</h1>
<hr>
<form action="<%= request.getContextPath()%>/Categoria/add" method=post class="form-horizontal">

    

    <div class="control-group">
        <label class="control-label" for="inputNomeLocal">Nome</label>
        <div class="controls">
            <input type="text" id="inputNomeCoordenada" name="nome" placeholder="Nome" required="required">
        </div>   
    </div>
    
    <div class="control-group">
        <label class="control-label" for="inputNomeLocal">Descrição</label>
        <div class="controls">
            <input type="text" id="inputNomeCoordenada" name="descricao" placeholder="Descrição" required="required">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputLatitude">Preço depósito:</label>        
        <div class="controls">
            <input type="text" id="inputLatitude" name="deposito" placeholder="Preço" required="required">
        </div>   
    </div>
    
     <div class="control-group">
        <label class="control-label" for="inputLongitude">Preço hora:</label>
        <div class="controls">
            <input type="text" id="inputLongitude" name="hora" placeholder="Preço" required="required">
        </div>   
    </div>
    



    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Registar</button>
        <button type="reset" class="btn">Limpar</button>
        <a class="btn" href="<%= request.getContextPath()%>/Categoria/index">Cancelar</a>
    </div>

</form>

</div>
