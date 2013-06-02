<%-- 
    Document   : register
    Created on : 2/Jun/2013, 2:09:24
    Author     : miltonnunes52
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<h1>Adicionar Contrato</h1>

<form action="<%= request.getContextPath()%>/Contrato/add" method=post class="form-horizontal">
 
    <div class="control-group">
        <label class="control-label" for="inputValor">Valor</label>
        <div class="controls">
            <input type="text" id="inputNome" name="valor" placeholder="Valor" required="required">
        </div>   
    </div>
    <div class="control-group">
        <label class="control-label" for="inputDatainicio">Data Início</label>
        <div class="controls">
            <input type="date" 
                   id="inputDatainicio" 
                   name="datainicio" 
                   placeholder="yyyy-MM-dd" 
                   min="1900-01-01" 
                   max="2020-01-01" 
                   pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" 
                   title="yyyy-MM-dd">
        </div>   
    </div>
    <div class="control-group">
        <label class="control-label" for="inputDatafim">Data Início</label>
        <div class="controls">
            <input type="date" 
                   id="inputDatafim" 
                   name="datafim" 
                   placeholder="yyyy-MM-dd" 
                   min="1900-01-01" 
                   max="2020-01-01" 
                   pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" 
                   title="yyyy-MM-dd">
        </div>   
    </div>
    
    <div class="control-group">
        <label class="control-label" for="inputCliente">Cliente</label>
            <div class="controls">
                <select id="inputClienteid" name="clienteid">
                        <c:forEach var="row" items="${listclientes}">
                            <option>${row.id}</option>
                        </c:forEach>
                    </select>
             </div>   
    </div>
    
    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Registar</button>
        <button type="button" class="btn">Limpar</button>
    </div>

</form>
