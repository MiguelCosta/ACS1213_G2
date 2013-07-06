<%-- 
    Document   : updateCoordinate
    Created on : 30/Mai/2013, 15:05:16
    Author     : JorgeMaia
--%>

<div class="container" style="max-width: 425px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">

<h1>Atualizar Carro</h1>
<hr>
<form action="<%= request.getContextPath()%>/Carro/update" method=post class="form-horizontal">

       <div class="control-group">
        <label class="control-label" for="inputNomeLocal">Matrícula:</label>
        <div class="controls">
            <input type="text" id="inputNomeCoordenada" name="matricula" placeholder="Matrícula" required="required" value="${carro.getMatricula()}">
        </div>   
       </div>
        
     <div class="control-group">
        <label class="control-label" for="inputNomeLocal">Descrição:</label>
        <div class="controls">
            <input type="text" id="inputNomeCoordenada" name="descricao" placeholder="Descrição" required="required" value="${carro.getDescricao()}">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputLatitude">Imagem:</label>        
        <div class="controls">
            <input type="text" id="inputLatitude" name="imagem" placeholder="Imagem" value="${carro.getImagem()}">
        </div>   
    </div>
    
      <div class="control-group">
        <label class="control-label" for="inputCidade">Categoria:</label>
        <div class="controls">
        <a class="btn" href="<%= request.getContextPath()%>/Categoria/view?id=${carro.getCategoriaid().getId()}">${carro.getCategoriaid().getNome()}</a>
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
        <a class="btn" href="<%= request.getContextPath()%>/Carro/index">Cancelar</a>
    </div>

</form>
        
</div>
