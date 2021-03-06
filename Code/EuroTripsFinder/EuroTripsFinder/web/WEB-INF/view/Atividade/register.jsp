<%-- 
    Document   : register
    Created on : 5/Jun/2013, 1:07:29
    Author     : JorgeMaia
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<div class="container" style="max-width: 445px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">

<h1>Adicionar Atividade:</h1>
<hr>

<form action="<%= request.getContextPath()%>/Atividade/add" method=post class="form-horizontal">



    <div class="control-group">
        <label class="control-label" for="inputNomeAtividade">Nome:</label>        
        <div class="controls" id="nomeAtividade">                   
            <input type="text" id="inputNomeAtividade" name="nomeAtividade" required="required" placeholder="Nome da Atividade"/>       
        </div>   
    </div>
    <div class="control-group">
        <label class="control-label" for="inputDescricao">Descri��o:</label>        
        <div class="controls" id="descricao">              
            <TEXTAREA id="inputDescricao" NAME="descricao" ROWS=3 COLS=30 required="required" placeholder="Descri��o"></textarea>
        </div>   
    </div>
    
    
    
                        
    <div class="control-group">
        <label class="control-label" for="inputCidade">Cidades:</label>
            <div class="controls">
                <select id="inputCidadeid" name="cidadeid">
                        <c:forEach var="row" items="${listcidades}">
                            <option value="${row.id}">${row.nome}</option>
                        </c:forEach>
                    </select>
             </div>   
    </div>
            
           
        </table>


    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Registar</button>
        <button type="reset" class="btn">Limpar</button>
        <a class="btn" href="<%= request.getContextPath()%>/Atividade/index">Cancelar</a>
    </div>


</form>

</div>
