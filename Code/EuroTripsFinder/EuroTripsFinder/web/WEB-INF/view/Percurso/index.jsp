<%-- 
    Document   : index
    Created on : 3/Jun/2013, 9:38:55
    Author     : Miguel
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<h1>Percursos</h1>
<table class="table table-hover"> 
                <tr> 
                    <td>ID:</td>
                    <td>Max Etapas:</td>
                    <td>Numero Etapas</td>
                    <td>Valor:</td>
                    <td>Data inicio:</td>
                    <td>Data fim:</td>
                </tr> 
            <c:forEach var="row" items="${percursos}"> 
                <tr>
                    <td><a href="<%= request.getContextPath()%>/Percurso/view?id=${row.getId()}">${row.getId()}</a></td>
                    <td>${row.getLimiteetapas()}</td>
                    <td>${row.getNumeroetapas()}</td>
                    <td>${row.getValortotal()}</td>
                </tr>
            </c:forEach> 
</table>
