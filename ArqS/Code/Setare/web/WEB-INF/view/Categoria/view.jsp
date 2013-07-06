<%-- 
    Document   : updateCoordinate
    Created on : 30/Mai/2013, 15:05:16
    Author     : JorgeMaia
--%>

<div class="container" style="max-width: 425px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">

<h1>Atualizar Categoria</h1>
<hr>
<form action="<%= request.getContextPath()%>/Categoria/update" method=post class="form-horizontal">

       <div class="control-group">
        <label class="control-label" for="inputNomeLocal">Nome:</label>
        <div class="controls">
            <input type="text" id="inputNomeCoordenada" name="nome" placeholder="Nome" required="required" value="${categoria.getNome()}">
        </div>   
       </div>
        
     <div class="control-group">
        <label class="control-label" for="inputNomeLocal">Descrição:</label>
        <div class="controls">
            <input type="text" id="inputNomeCoordenada" name="descricao" placeholder="Descrição" required="required" value="${categoria.getDescricao()}">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputLatitude">Preço depósito:</label>        
        <div class="controls">
            <input type="text" id="inputLatitude" name="deposito" placeholder="Preço" value="${categoria.getPrecoDeposito()}">
        </div>   
    </div>
    
     <div class="control-group">
        <label class="control-label" for="inputLongitude">Preço hora:</label>
        <div class="controls">
            <input type="text" id="inputLongitude" name="hora" placeholder="Preço" value="${categoria.getPrecoPorHora()}">
        </div>   
    </div>

    <% if (session.getAttribute("erro") != null) {%>
    <div class="alert alert-error">
        <%= session.getAttribute("erro")%>
    </div>
    <% session.setAttribute("erro", null);
        }%>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Guardar</button>
        <a class="btn" href="<%= request.getContextPath()%>/Categoria/index">Cancelar</a>
    </div>

</form>
        
</div>
