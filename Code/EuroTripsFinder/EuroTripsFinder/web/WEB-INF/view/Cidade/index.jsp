<%-- 
    Document   : index
    Created on : 4/Jun/2013, 16:46:13
    Author     : JorgeMaia
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<h1>Lista de Cidades</h1>

<div class="pagination pagination-centered">
    <ul>

        <% if (request.getParameter("page").equals("1")) {%>
        <li class="disabled"><a>&laquo;</a></li>
            <%}%>
            <% if (!request.getParameter("page").equals("1")) {
                    int next = new Integer(request.getParameter("page")) - 1;
            %>
        <li><a href="<%= request.getContextPath()%>/Cidade/index?page=<%=next%>">&laquo;</a></li>
        <%}%>
        
        
        <%int i = 1;%>
        <c:forEach var="row" begin="1" end="${nrpages}" >
            
             <% if (request.getParameter("page").equals(String.valueOf(i))) {%>
                <li class="active"><a href="<%= request.getContextPath()%>/Cidade/index?page=${row}"><%=i%></a></li>          
             <%}else{%>
                <li><a href="<%= request.getContextPath()%>/Cidade/index?page=${row}"><%=i%></a></li>
             <%}%>
            
            <%i++;%>
        </c:forEach>
        

        <% if (request.getParameter("page").equals(request.getAttribute("nrpages"))) {%>
            <li class="disabled"><a>&raquo;</a></li>
        <%}%>
        <% if (!request.getParameter("page").equals(request.getAttribute("nrpages"))) {
            int next = new Integer(request.getParameter("page")) + 1;
        %>
            <li><a href="<%= request.getContextPath()%>/Cidade/index?page=<%=next%>">&raquo;</a></li>
        <%}%>
    </ul>
</div>
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
            <td><a href="<%= request.getContextPath()%>/Atividade/view?id=${row.nome}">Ver Atividades</a></td>  
        </tr>
    </c:forEach> 
</table>
