<%-- 
    Document   : updateCoordinate
    Created on : 30/Mai/2013, 15:05:16
    Author     : JorgeMaia
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<h1>Servi�os de Aluguer de Ve�culo dos Utilizadores</h1>

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
        <td>Nome de Utilizador:</td>
        <td>Data/Hora de Levantamento:</td>
        <td>Data/Hora de Entrega:</td>       
        <td>Local Levantamento:</td>
        <td>Local Entrega:</td>
        <td>Op��es:</td>

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