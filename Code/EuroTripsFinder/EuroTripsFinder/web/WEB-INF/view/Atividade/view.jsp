<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<h1>Atividades da Cidade</h1>
<div class="pagination pagination-centered">
    <ul>

        <% if (request.getParameter("page").equals("1")) {%>
        <li class="disabled"><a>&laquo;</a></li>
            <%}%>
            <% if (!request.getParameter("page").equals("1")) {
                    int next = new Integer(request.getParameter("page")) - 1;
            %>
        <li><a href="<%= request.getContextPath()%>/Atividade/index?page=<%=next%>">&laquo;</a></li>
        <%}%>
        
        
        <%int i = 1;%>
        <c:forEach var="row" begin="1" end="${nrpages}" >
            
             <% if (request.getParameter("page").equals(String.valueOf(i))) {%>
                <li class="active"><a href="<%= request.getContextPath()%>/Atividade/index?page=${row}"><%=i%></a></li>          
             <%}else{%>
                <li><a href="<%= request.getContextPath()%>/Atividade/index?page=${row}"><%=i%></a></li>
             <%}%>
            
            <%i++;%>
        </c:forEach>
        

        <% if (request.getParameter("page").equals(request.getAttribute("nrpages"))) {%>
            <li class="disabled"><a>&raquo;</a></li>
        <%}%>
        <% if (!request.getParameter("page").equals(request.getAttribute("nrpages"))) {
            int next = new Integer(request.getParameter("page")) + 1;
        %>
            <li><a href="<%= request.getContextPath()%>/Atividade/index?page=<%=next%>">&raquo;</a></li>
        <%}%>
    </ul>
</div>

<table class="table table-hover"> 
                <tr>
                    <td>Nome:</td>
                    <td>Descrição:</td> 
                                                      
                </tr> 
            <c:forEach var="row" items="${atividades}"> 
                <tr>                  
                    <td>${row.nome}</a></td>
                    <td>${row.descricao}</td>         
                </tr>
            </c:forEach> 
</table>