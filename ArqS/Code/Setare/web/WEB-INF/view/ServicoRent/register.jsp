<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 



<form  action="<%= request.getContextPath()%>/ServicoRent/register" method=post class="form-signin">

    <div class="container" style="max-width: 80%; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px;">
        <h1 class="form-signin-heading" style="text-align: center">Efetuar Reserva</h1>
        <hr>
        <div class="row-fluid">
            <div class="span4" style="vertical-align:  middle;">


                <div class="control-group">
                    <label class="control-label" for="inputCategoria">Categoria do Veiculo:</label>
                    <div class="controls">               
                        <select id="inputCategoria" name="categoria">
                            <c:forEach var="row" items="${listacategorias}">
                                <option value="${row.id}">${row.nome}</option>
                            </c:forEach>
                        </select>
                    </div>   
                </div>


            </div>
            <div class="span4">
                <div class="control-group">
                    <label class="control-label" for="inputLocalOrigem">Estação Levantamento:</label>
                    <div class="controls">
                        <select id="inputLocalOrigem" name="localorigem">
                            <c:forEach var="row" items="${listalocais}">
                                <option value="${row.id}">${row.nome}</option>
                            </c:forEach>
                        </select>
                    </div>   
                </div>

                <div class="control-group">
                    <label class="control-label" for="inputDataLevantamento">Data:</label>
                    <div class="controls">          
                        <input type="date" id="inputDataLevantamento" name="datapartida" placeholder="dd-MM-AAAA" style="width: 50%; height: 30px;" >
                    </div>   
                </div>

                <div class="control-group">
                    <label class="control-label" for="inputHoraLevantamento">Hora:</label>
                    <div class="controls">
                        <input type="time" id="inputHoraLevantamento" name="horalevantamento" placeholder="00:00" style="width: 35%; height: 30px;">
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

                <div class="control-group">
                    <label class="control-label" for="inputDataEntrega">Data:</label>
                    <div class="controls">            
                        <input type="date" id="inputDataEntrega" name="datachegada" placeholder="Data de Entrega" style="width: 50%; height: 30px;" >
                    </div>   
                </div>

                <div class="control-group">
                    <label class="control-label" for="inputHoraEntrega">Hora:</label>
                    <div class="controls">
                        <input type="time" id="inputHoraEntrega" name="horaentrega" placeholder="00:00" style="width: 35%; height: 30px;">
                    </div>   
                </div>
            </div>

        </div>


        <div class="jumbotron" style="text-align: center;">
            <button class="btn btn-large btn-primary" type="submit">Reservar</button>
        </div>
    </div>
</form>


