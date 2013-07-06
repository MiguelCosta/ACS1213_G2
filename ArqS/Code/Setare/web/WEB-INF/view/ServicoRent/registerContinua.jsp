<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<script type="text/javascript" language="javascript">


    function showHide(aux) {
        var ele = document.getElementById("linha" + aux);

        if (ele.style.display === "block") {
            ele.style.display = "none";
        }
        else {
            ele.style.display = "block";
        }

    }
</script>

<form  action="<%= request.getContextPath()%>/ServicoRent/registerContinua" method=post>


    <div style="float: left; clear: left; position: static;">
        <div class="container" style="max-width: 250px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px;">


            <h1 class="form-signin-heading" style="text-align: center">Dados da Reserva:</h1>
            <hr>

            <div class="control-group">
                <label class="control-label" for="inputCategoria">Categoria do Veiculo:</label>
                <div class="controls">               
                    <select id="inputCategoria" name="categoria" style="width: 100%">
                        <c:forEach var="row" items="${listacategorias}">
                            <option value="${row.id}" ${row.id == categoriaId ? 'selected' : ''}>${row.nome}</option>
                        </c:forEach>
                    </select>
                </div>   
            </div>

            <div class="control-group">
                <label class="control-label" for="inputLocalOrigem">Levantamento:</label>
                <div class="controls">
                    <select id="inputLocalOrigem" name="localorigem" style="width: 100%">
                        <c:forEach var="row" items="${listalocais}">
                            <option value="${row.id}" ${row.id == localLevantamentoId ? 'selected' : ''}>${row.nome}</option>
                        </c:forEach>
                    </select>
                </div>   
            </div>
            <div style="float: left; clear: left; position: static;">
                <div class="control-group">

                    <div class="controls">          
                        <input type="date" id="inputDataLevantamento" name="datapartida" placeholder="dd-MM-AAAA" style="width:150px;" value="${dataLevantamento}">
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
                <label class="control-label" for="inputLocalEntrega">Entrega:</label>
                <div class="controls">
                    <select id="inputLocalOrigem" name="localchegada" style="width: 100%">
                        <c:forEach var="row" items="${listalocais}">
                            <option value="${row.id}" ${row.id == localEntregaId ? 'selected' : ''}>${row.nome}</option>
                        </c:forEach>
                    </select>
                </div>   
            </div>
            <div style="float: left; clear: left; position: static;">
                <div class="control-group">

                    <div class="controls">          
                        <input type="date" id="inputDataEntrega" name="datachegada" placeholder="dd-MM-AAAA" style="width:150px;" value="${dataEntrega}">
                    </div>   
                </div>


            </div>
            <div style="float: right; clear: right; position: static;">
                <div class="control-group">

                    <div class="controls">
                        <input type="time" id="inputHoraEntrega" name="horaEntrega" style="width:90px;" value="${horaEntrega}">
                    </div>   
                </div>
            </div>
            <div style="text-align: center;">
                <b>Dias:</b><input type="text" name="dias" value="${dias}">
            </div>




        </div> <!-- /container -->
        <img src="http://www.conceptvehicleleasing.co.uk/images/usability-Nov11/fuel1.png" width="90%" height="90%" style="vertical-align: middle;">

    </div>
    <div style="float: right; clear: right; position: relative; width: 70%;">

        <table class="table table-hover"> 


            <c:forEach var="row" items="${listacarros}"> 
                <tr>
                    <td style="width: 25%;"><img src="${row.imagem}" width="200" height="200"></td>  

                    <td>
                        <table style="width: 100%; height: 100%;">
                            <tr>
                                <td>
                                    ${row.descricao} <br> <b>Preço por Hora:</b>${precoCategoria}&#8364
                                </td>
                            </tr>
                            <tr style="text-align: center; vertical-align:  bottom;">
                                <td  >
                                    <a class="btn btn-small btn-info" onclick="showHide('${row.id}');" >+Extras</a>
                                    <div  id="linha${row.id}" value="mostra" style="display: none; width: 100%; float: left;"><br>

                                        <table style="width: 100%">

                                            <tr>
                                                <td style="width:60%">
                                                    <b><label>GPS</label></b>
                                                </td>
                                                <td style="text-align: right">
                                                    <b><label>6 &#8364/dia </label>

                                                </td>
                                                <td style="width:10%; float: right">
                                                    <input type="checkbox" name="gps${row.id}" value="1">    
                                                </td>

                                            </tr>
                                            <tr>
                                                <td style="width:60%">
                                                    <b><label>Cadeira de Bébé</label></b> 
                                                </td>
                                                <td style="text-align: right">
                                                    <b><label>8 &#8364/dia </label>
                                                </td>
                                                <td style="width:10%; float: right">
                                                    <input type="checkbox" name="cadeira${row.id}" value="1"> 
                                                </td>

                                            </tr>
                                            <tr>
                                                <td style="width:60%">
                                                    <b><label>Seguro Contra Todos os Riscos</label></b>
                                                </td>
                                                <td style="text-align: right">
                                                    <b><label>35 &#8364/dia </label>
                                                </td>
                                                <td style="width:10%; float: right">
                                                    <input type="checkbox" name="sctr${row.id}" value="1">

                                                </td>

                                            </tr>
                                            <tr>
                                                <td style="width:60%">
                                                    <b><label>Seguro Danos Próprios com franquia</label></b>  
                                                </td>
                                                <td style="text-align: right">
                                                    <b><label>(Extra incluído)</label>
                                                </td>
                                                <td>
                                                    <input type="checkbox" name="extrasCheckbox${row.id}" value="1" disabled checked="checked"> 
                                                </td>

                                            </tr>
                                            <tr>
                                                <td style="width:60%">
                                                    <b><label>Condutor Adicional</label></b>  
                                                </td>
                                                <td style="text-align: right">
                                                    <b><label>5 &#8364/dia </label>
                                                </td>
                                                <td>
                                                    <input type="checkbox" name="condExtra${row.id}" value="1"> 
                                                </td>

                                            </tr>
                                            <tr>
                                                <td style="width:60%">
                                                    <b><label>Deposito Cheio</label></b>  
                                                </td>
                                                <td style="text-align: right">
                                                    <b><label>50&#8364</label>
                                                </td>
                                                <td>
                                                    <input type="checkbox" name="deposito${row.id}" value="1"> 
                                                </td>

                                            </tr>

                                        </table>

                                    </div>
                                </td>
                            </tr>
                        </table>
                    </td>

                    <td  style="text-align: center; vertical-align: middle; width: 18%;"> 
                        <button class="btn btn-large btn-primary" type="submit" name="id" value="${row.id}">Reservar</button>

                    </td>

                </tr>

            </c:forEach> 

        </table>

    </div>
</form>






