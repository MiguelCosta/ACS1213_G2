
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Viagens dispon�veis</h1>

<p class="text-info">N�mero de viagens dispon�veis: ${caminhos.size()}</p>

<form action="<%= request.getContextPath()%>/Etapa/add" method=post class="form-horizontal">

    <c:forEach var="row" items="${caminhos}"> 
        <label class="radio">
            <input type="radio" name="viagemselec" id="optionsRadios1" value="${row.getOrigem().getId()}-${row.getDestino().getId()}">
            Partida �s <span class="label label-info">${row.getOrigem().getDatapartida()}</span> 
            e chegada �s <span class="label label-info">${row.getDestino().getDatachegada()}</span> <small>${row.getOrigem().getViagemid()}</small>.
        </label>
    </c:forEach> 

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Selecionar</button>
    </div>

</form>