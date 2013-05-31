<%-- 
    Document   : updateCoordinate
    Created on : 30/Mai/2013, 15:05:16
    Author     : JorgeMaia
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<h1>Coordenadas Registadas</h1>
<table class="table table-hover"> 
                <tr> 
                    <td>ID</td>
                    <td>Nome:</td>
                    <td>Latitude:</td> 
                    <td>Longitude:</td>                                      
                </tr> 
            <c:forEach var="row" items="${resultado}"> 
                <tr>
                    <td><a href="<%= request.getContextPath()%>/Coordenada/view?id=<c:out value="${row.id}"/>"><c:out value="${row.id}"/></a></td>
                    <td><c:out value="${row.nome}"/></td>
                    <td><c:out value="${row.latitude}"/></td> 
                    <td><c:out value="${row.longitude}"/></td>                     
                </tr>
            </c:forEach> 
</table>