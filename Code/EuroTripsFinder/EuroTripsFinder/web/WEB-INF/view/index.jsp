<%-- 
    Document   : index
    Created on : 26/Jun/2013, 22:46:20
    Author     : miltonnunes52
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<h1>Utilizadores</h1>

<table class="table table-hover"> 
    <thead> 
    <td>ID</td>
    <td>Nome</td>
    <td>Username</td>
    <td>Tipo</td>
    <td>Data Registo</td>
</thead> 
<c:forEach var="row" items="${listutilizadores}"> 
    <tr>
        <td><a href="<%=request.getContextPath()%>/Utilizador/edit?id=${row.id}"><c:out value="${row.getId()}"/></a></td>            
        <td><c:out value="${row.getNome()}"/></td>
        <td><c:out value="${row.getUsername()}"/></td>
        <td><c:out value="${row.getFuncao()}"/></td>
        <td><c:out value="${row.getDataregisto()}"/></td>
    </tr>
</c:forEach> 
</table>

