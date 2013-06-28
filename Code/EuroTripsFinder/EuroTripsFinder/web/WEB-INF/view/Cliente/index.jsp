<%-- 
    Document   : index
    Created on : 1/Jun/2013, 22:35:08
    Author     : miltonnunes52
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<h1>Clientes</h1>

<div class="pagination pagination-centered">
    <ul>

        <% if (request.getParameter("page").equals("1")) {%>
        <li class="disabled"><a>&laquo;</a></li>
            <%}%>
            <% if (!request.getParameter("page").equals("1")) {
                    int next = new Integer(request.getParameter("page")) - 1;
            %>
        <li><a href="<%= request.getContextPath()%>/Cliente/index?page=<%=next%>">&laquo;</a></li>
        <%}%>
        
        
        <%int i = 1;%>
        <c:forEach var="row" begin="1" end="${nrpages}" >
            
             <% if (request.getParameter("page").equals(String.valueOf(i))) {%>
                <li class="active"><a href="<%= request.getContextPath()%>/Cliente/index?page=${row}"><%=i%></a></li>          
             <%}else{%>
                <li><a href="<%= request.getContextPath()%>/Cliente/index?page=${row}"><%=i%></a></li>
             <%}%>
            
            <%i++;%>
        </c:forEach>
        

        <% if (request.getParameter("page").equals(request.getAttribute("nrpages"))) {%>
            <li class="disabled"><a>&raquo;</a></li>
        <%}%>
        <% if (!request.getParameter("page").equals(request.getAttribute("nrpages"))) {
            int next = new Integer(request.getParameter("page")) + 1;
        %>
            <li><a href="<%= request.getContextPath()%>/Cliente/index?page=<%=next%>">&raquo;</a></li>
        <%}%>
    </ul>
</div>
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
