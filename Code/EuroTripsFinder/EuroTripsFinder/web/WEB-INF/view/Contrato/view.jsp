<%-- 
    Document   : view
    Created on : 2/Jun/2013, 2:09:08
    Author     : miltonnunes52
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<h1>Contrato ${contrato.getId()}</h1>

<form action="<%= request.getContextPath()%>/Contrato/update" method=post class="form-horizontal">

   
    <div class="control-group">
        <label class="control-label" for="inputValor">Valor</label>
        <div class="controls">
            <input type="text" id="inputNome" name="valor" placeholder="Valor" required="required" value="${contrato.getValor()}">
        </div>   
    </div>
    <div class="control-group">
        <label class="control-label" for="inputDatainicio">Data Início</label>
        <div class="controls">
            <input type="date" id="inputDataInicio" name="datainicio" placeholder="yyyy-MM-dd" min="1900-01-01" max="2020-01-01" value="${contrato.getDatainicioString()}">
        </div>   
    </div>
    
    <div class="control-group">
        <label class="control-label" for="inputDatainicio">Data Fim</label>
        <div class="controls">
            <input type="date" id="inputDataFim" name="datafim" placeholder="yyyy-MM-dd" min="1900-01-01" max="2020-01-01" value="${contrato.getDatafimString()}">
        </div>   
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Update</button>
        <button type="button" class="btn">Limpar</button>
    </div>

</form>
<c:choose>
    <c:when test="${artigos.size() > 0}">
         <h1>Artigos Publicitários</h1>
        <table class="table table-hover"> 
                <tr> 
                    <td>Nome:</td>
                </tr>
                <c:forEach var="row" items="${artigos}"> 
                <tr>
                    <td><a href="<%= request.getContextPath()%>/Artigo/view?id=${row.id}">${row.nome}</a></td>
                </tr>
                </c:forEach> 
        </table>
    </c:when>
</c:choose>
