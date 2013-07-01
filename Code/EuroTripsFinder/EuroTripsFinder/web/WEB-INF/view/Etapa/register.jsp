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

Percurso: ${percurso.getId()} - ${percurso.getNome()}

<form action="<%= request.getContextPath()%>/Etapa/add" method=post class="form-horizontal">

    <div class="control-group">
        <label class="control-label" for="inputNomeEtapa">Nova Etapa</label>
        <div class="controls">
            <input type="text" id="inputNomeEtapa" name="nomeEtapa" placeholder="Nome" required="required">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputLocalInicial">Local Inicial</label>
        <div class="controls">
            <select id="inputLocalInicial" name="localInicial">
                <c:forEach var="row" items="${locais}">
                    <option value="${row.id}">${row.nome}</option>
                </c:forEach>
            </select><button onclick="popUpCal()">Pontos de Interesse</button> 

        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputLocalFinal">Local Final</label>
        <div class="controls">
            <select id="inputLocalFinal" name="localFinal">
                <c:forEach var="row" items="${locais}">
                    <option value="${row.id}">${row.nome}</option>
                </c:forEach>
            </select>
        </div>   
    </div>

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
                   title="yyyy-MM-dd">
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
                   title="yyyy-MM-dd">
        </div>   
    </div>


    <div class="control-group">  
        <label class="control-label" for="inputMeioTransporte">Meio de Transporte</label>
        <div class="controls">
            <select id="inputMeioTransporte" name="meioTransporte">
                <c:forEach var="row" items="${listmeiostransporte}">
                    <option value="${row.id}">${row.nome}</option>
                </c:forEach>
            </select>  
        </div>   
    </div>

    <div class="control-group">  
        <label class="control-label" for="inputCusto">Custo em Euro</label>
        <div class="controls">
            <input type="text" id="inputCusto" name="custo" placeholder="custo" required="required" pattern="\d*">
        </div>   
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Registar</button>
        <button type="button" class="btn">Limpar</button>
    </div>

</form>