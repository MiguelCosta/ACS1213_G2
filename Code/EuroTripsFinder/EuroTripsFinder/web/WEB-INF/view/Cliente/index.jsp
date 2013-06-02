<%-- 
    Document   : index
    Created on : 1/Jun/2013, 22:35:08
    Author     : miltonnunes52
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<h1>Clientes</h1>
<table class="table table-hover"> 
                <tr> 
                    <td>ID:</td>
                    <td>Nome:</td>
                    <td>Username:</td>
                    <td>Morada:</td>
                    <td>Email:</td>
                    <td>Nif:</td>
                    <td>Contacto:</td>
                </tr> 
            <c:forEach var="row" items="${listclientes}"> 
                <tr>
                    <td><a href="<%= request.getContextPath()%>/Cliente/view?id=${row.id}">${row.id}</a></td>
                    <td>${row.utilizador.nome}</td>
                    <td>${row.utilizador.username}</td>
                    <td>${row.utilizador.morada}</td>
                    <td>${row.utilizador.email}</td>
                    <td>${row.nif}</td>
                    <td>${row.contacto}</td>
                </tr>
            </c:forEach> 
</table>
