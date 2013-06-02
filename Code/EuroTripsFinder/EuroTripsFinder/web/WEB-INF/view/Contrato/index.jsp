<%-- 
    Document   : index
    Created on : 2/Jun/2013, 2:09:30
    Author     : miltonnunes52
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<h1>Contratos</h1>
<table class="table table-hover"> 
                <tr> 
                    <td>ID:</td>
                    <td>Valor:</td>
                    <td>Data Início:</td>
                    <td>Data Fim:</td>
                </tr> 
            <c:forEach var="row" items="${listcontratos}"> 
                <tr>
                    <td><a href="<%= request.getContextPath()%>/Contrato/view?id=${row.id}">${row.id}</a></td>
                    <td>${row.getValor()}</td>
                    <td>${row.getDatainicioString()}</td>
                    <td>${row.getDatafimString()}</td>
                </tr>
            </c:forEach> 
</table>