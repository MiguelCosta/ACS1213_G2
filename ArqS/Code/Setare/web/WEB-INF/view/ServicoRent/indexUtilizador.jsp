<%-- 
    Document   : updateCoordinate
    Created on : 30/Mai/2013, 15:05:16
    Author     : JorgeMaia
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<h1>Serviços de Aluguer de Veículo</h1>

<table class="table table-hover"> 
    <tr>
        <td>Data/Hora de Levantamento:</td>
        <td>Data/Hora de Entrega:</td>       
        <td>Local Levantamento:</td>
        <td>Local Entrega:</td>
        <td>Opções:</td>

    </tr> 
    <c:forEach var="row" items="${servicosRent}"> 
        <tr>                  
            <td>${row.getDataHoraPartidaFormatada()}</td>
            <td>${row.getDataHoraChegadaFormatada()}</td> 
            <td>${row.getLocalChegadaid().getNome()}</td> 
            <td>${row.getLocalPartidaid().getNome()}</td> 
            <td><a class="btn btn-info" href="/Setare/ServicoRent/view?id=${row.getId()}" ><i class="icon-pencil icon-white"></i></a>
            </td>
        </tr>
    </c:forEach> 
</table>