<%@page import="entity.Viagem"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form action="<%= request.getContextPath()%>/Viagem/view" method=post class="form-horizontal">

    <div style="float: left; clear: left; position: static;">
        <h1>Detalhes da Viagem</h1>
        <div class="control-group">
            <label class="control-label" for="inputNomeViagem">Nome da Viagem</label>
            <div class="controls">
                <input type="text" id="inputEmail" style="height: 30px;" name="nome" placeholder="nome" required="required" value="${viagem.getNome()}">
            </div>   
        </div>

        <div class="control-group">
            <label class="control-label" for="inputDescricao">Descrição</label>
            <div class="controls">
                <textarea id="inputDescricao" name="descricao" placeholder="descricao" required="required" >${viagem.getDescricao()}</textarea>
            </div>   
        </div>

        <div class="control-group">
            <label class="control-label" for="inputDataInicio">Data de Início</label>
            <div class="controls">
                <input type="text" id="inputDataInicio" name="datainicio" placeholder="Data de Início" required="required" value="${viagem.getDatainicioString()}">
            </div>   
        </div>

        <div class="control-group">
            <label class="control-label" for="inputDataFim">Data de Fim</label>
            <div class="controls">
                <input type="text" id="inputDataFim" name="datafim" placeholder="Data de Fim" required="required" value="${viagem.getDatafimString()}">
            </div>   
        </div>

        <script type="text/javascript">
            window.onload = function() {
                calcRoute('${origem}', '${destino}', '${intermedios}');
            };
        </script>
    </div>

    <div style="float: right; width: 60%; height: 60%; clear: right; position: static;">
        <div id="map-canvas" style="float:left;width:50%;height:70%;"></div>
        <div id="directions_panel" style="margin:20px;background-color:#FFEE77;"></div>
    </div>

    <div style="position: static; clear: both;">

        <h1>Percurso</h1>
        <% if (((Viagem) request.getAttribute("viagem")).getPercursoid() != null) {%>

        <div class="control-group">
            <label class="control-label">Etapas</label>
            <div class="controls">
                <input type="text" name="maximoEtapas" style="height: 30px;" value="${viagem.getPercursoid().getEtapaCollection().size()} de ${viagem.getPercursoid().getLimiteetapas()}">                
            </div>   
        </div>

        <div class="control-group">
            <label class="control-label">Custo</label>
            <div class="controls">
                <input type="text" name="maximoCusto" style="height: 30px;" value="${viagem.getPercursoid().getCustoTotal()}">                
            </div>   
        </div>



        <% } else {%>
        <form action="<%= request.getContextPath()%>/Viagem/addPercurso" method=post class="form-horizontal">
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Adicionar Percurso</button>
            </div>
        </form>
        <% }%>

        <h2>Etapas 
            <a class="btn btn-success" href="<%= request.getContextPath()%>/Etapa/register" ><i class="icon-plus icon-white"></i></a>
        </h2>
        <table class="table table-hover"> 
            <thead> 
            <th>ID</th>       
            <th>Data Partida</th>
            <th>Data Chegada</th>
            <th>Valor</td>
            <th>Local Partida</th>
            <th>Local Chegada</th>
            <th>Meio de Transporte</th>

            </thead> 

            <c:forEach var="row" items="${viagem.getPercursoid().getEtapaCollection()}"> 
                <tr>
                    <td>${row.getId()}</td>
                    <td>${row.getDatapartidaString()}</td>
                    <td>${row.getDatachegadaString()}</td>
                    <td>${row.getValor()}</td>
                    <td>${row.getLocalparageminicialid()}</td>
                    <td>${row.getLocalparagemdestinoid()}</td>
                    <td>Autocarro</td>
                    <td><a class="btn btn-info" href="<%= request.getContextPath()%>/Etapa/view?id=${row.id}" ><i class="icon-pencil icon-white"></i></a>
                        <a class="btn btn-danger" href="<%= request.getContextPath()%>/Etapa/delete?id=${row.id}" ><i class="icon-remove icon-white"></i></a></td>
                </tr>
            </c:forEach> 
        </table>       
    </div>

</form>



