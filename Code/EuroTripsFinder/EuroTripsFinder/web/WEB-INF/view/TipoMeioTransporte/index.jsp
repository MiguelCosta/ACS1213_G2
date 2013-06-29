<%-- 
    Document   : index
    Created on : 30/Mai/2013, 22:55:13
    Author     : miltonnunes52
--%>
 

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<h1>Tipos de Meio de Transporte
    <a class="btn btn-success" href="<%= request.getContextPath()%>/TipoMeioTransporte/register" ><i class="icon-plus icon-white"></i></a>
</h1>

<div class="pagination pagination-centered">
    <ul>

        <% if (request.getParameter("page").equals("1")) {%>
        <li class="disabled"><a>&laquo;</a></li>
            <%}%>
            <% if (!request.getParameter("page").equals("1")) {
                    int next = new Integer(request.getParameter("page")) - 1;
            %>
        <li><a href="<%= request.getContextPath()%>/TipoMeioTransporte/index?page=<%=next%>">&laquo;</a></li>
        <%}%>
        
        
        <%int i = 1;%>
        <c:forEach var="row" begin="1" end="${nrpages}" >
            
             <% if (request.getParameter("page").equals(String.valueOf(i))) {%>
                <li class="active"><a href="<%= request.getContextPath()%>/TipoMeioTransporte/index?page=${row}"><%=i%></a></li>          
             <%}else{%>
                <li><a href="<%= request.getContextPath()%>/TipoMeioTransporte/index?page=${row}"><%=i%></a></li>
             <%}%>
            
            <%i++;%>
        </c:forEach>
        

        <% if (request.getParameter("page").equals(request.getAttribute("nrpages"))) {%>
            <li class="disabled"><a>&raquo;</a></li>
        <%}%>
        <% if (!request.getParameter("page").equals(request.getAttribute("nrpages"))) {
            int next = new Integer(request.getParameter("page")) + 1;
        %>
            <li><a href="<%= request.getContextPath()%>/TipoMeioTransporte/index?page=<%=next%>">&raquo;</a></li>
        <%}%>
    </ul>
</div>
<table class="table table-hover"> 
                <tr> 
                    <td>ID:</td>
                    <td>Nome:</td>
                    <td>Op��es:</td>
                </tr> 
            <c:forEach var="row" items="${listmeiostransporte}"> 
                <tr>
                    <td>${row.id}</td>
                    <td>${row.nome}</td>  
                    <td><a class="btn btn-info" href="<%= request.getContextPath()%>/TipoMeioTransporte/view?id=${row.id}" ><i class="icon-pencil icon-white"></i></a>
                    <a class="btn btn-danger" href="<%= request.getContextPath()%>/TipoMeioTransporte/delete?id=${row.id}" ><i class="icon-remove icon-white"></i></a></td>
                </tr>
            </c:forEach> 
</table>