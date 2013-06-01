<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<h1>Pesquisar Atividades por Cidade</h1>
<form action="/EuroTripsFinder/Atividade/view" method=post>
    <div id="loginBox">
        <p><strong>Cidade:</strong>
            <input type="text" size="20" name="nomeCidade"></p>
            <input type="submit" value="Procurar">
    </div>
</form>

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