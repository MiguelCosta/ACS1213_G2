<%-- 
    Document   : register
    Created on : 2/Jun/2013, 2:09:00
    Author     : miltonnunes52
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<h1>Adicionar Artigo Publicitário</h1>

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
                            <option>${row.id}</option>
                        </c:forEach>
                    </select>
             </div>   
    </div>
    
    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Registar</button>
        <button type="button" class="btn">Limpar</button>
    </div>
    
</form>