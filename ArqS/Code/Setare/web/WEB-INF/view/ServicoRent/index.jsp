<%-- 
    Document   : updateCoordinate
    Created on : 30/Mai/2013, 15:05:16
    Author     : JorgeMaia
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<h1>Serviços de Aluguer de Veículo dos Utilizadores</h1>


<a class="btn-small btn-info" href="/Setare/ServicoRent/indexUtilizador">Meus Pedidos</a>
<div class="pagination pagination-centered">
    <ul>

        <% if (request.getParameter("page").equals("1")) {%>
        <li class="disabled"><a>&laquo;</a></li>
            <%}%>
            <% if (!request.getParameter("page").equals("1")) {
                    int next = new Integer(request.getParameter("page")) - 1;
            %>
        <li><a href="<%= request.getContextPath()%>/ServicoRent/index?page=<%=next%>">&laquo;</a></li>
        <%}%>
        
        
        <%int i = 1;%>
        <c:forEach var="row" begin="1" end="${nrpages}" >
            
             <% if (request.getParameter("page").equals(String.valueOf(i))) {%>
                <li class="active"><a href="<%= request.getContextPath()%>/ServicoRent/index?page=${row}"><%=i%></a></li>          
             <%}else{%>
                <li><a href="<%= request.getContextPath()%>/ServicoRent/index?page=${row}"><%=i%></a></li>
             <%}%>
            
            <%i++;%>
        </c:forEach>
        

        <% if (request.getParameter("page").equals(request.getAttribute("nrpages"))) {%>
            <li class="disabled"><a>&raquo;</a></li>
        <%}%>
        <% if (!request.getParameter("page").equals(request.getAttribute("nrpages"))) {
            int next = new Integer(request.getParameter("page")) + 1;
        %>
            <li><a href="<%= request.getContextPath()%>/ServicoRent/index?page=<%=next%>">&raquo;</a></li>
        <%}%>
    </ul>
</div>
<table class="table table-hover"> 
    <tr>
        <td><h4>Nome de Utilizador:</h4></td>
        <td><h4>Data/Hora de Levantamento:</h4></td>
        <td><h4>Data/Hora de Entrega:</h4></td>       
        <td><h4>Local Levantamento:</h4></td>
        <td><h4>Local Entrega:</h4></td>
        <td><h4>Opções:</h4></td>

    </tr> 
    <c:forEach var="row" items="${servicosRent}"> 
        <tr>
            <td>${row.getUtilizadorid().getNome()}</td>
            <td>${row.getDataHoraPartidaFormatada()}</td>
            <td>${row.getDataHoraChegadaFormatada()}</td> 
            <td>${row.getLocalChegadaid().getNome()}</td> 
            <td>${row.getLocalPartidaid().getNome()}</td> 
            <td><a class="btn btn-info" href="/Setare/ServicoRent/view?id=${row.getId()}" ><i class="icon-pencil icon-white"></i></a>
            </td>
        </tr>
    </c:forEach> 
</table>