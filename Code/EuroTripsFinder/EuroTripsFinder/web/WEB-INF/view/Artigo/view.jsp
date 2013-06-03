<%-- 
    Document   : view
    Created on : 2/Jun/2013, 2:08:53
    Author     : miltonnunes52
--%>

<h1>${artigo.getNome()}</h1>

<form action="<%= request.getContextPath()%>/Artigo/update" method=post class="form-horizontal">

    <input type="hidden" name ="id" value="${artigo.getId()}">

    <div class="control-group">
        <label class="control-label" for="inputNome">Nome</label>
        <div class="controls">
            <input type="text" id="inputNome" name="nome" placeholder="Nome" required="required" value="${artigo.getNome()}">
        </div>   
    </div>
    <div class="control-group">
        <label class="control-label" for="inputConteudo">Conteúdo</label>
        <div class="controls">
            <input type="text" id="inputNome" name="conteudo" placeholder="Conteudo" required="required" value="${artigo.getConteudo()}">
        </div>   
    </div>
    

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Update</button>
        <button type="button" class="btn">Limpar</button>
    </div>

</form>
