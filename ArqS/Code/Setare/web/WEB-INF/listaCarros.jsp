<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="listaCarros" name="listaCarros" class="table table-hover"> 

    <c:forEach var="row" items="${listacarros}"> 
        <tr>
            <td style="width: 25%;"><img src="${row.imagem}" width="200" height="200"></td>  

            <td>
                <table style="width: 100%; height: 100%;">
                    <tr>
                        <td>
                            ${row.descricao} <br> <b> jyjyyjt Preço por Hora:</b>${precoCategoria}&#8364<br><b>Preço do Depósito:</b>${row.getCategoriaid().getPrecoDeposito()}&#8364
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
            <td  style="text-align: center; vertical-align: middle; width: 18%;"> 
                <img src="/Setare/img/logo-paypal.png"/>
            </td>

        </tr>

    </c:forEach> 
</table>