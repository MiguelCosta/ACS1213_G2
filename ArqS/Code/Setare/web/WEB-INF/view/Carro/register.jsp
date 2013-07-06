<%-- 
    Document   : register
    Created on : 5/Jul/2013, 16:26:30
    Author     : miltonnunes52
--%>

<%-- 
    Document   : insertCoordinates
    Created on : 30/Mai/2013, 15:04:57
    Author     : JorgeMaia
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<div class="container" style="max-width: 445px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">

<h1>Inserir Carro</h1>
<hr>
<form action="<%= request.getContextPath()%>/Carro/add" method=post class="form-horizontal">

    

    <div class="control-group">
        <label class="control-label" for="inputNomeLocal">Matrículo</label>
        <div class="controls">
            <input type="text" id="inputNomeCoordenada" name="matricula" placeholder="Matrícula" required="required">
        </div>   
    </div>
    
    <div class="control-group">
        <label class="control-label" for="inputNomeLocal">Descrição</label>
        <div class="controls">
            <input type="text" id="inputNomeCoordenada" name="descricao" placeholder="Descrição" required="required">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputLatitude">Imagem:</label>        
        <div class="controls">
            <input type="text" id="inputLatitude" name="imagem" placeholder="Imagem" required="required">
        </div>   
    </div>
    
     <div class="control-group">
        <label class="control-label" for="inputCidade">Categorias:</label>
            <div class="controls">
                <select id="inputCidadeid" name="categoriaid">
                        <c:forEach var="row" items="${listcategorias}">
                            <option value="${row.id}">${row.nome}</option>
                        </c:forEach>
                    </select>
             </div>   
    </div>
    



    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Registar</button>
        <button type="reset" class="btn">Limpar</button>
        <a class="btn" href="<%= request.getContextPath()%>/Carro/index">Cancelar</a>
    </div>

</form>

</div>
