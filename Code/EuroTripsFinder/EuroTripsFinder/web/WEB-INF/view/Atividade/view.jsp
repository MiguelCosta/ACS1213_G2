<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<h1>Atividades da Cidade</h1>


<table class="table table-hover"> 
                <tr>
                    <td>Nome:</td>
                    <td>Descrição:</td> 
                                                      
                </tr> 
            <c:forEach var="row" items="${atividades}"> 
                <tr>                  
                    <td> ${row.nome}</a></td>
                    <td>${row.descricao}</td> 
                                        
                </tr>
            </c:forEach> 
</table>