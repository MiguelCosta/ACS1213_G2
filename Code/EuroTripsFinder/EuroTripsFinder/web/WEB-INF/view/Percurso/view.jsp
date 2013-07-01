<%-- 
    Document   : view
    Created on : 3/Jun/2013, 9:39:21
    Author     : Miguel
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<h1>Percurso</h1>

ID: ${percurso.getId()}<br/>
Nome: ${percurso.getNome()}<br/>
Número máximo de etapas: ${percurso.getLimiteetapas()}<br/>


<h2>Etapas 
    <a class="btn btn-success" href="<%= request.getContextPath()%>/Etapa/register" ><i class="icon-plus icon-white"></i></a>
</h2>

<table class="table table-hover" style> 
    <thead> 
    <td>ID</td>
    <td>Local Partida</td>
    <td>Hora Partida</td>
    <td>Local Chegada</td>
    <td>Hora Chegada</td>
</thead> 
<c:forEach var="row" items="${percurso.getEtapaCollection()}"> 
    <tr>
        <td><c:out value="${row.getId()}"/></td>            
        <td><c:out value="${row.getLocalparageminicialid()}"/></td>
        <td><c:out value="${row.getDatapartida()}"/></td>
        <td><c:out value="${row.getLocalparagemdestinoid()}"/></td>
        <td><c:out value="${row.getDatachegada()}"/></td>
    </tr>
</c:forEach> 
</table>




