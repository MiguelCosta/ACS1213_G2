<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<script>
    function popUpCal()
    {
        var url = "pontoInteresse";
        var width = 620;
        var height = 400;
        var left = parseInt((screen.availWidth / 2) - (width / 2));
        var top = parseInt((screen.availHeight / 2) - (height / 2));
        var windowFeatures = "width=" + width + ",height=" + height +
                ",status,resizable,left=" + left + ",top=" + top +
                "screenX=" + left + ",screenY=" + top + ",scrollbars=yes";
        window.open(url, "subWind", windowFeatures, "POS");
    }
</script>

<h1>Adicionar Nova Etapa</h1>

<form action="<%= request.getContextPath()%>/Etapa/add" method=post class="form-horizontal">

    <div class="control-group">
        <label class="control-label" for="inputNomeEtapa">Nova Etapa</label>
        <div class="controls">
            <input type="text" id="inputNomeEtapa" style="height: 30px;" name="nomeEtapa" placeholder="Nome" required="required">
        </div>   
    </div>

    <h4>Origem</h4>

    <div class="control-group">  
        <label class="control-label" for="inputMeioTransporte">País</label>
        <div class="controls">
            <select id="inputMeioTransporte" name="meioTransporte" required="required">
                <option value="-1">Portugal</option>
            </select>  
        </div>   
    </div>

    <div class="control-group">  
        <label class="control-label" for="inputMeioTransporte">Cidade</label>
        <div class="controls">
            <select id="inputMeioTransporte" name="meioTransporte" required="required">
                <option value="-1">Maia</option>
            </select>  
        </div>   
    </div>
    
    <div class="control-group">
        <label class="control-label" for="inputLocalInicial">Local</label>
        <div class="controls">
            <select id="inputLocalInicial" name="localInicial">
                <c:forEach var="row" items="${locais}">
                    <option value="${row.id}">${row.nome}</option>
                </c:forEach>
            </select><button onclick="popUpCal()">Pontos de Interesse</button> 

        </div>   
    </div>
    
    <h4>Destino</h4>

    <div class="control-group">  
        <label class="control-label" for="inputMeioTransporte">País</label>
        <div class="controls">
            <select id="inputMeioTransporte" name="meioTransporte" required="required">
                <option value="-1">Portugal</option>
            </select>  
        </div>   
    </div>

    <div class="control-group">  
        <label class="control-label" for="inputMeioTransporte">Cidade</label>
        <div class="controls">
            <select id="inputMeioTransporte" name="meioTransporte" required="required">
                <option value="-1">Maia</option>
            </select>  
        </div>   
    </div>
    
    <div class="control-group">
        <label class="control-label" for="inputLocalFinal">Local</label>
        <div class="controls">
            <select id="inputLocalFinal" name="localFinal" required="required">
                <c:forEach var="row" items="${locais}">
                    <option value="${row.id}">${row.nome}</option>
                </c:forEach>
            </select>
        </div>   
    </div>
    
    <h4>Filtros</h4>

    <div class="control-group">
        <label class="control-label" for="inputDataInicial">Data Inicial</label>
        <div class="controls">
            <input type="date" 
                   id="inputDataInicial" 
                   name="dataInicial" 
                   placeholder="yyyy-MM-dd" 
                   min="1900-01-01" 
                   max="2020-01-01" 
                   pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" 
                   title="yyyy-MM-dd"
                   required="required">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputDataFinal">Data Final</label>
        <div class="controls">
            <input type="date" 
                   id="inputDataFinal" 
                   name="dataFinal" 
                   placeholder="yyyy-MM-dd" 
                   min="1900-01-01" 
                   max="2020-01-01" 
                   pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" 
                   title="yyyy-MM-dd"
                   required="required">
        </div>   
    </div>

    <div class="control-group">  
        <label class="control-label" for="inputMeioTransporte">Meio de Transporte</label>
        <div class="controls">
            <select id="inputMeioTransporte" name="meioTransporte" required="required">
                <option value="-1">Qualquer</option>
                <option value="0">Elétrico</option>
                <option value="1">Metro</option>
                <option value="2">Autocarros inter-cidades</option>
                <option value="3">Autocarros</option>
                <option value="4">Ferry-boat</option>
                <option value="5">Teleférico</option>
                <option value="7">Funicular</option>
                <option value="8">Avião</option>
            </select>  
        </div>   
    </div>

    <div class="control-group">  
        <label class="control-label" for="inputCusto">Custo em Euro</label>
        <div class="controls">
            <input type="number" style="height: 30px;" id="inputCusto" name="custo" placeholder="custo" required="required" min="0" max="100" value="0"> 0 - não definido
        </div>   
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Registar</button>
        <button type="button" class="btn">Limpar</button>
    </div>

</form>