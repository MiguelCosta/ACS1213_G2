
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Viagens disponíveis</h1>

Local de partida: ${etapa.getLocalparageminicialid()} <br/>
Data de partida: ${etapa.getDatapartida()} <br/>
Local de chegada: ${etapa.getLocalparagemdestinoid()} <br/>
Data de chegada: ${etapa.getDatachegada()} <br/>

<p class="text-info">Número de viagens disponíveis: ${caminhos.size()}</p>

<form action="<%= request.getContextPath()%>/Etapa/addEtapa" method=post class="form-horizontal">

    <c:forEach var="row" items="${caminhos}"> 
        <label class="radio">
            <input type="radio" name="viagemselec" id="optionsRadios1" value="${row.getOrigem().getId()}-${row.getDestino().getId()}">
            Partida às <span class="label label-info">${row.getOrigem().getDatapartida()}</span> 
            e chegada às <span class="label label-info">${row.getDestino().getDatachegada()}</span>
        </label>
    </c:forEach> 

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Selecionar</button>
        <button onClick="history.go(-1)">Voltar</button>
    </div>

</form>