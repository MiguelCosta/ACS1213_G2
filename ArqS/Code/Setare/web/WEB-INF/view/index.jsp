<%-- 
    Document   : index
    Created on : 26/Jun/2013, 22:46:20
    Author     : miltonnunes52
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<h1>Utilizadores

    <a class="btn btn-success" href="<%= request.getContextPath()%>/Utilizador/register" ><i class="icon-plus icon-white"></i></a>
    
</h1>


<div class="pagination pagination-centered">
    <ul>

        <% if (request.getParameter("page").equals("1")) {%>
        <li class="disabled"><a>&laquo;</a></li>
            <%}%>
            <% if (!request.getParameter("page").equals("1")) {
                    int next = new Integer(request.getParameter("page")) - 1;
            %>
        <li><a href="<%= request.getContextPath()%>/Utilizador/index?page=<%=next%>">&laquo;</a></li>
            <%}%>


        <%int i = 1;%>
        <c:forEach var="row" begin="1" end="${nrpages}" >

            <% if (request.getParameter("page").equals(String.valueOf(i))) {%>
            <li class="active"><a href="<%= request.getContextPath()%>/Utilizador/index?page=${row}"><%=i%></a></li>          
                <%} else {%>
            <li><a href="<%= request.getContextPath()%>/Utilizador/index?page=${row}"><%=i%></a></li>
                <%}%>

            <%i++;%>
        </c:forEach>


        <% if (request.getParameter("page").equals(request.getAttribute("nrpages"))) {%>
        <li class="disabled"><a>&raquo;</a></li>
            <%}%>
            <% if (!request.getParameter("page").equals(request.getAttribute("nrpages"))) {
                    int next = new Integer(request.getParameter("page")) + 1;
            %>
        <li><a href="<%= request.getContextPath()%>/Utilizador/index?page=<%=next%>">&raquo;</a></li>
            <%}%>
    </ul>
</div>


<table class="table table-hover" style> 
    <thead> 
    <td>ID</td>    
    <td>Username</td>  
    <td>Email</td>
    <td>Opções</td>
</thead> 
<c:forEach var="row" items="${listutilizadores}"> 
    <tr>
        <td><c:out value="${row.getId()}"/></td>            
        <td><c:out value="${row.getNome()}"/></td>       
        <td><c:out value="${row.getEmail()}"/></td>
        <td><a class="btn btn-info" href="<%= request.getContextPath()%>/Utilizador/edit?id=${row.id}" ><i class="icon-pencil icon-white"></i></a>
    </tr>
</c:forEach> 
</table>

