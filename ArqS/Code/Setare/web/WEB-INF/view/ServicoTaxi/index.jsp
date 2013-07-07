<%-- 
    Document   : updateCoordinate
    Created on : 30/Mai/2013, 15:05:16
    Author     : JorgeMaia
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<h1>Serviços de Taxi
    </h1>


<div class="pagination pagination-centered">
    <ul>

        <% if (request.getParameter("page").equals("1")) {%>
        <li class="disabled"><a>&laquo;</a></li>
            <%}%>
            <% if (!request.getParameter("page").equals("1")) {
                    int next = new Integer(request.getParameter("page")) - 1;
            %>
        <li><a href="<%= request.getContextPath()%>/ServicosTaxi/index?page=<%=next%>">&laquo;</a></li>
        <%}%>
        
        
        <%int i = 1;%>
        <c:forEach var="row" begin="1" end="${nrpages}" >
            
             <% if (request.getParameter("page").equals(String.valueOf(i))) {%>
                <li class="active"><a href="<%= request.getContextPath()%>/ServicosTaxi/index?page=${row}"><%=i%></a></li>          
             <%}else{%>
                <li><a href="<%= request.getContextPath()%>/ServicosTaxi/index?page=${row}"><%=i%></a></li>
             <%}%>
            
            <%i++;%>
        </c:forEach>
        

        <% if (request.getParameter("page").equals(request.getAttribute("nrpages"))) {%>
            <li class="disabled"><a>&raquo;</a></li>
        <%}%>
        <% if (!request.getParameter("page").equals(request.getAttribute("nrpages"))) {
            int next = new Integer(request.getParameter("page")) + 1;
        %>
            <li><a href="<%= request.getContextPath()%>/ServicosTaxi/index?page=<%=next%>">&raquo;</a></li>
        <%}%>
    </ul>
</div>
    
<table class="table table-hover"> 
    <tr>
        <td>Utilizador:</td>
        <td>Partida:</td> 
        <td>Chegada:</td>
        <td>Data:</td>
        <td>Bagagem:</td>
        <td>Passageiros:</td>
        <td>Taxi:</td>
        <td>Opções:</td>
    </tr> 
    <c:forEach var="row" items="${resultado}"> 
        <tr>                  
            <td>${row.getUtilizadorid().getNome()}</td>
            <td>${row.getLocalpartidaid().getNome()}</td> 
            <td>${row.getLocalchegadaid().getNome()}</td> 
            <td>${row.getDatapartida()}</td> 
            <td>${row.getBagagem()}</td> 
            <td>${row.getPassageiros()}</td>
            <td>${row.getCodigotaxi()}</td> 
            <td><a class="btn btn-info" href="/Setare/ServicoTaxi/view?id=${row.getId()}" ><i class="icon-pencil icon-white"></i></a>
           </td>
        </tr>
    </c:forEach> 
</table>
