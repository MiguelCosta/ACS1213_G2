<%-- 
    Document   : index
    Created on : 1/Jun/2013, 16:51:11
    Author     : Miguel
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<h1>Viagens</h1>

<table class="table table-hover"> 
    <thead> 
    <td>ID</td>
    <td>Nome</td>
    <td>Data Inicio</td>
    <td>Data Fim</td>
    <td> Percurso</td>
</thead> 
<c:forEach var="row" items="${viagens}"> 
    <tr>
        <td><a href="<%= request.getContextPath()%>/Viagem/view?id=${row.getId()}">${row.getId()}</a></td>
        <td>${row.getNome()}</td>
        <td>${row.getDatainicio()}</td>
        <td>${row.getDatafim()}</td>
        <td>${row.getPercursoid()}</td>
    </tr>
</c:forEach> 
</table>
