<%-- 
    Document   : view
    Created on : 4/Jun/2013, 18:26:49
    Author     : JorgeMaia
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<form action="<%= request.getContextPath()%>/Viagem/view" method=post class="form-horizontal">
    <h1>Detalhes da Viagem</h1>
    <div class="control-group">
        <label class="control-label" for="inputNomeViagem">Nome da Viagem</label>
        <div class="controls">
            <input type="text" id="inputEmail" name="nome" placeholder="nome" required="required" value="${viagem.getNome()}">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputDescricao">Descri��o</label>
        <div class="controls">
            <input type="text" id="inputDescricao" name="descricao" placeholder="descricao" required="required" readonly="readonly" value="${viagem.getDescricao()}">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputDataInicio">Data de In�cio</label>
        <div class="controls">
            <input type="text" id="inputDataInicio" name="datainicio" placeholder="Data de In�cio" required="required" value="${viagem.getDatainicio()}">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputDataFim">Data de Fim</label>
        <div class="controls">
            <input type="text" id="inputDataFim" name="datafim" placeholder="Data de Fim" required="required" value="${viagem.getDatafim()}">
        </div>   
    </div>

    <h1>Etapas</h1>

    <table class="table table-hover"> 
        <thead> 
        <td>ID</td>       
        <td>Data Partida</td>
        <td>Data Chegada</td>
        <td>Valor</td>
        <td>Local Partida</td>
        <td>Local Chegada</td>
        <td>Meio de Transporte</td>

        </thead> 
        
        <c:forEach var="row" items="${viagem.getPercursoid().getEtapaCollection()}"> 
            <tr>
                <td>${row.getId()}</td>
                <td>${row.getDatapartida()}</td>
                <td>${row.getDatachegada()}</td>
                <td>${row.getValor()}</td>
                <td>${row.getLocalparageminicialid()}</td>
                <td>${row.getLocalparagemdestinoid()}</td>
                <td>${row.getMeioTransporteid()}</td>
            </tr>

        </c:forEach> 
    </table>    



</form>









<form action="<%= request.getContextPath()%>/Etapa" method=post class="form-horizontal">  
    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Consultar Etapas</button>
    </div>
</form>

