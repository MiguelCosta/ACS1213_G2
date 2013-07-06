<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<form  action="<%= request.getContextPath()%>/ServicoTaxi/register" method=post class="form-signin">
    <div class="container" style="max-width: 60%; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px;">
         <h1 class="form-signin-heading" style="text-align: center">Efetuar Reserva de Taxi</h1>
        <hr>
        <div class="row-fluid">

            <div style="float: left; width: 49%">
                <div class="control-group">
                    <label class="control-label" for="inputLocalOrigem">Origem:</label>
                    <div class="controls">
                        <select id="inputLocalOrigem" name="localorigem" required="required">
                            <c:forEach var="row" items="${listalocais}">
                                <option value="${row.id}">${row.nome}</option>
                            </c:forEach>
                        </select>
                    </div>   
                </div>

                <div class="control-group">
                    <label class="control-label" for="inputDataLevantamento">Data:</label>
                    <div class="controls">          
                        <input type="date" id="inputDataLevantamento" name="datapartida" placeholder="dd-MM-AAAA" required="required"  style="height: 30px; width: 50%;">
                    </div>   
                </div>

                <div class="control-group">
                    <label class="control-label" for="inputHoraLevantamento">Hora:</label>
                    <div class="controls">
                        <input type="time" style="height: 30px; width: 35%;" id="inputHoraLevantamento" name="horalevantamento" placeholder="00:00" required="required">
                    </div>   
                </div> 
                <div class="control-group">
                    <label class="control-label" for="inputBagagem">Bagagem</label>
                    <div class="controls">
                        <select id="inputLocalOrigem" name="bagagem" required="required" style="height: 30px; width: 35%;">
                            <option value="Não">Não</option>
                            <option value="Sim">Sim</option>


                        </select>
                    </div>   
                </div>
            </div>


            <div style="float: left; width: 49%">
                <div class="control-group">
                    <label class="control-label" for="inputLocalChegada">Destino:</label>
                    <div class="controls">              
                        <select id="inputLocalChegada" name="localchegada" required="required">
                            <c:forEach var="row" items="${listalocais}">
                                <option value="${row.id}">${row.nome}</option>
                            </c:forEach>
                        </select>
                    </div>   
                </div>





                <div class="control-group">
                    <label class="control-label" for="inputPassageiros">Passageiros:</label>
                    <div class="controls">               
                        <input type="number" style="height: 30px;" id="inputHoraLevantamento" name="passageiros" placeholder="Número de passageiros" required="required">

                    </div>   
                </div>



                <div class="jumbotron">
                    <br>
                    <br>
                    <br>
                    <button class="btn btn-large btn-primary" type="submit">Reservar</button>
                </div>
            </div>
        </div>
</form>


