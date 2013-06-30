<%-- 
    Document   : index
    Created on : 4/Jun/2013, 16:46:13
    Author     : JorgeMaia
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<h1>Lista de Cidades
    <a class="btn btn-success" href="<%= request.getContextPath()%>/Cidade/register" ><i class="icon-plus icon-white"></i></a>
</h1>

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
        <td><b>Pa�s:</b></td> 
        <td><b>Regi�o:</b></td>
        <td>Op��es:</td>

    </tr> 
    <c:forEach var="row" items="${resultado}"> 
        <tr>            
            <td>${row.nome}</td>
            <td>${row.pais}</td> 
            <td>${row.regiao}</td>
            <td><a class="btn btn-info" href="<%= request.getContextPath()%>/Atividade/view?id=${row.nome}">Atividades</a>
            <a class="btn btn-info" href="<%= request.getContextPath()%>/Cidade/view?id=${row.id}" ><i class="icon-pencil icon-white"></i></a>
            <a class="btn btn-danger" href="<%= request.getContextPath()%>/Cidade/delete?id=${row.id}" ><i class="icon-remove icon-white"></i></a></td>

        </tr>
    </c:forEach> 
</table>
