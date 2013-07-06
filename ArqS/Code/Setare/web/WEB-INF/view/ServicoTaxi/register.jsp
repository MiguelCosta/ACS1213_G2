<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<form  action="<%= request.getContextPath()%>/ServicoTaxi/register" method=post class="form-signin">
    <div class="container" style="max-width: 80%; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px;">
        <div class="row-fluid">
            <div class="span4">


            </div>
            <div class="span4">
                <div class="control-group">
                    <label class="control-label" for="inputLocalOrigem">Estação Levantamento:</label>
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
                        <input type="date" id="inputDataLevantamento" name="datapartida" placeholder="dd-MM-AAAA" required="required">
                    </div>   
                </div>

                <div class="control-group">
                    <label class="control-label" for="inputHoraLevantamento">Hora:</label>
                    <div class="controls">
                        <input type="time" id="inputHoraLevantamento" name="horalevantamento" placeholder="00:00" required="required">
                    </div>   
                </div> 
                <div class="control-group">
                    <label class="control-label" for="inputBagagem">Bagagem</label>
                    <div class="controls">
                        <select id="inputLocalOrigem" name="bagagem" required="required">
                                <option value="Não">Não</option>
                                <option value="Sim">Sim</option>
                                
                            
                        </select>
                    </div>   
                </div>
            </div>
            
            
            <div class="span4">
                <div class="control-group">
                    <label class="control-label" for="inputLocalChegada">Estação Entrega</label>
                    <div class="controls">              
                        <select id="inputLocalChegada" name="localchegada" required="required">
                            <c:forEach var="row" items="${listalocais}">
                                <option value="${row.id}">${row.nome}</option>
                            </c:forEach>
                        </select>
                    </div>   
                </div>

                

        </div>
            
            <div class="control-group">
                    <label class="control-label" for="inputPassageiros">Passageiros:</label>
                    <div class="controls">               
                         <input type="number" id="inputHoraLevantamento" name="passageiros" placeholder="Número de passageiros" required="required">
                         
                    </div>   
                </div>


        <div class="jumbotron" style="text-align: center;">
            <button class="btn btn-large btn-primary" type="submit">Reservar</button>
        </div>
    </div>
</form>


