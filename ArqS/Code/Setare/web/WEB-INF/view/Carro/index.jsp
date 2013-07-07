<%-- 
    Document   : updateCoordinate
    Created on : 30/Mai/2013, 15:05:16
    Author     : JorgeMaia
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<h1>Carros
    <a class="btn btn-success" href="<%= request.getContextPath()%>/Carro/register" ><i class="icon-plus icon-white"></i></a>
</h1>


<div class="pagination pagination-centered">
    <ul>

        <% if (request.getParameter("page").equals("1")) {%>
        <li class="disabled"><a>&laquo;</a></li>
            <%}%>
            <% if (!request.getParameter("page").equals("1")) {
                    int next = new Integer(request.getParameter("page")) - 1;
            %>
        <li><a href="<%= request.getContextPath()%>/Carro/index?page=<%=next%>">&laquo;</a></li>
        <%}%>
        
        
        <%int i = 1;%>
        <c:forEach var="row" begin="1" end="${nrpages}" >
            
             <% if (request.getParameter("page").equals(String.valueOf(i))) {%>
                <li class="active"><a href="<%= request.getContextPath()%>/Carro/index?page=${row}"><%=i%></a></li>          
             <%}else{%>
                <li><a href="<%= request.getContextPath()%>/Carro/index?page=${row}"><%=i%></a></li>
             <%}%>
            
            <%i++;%>
        </c:forEach>
        

        <% if (request.getParameter("page").equals(request.getAttribute("nrpages"))) {%>
            <li class="disabled"><a>&raquo;</a></li>
        <%}%>
        <% if (!request.getParameter("page").equals(request.getAttribute("nrpages"))) {
            int next = new Integer(request.getParameter("page")) + 1;
        %>
            <li><a href="<%= request.getContextPath()%>/Carro/index?page=<%=next%>">&raquo;</a></li>
        <%}%>
    </ul>
</div>
    
<table class="table table-hover"> 
    <tr>
        <td><h4>Matrícula:</h4></td>
        <td><h4>Descrição:</h4></td> 
        <td><h4>Imagem:</h4></td>
        <td><h4>Categoria:</h4></td>
        <td><h4>Opções:</h4></td>
    </tr> 
    <c:forEach var="row" items="${resultado}"> 
        <tr>                  
            <td style="vertical-align: middle;">${row.matricula}</td>
             <td style="vertical-align: middle;">${row.descricao}</td>
             <td style="vertical-align: middle;"><img src="${row.imagem}" width="200" height="150" /></td> 
             <td style="vertical-align: middle;">${row.getCategoriaid().getNome()}</td> 
            <td style="vertical-align: middle;"><a class="btn btn-info" href="<%= request.getContextPath()%>/Carro/view?id=${row.id}" ><i class="icon-pencil icon-white"></i></a>
           </td>
        </tr>
    </c:forEach> 
</table>
