<%-- 
    Document   : register
    Created on : 2/Jun/2013, 2:09:00
    Author     : miltonnunes52
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<div class="container" style="max-width: 445px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">

<h1>Adicionar Artigo</h1>
<hr>

<form action="<%= request.getContextPath()%>/Artigo/add" method=post class="form-horizontal">

    <div class="control-group">
        <label class="control-label" for="inputNome">Nome</label>
        <div class="controls">
            <input type="text" id="inputNome" name="nome" placeholder="Nome" required="required">
        </div>   
    </div>
    <div class="control-group">
        <label class="control-label" for="inputConteudo">Conteúdo</label>
        <div class="controls">
            <input type="text" id="inputNome" name="conteudo" placeholder="Conteudo" required="required">
        </div>   
    </div>
    
    <div class="control-group">
        <label class="control-label" for="inputContrato">Contrato</label>
            <div class="controls">
                <select id="inputContratoid" name="contratoid">
                        <c:forEach var="row" items="${listcontratos}">
                            <option value="${row.id}">${row.getClienteid().getUtilizador().getNome()}</option>
                        </c:forEach>
                    </select>
             </div>   
    </div>
    
    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Registar</button>
        <button type="reset" class="btn">Limpar</button>
        <a class="btn" href="<%= request.getContextPath()%>/Artigo/index">Cancelar</a>

    </div>
    
</form>

</div>