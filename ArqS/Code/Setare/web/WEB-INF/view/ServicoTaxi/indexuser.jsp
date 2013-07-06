<%-- 
    Document   : updateCoordinate
    Created on : 30/Mai/2013, 15:05:16
    Author     : JorgeMaia
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<h1>Serviços de Taxi</h1>




<table class="table table-hover"> 
    <tr>
        <td>Partida:</td>
        <td>Chegada:</td>       
        <td>Data:</td>
        <td>Bagagem:</td>
        <td>Passageiros:</td>
        <td>Taxi:</td>
        <td>Opções:</td>

    </tr> 
    <c:forEach var="row" items="${resultadostaxi}"> 
        <tr>                  
            <td>${row.getLocalpartidaid().getNome()}</td> 
            <td>${row.getLocalchegadaid().getNome()}</td> 
            <td>${row.getDatapartida()}</td> 
            <td>${row.getBagagem()}</td>
            <td>${row.getPassageiros()}</td>
            <td>${row.getCodigotaxi()}</td> 
            <td><a class="btn btn-info" href="/Setare/ServicoTaxi/view?id=${row.getId()}" ><i class="icon-pencil icon-white"></i></a>
            <a class="btn btn-danger" href="#" ><i class="icon-remove icon-white"></i></a></td>
        </tr>
    </c:forEach> 
</table>