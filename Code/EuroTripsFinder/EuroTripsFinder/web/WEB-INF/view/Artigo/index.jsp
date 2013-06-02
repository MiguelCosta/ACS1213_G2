<%-- 
    Document   : index
    Created on : 2/Jun/2013, 2:08:45
    Author     : miltonnunes52
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<h1>Artigos Publicitários</h1>
<table class="table table-hover"> 
                <tr> 
                    <td>ID:</td>
                    <td>Nome:</td>
                    <td>Conteúdo:</td>
                </tr> 
            <c:forEach var="row" items="${listartigos}"> 
                <tr>
                    <td><a href="<%= request.getContextPath()%>/Artigo/view?id=${row.id}">${row.id}</a></td>
                    <td>${row.nome}</td>
                    <td>${row.conteudo}</td>
                </tr>
            </c:forEach> 
</table>