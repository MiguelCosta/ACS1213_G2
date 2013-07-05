<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 



<div style="float: left; clear: left; position: static;">
    <div class="container" style="max-width: 250px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px;">

        <form class="form-signin">
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
            


        </form>

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
                                ${row.descricao}
                            </td>
                        </tr>
                        <tr style="text-align: center; vertical-align:  bottom;">
                            <td  >
                                <a class="btn btn-large btn-info" >+Extras</a>
                            </td>
                        </tr>
                    </table>
                </td>

                <td  style="text-align: center; vertical-align: middle; width: 18%;"><a class="btn btn-large btn-primary" href="<%= request.getContextPath()%>/ServicoTaxi/registerContinua?id=${row.id}">Reservar !</a></td>
            </tr>
        </c:forEach> 
    </table>
</div>




