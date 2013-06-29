<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form action="<%= request.getContextPath()%>/Viagem/view" method=post class="form-horizontal">

    <div style="float: left; clear: left; position: static;">
        <h1>Detalhes da Viagem</h1>
        <div class="control-group">
            <label class="control-label" for="inputNomeViagem">Nome da Viagem</label>
            <div class="controls">
                <input type="text" id="inputEmail" name="nome" placeholder="nome" required="required" value="${viagem.getNome()}">
            </div>   
        </div>

        <div class="control-group">
            <label class="control-label" for="inputDescricao">Descrição</label>
            <div class="controls">
                <input type="text" id="inputDescricao" name="descricao" placeholder="descricao" required="required" readonly="readonly" value="${viagem.getDescricao()}">
            </div>   
        </div>

        <div class="control-group">
            <label class="control-label" for="inputDataInicio">Data de Início</label>
            <div class="controls">
                <input type="text" id="inputDataInicio" name="datainicio" placeholder="Data de Início" required="required" value="${viagem.getDatainicio()}">
            </div>   
        </div>

        <div class="control-group">
            <label class="control-label" for="inputDataFim">Data de Fim</label>
            <div class="controls">
                <input type="text" id="inputDataFim" name="datafim" placeholder="Data de Fim" required="required" value="${viagem.getDatafim()}">
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
    </div>

</form>
<form action="<%= request.getContextPath()%>/Etapa/register" method=post class="form-horizontal">
    <div class="form-actions">
        <button type="submit" class="btn btn-primary" >Adicionar Nova</button>
    </div>
</form>



