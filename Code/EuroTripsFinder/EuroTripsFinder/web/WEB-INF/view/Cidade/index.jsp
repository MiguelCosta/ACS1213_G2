<%-- 
    Document   : index
    Created on : 4/Jun/2013, 16:46:13
    Author     : JorgeMaia
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<h1>Lista de Cidades</h1>
<table class="table table-hover"> 
    <tr>
        <td><b>Nome:</b></td>
        <td><b>País:</b></td> 
        <td><b>Região:</b></td>
        <td></td>

    </tr> 
    <c:forEach var="row" items="${resultado}"> 
        <tr>            
            <td> <a href="<%= request.getContextPath()%>/Cidade/view?id=${row.id}">${row.nome}</a></td>
            <td>${row.pais}</td> 
            <td>${row.regiao}</td>
            <td><a href="<%= request.getContextPath()%>/Cidade/Atividade/view?id=${row.nome}">Ver Atividades</a></td>  
        </tr>
    </c:forEach> 
</table>
