<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 



<div style="float: left; clear: left; position: static;">
    <div class="container" style="max-width: 250px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px;">

        <form class="form-signin">
            <h1 class="form-signin-heading" style="text-align: center">Dados da Reserva:</h1>
            <hr>

            
            <div class="control-group">
                <label class="control-label" for="localLevantamentoId">Levantamento:</label>
                <div class="controls">
                    <select id="localLevantamentoId" name="localLevantamentoId" style="width: 100%">
                        <c:forEach var="row" items="${listalocais}">
                            <option  name ="localLevantamentoId"value="${row.id}" ${row.id == localLevantamentoId ? 'selected' : ''}>${row.nome}</option>
                        </c:forEach>
                    </select>
                </div>   
            </div>
            <div style="float: left; clear: left; position: static;">
                <div class="control-group">

                    <div class="controls">          
                        <input type="date" id="inputDataLevantamento" name="dataLevantamento" placeholder="dd-MM-AAAA" style="width:150px;" value="${dataLevantamento}">
                    </div>   
                </div>


            </div>
            <div style="float: right; clear: right; position: static;">
                <div class="control-group">

                    <div class="controls">
                        <input type="time" id="inputHoraLevantamento" name="horalevantamento" style="width:90px;" value="${horaLevantamento}">
                    </div>   
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="localEntregaId">Entrega:</label>
                <div class="controls">
                    <select id="localEntregaId" name="localEntregaId" style="width: 100%">
                        <c:forEach var="row" items="${listalocais}">
                            <option name ="localEntregaId" value="${row.id}" ${row.id == localEntregaId ? 'selected' : ''}>${row.nome}</option>
                        </c:forEach>
                    </select>
                </div>   
            </div>
            
            <div style="float: right; clear: right; position: static;">
                <div class="control-group">

                    <div class="controls">
                        <input type="number" id="inputpassageiros" name="passageiros" style="width:90px;" value="${passageiros}">
                    </div>   
                </div>
            </div>
                    
            <label class="checkbox">
                <input type="checkbox" name="bagagem" value="${bagagem}">Bagagem
                </label>


        </form>

    </div> <!-- /container -->

</div>
      <div>
        <td  style="text-align: center; vertical-align: middle; width: 18%;"><a class="btn btn-large btn-primary" href="<%= request.getContextPath()%>/ServicoTaxi/registerContinua">Efectuar Pagamento</a></td>
        <img src="/Setare/img/paypal_now_accepted.png">
      </div>
</div>




