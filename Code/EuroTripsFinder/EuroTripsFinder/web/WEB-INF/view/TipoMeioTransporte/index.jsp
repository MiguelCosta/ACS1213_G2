<%-- 
    Document   : index
    Created on : 30/Mai/2013, 22:55:13
    Author     : miltonnunes52
--%>
 

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<h1>Tipos de Meio de Transporte</h1>
<table class="table table-hover"> 
                <tr> 
                    <td>Nome:</td>
                </tr> 
            <c:forEach var="row" items="${listmeiostransporte}"> 
                <tr>
                    <td><a href="<%= request.getContextPath()%>/TipoMeioTransporte/view?id=<c:out value="${row.id}"/>"><c:out value="${row.id}"/></a></td>
                    <td><c:out value="${row.nome}"/></td>                  
                </tr>
            </c:forEach> 
</table>