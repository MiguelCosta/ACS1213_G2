<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 




<div class="container" style="max-width: 350px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px;">

    <form  action="<%= request.getContextPath()%>/ServicoTaxi/registerContinua" method=post>
        <h1 class="form-signin-heading" style="text-align: center">Confirmar Dados da Reserva:</h1>
        <hr>


        <div class="control-group">
            <label class="control-label" for="localLevantamentoId">Origem:</label>
            <div class="controls">
                <select id="localLevantamentoId" name="localLevantamentoId" style="width: 100%" required="required">
                    <c:forEach var="row" items="${listalocais}">
                        <option  name ="localLevantamentoId"value="${row.id}" ${row.id == localLevantamentoId ? 'selected' : ''}>${row.nome}</option>
                    </c:forEach>
                </select>
            </div>   
        </div>
        <div style="float: left; clear: left; position: static; width: 69%">
            <div class="control-group">
                <label class="control-label" for="localLevantamentoId">Data:</label>
                <div class="controls">          
                    <input type="date" id="inputDataLevantamento" name="dataLevantamento" placeholder="dd-MM-AAAA" style="width:150px; height: 30;" value="${dataLevantamento}" required="required">
                </div>   
            </div>


        </div>
        <div style="float: right; clear: right; position: static; width: 29%">
            <div class="control-group">
                <label class="control-label" for="localLevantamentoId">Hora:</label>
                <div class="controls">
                    <input type="time" id="inputHoraLevantamento" name="horalevantamento" style="width:100%; height: 30;" value="${horaLevantamento}" required="required">
                </div>   
            </div>
        </div>
        <div class="control-group">

            <label class="control-label" for="localEntregaId">Destino:</label>
            <select id="localEntregaId" name="localEntregaId" style="width: 100%" required="required">
                <c:forEach var="row" items="${listalocais}">
                    <option name ="localEntregaId" value="${row.id}" ${row.id == localEntregaId ? 'selected' : ''}>${row.nome}</option>
                </c:forEach>
            </select>

        </div>
        <div style="float: left; clear: left; position: static; width: 50%">
            <div class="control-group">
                <label class="control-label" for="localLevantamentoId">Nº passageiros:</label>
                <div class="controls">
                    <input type="number" id="inputpassageiros" name="passageiros" style="width:50%;height: 30px;" value="${passageiros}" required="required">
                </div>   
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="inputBagagem">Bagagem</label>
            <div class="controls">
                <select id="inputLocalOrigem" name="bagagem" required="required" style="width: 50%">
                    <option name ="localEntregaId" value="Não" ${'Não' == bagagem ? 'selected' : ''}>Não</option>
                    <option name ="localEntregaId" value="Sim" ${'Sim' == bagagem ? 'selected' : ''}>Sim</option>
                </select>
            </div>   
        </div>


        <div style="width: 100%; text-align: center;"> 
            <br>
            <button class="btn btn-large btn-primary" type="submit" name="id">Confirmar</button>
        </div>


    </form>

</div> <!-- /container -->







