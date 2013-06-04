<%-- 
    Document   : view
    Created on : 4/Jun/2013, 18:26:49
    Author     : JorgeMaia
--%>

<form action="<%= request.getContextPath()%>/Cidade/update" method=post class="form-horizontal">
    <div style="float:left">
        <div class="control-group">
            <label class="control-label" for="inputNome">Nome da Cidade:</label>        
            <div class="controls">
                <div>
                    <input type="text" name="nomeCidade" value="${cidade.getNome()}"/>
                </div>

            </div>   
        </div>    

        <div class="control-group">
            <label class="control-label" for="inputPais">País:</label>        
            <div class="controls" id="pais">                   
                <input type="text" id="inputPais" name="pais" value="${cidade.getPais()}"/>       
            </div>   
        </div>
        <div class="control-group">
            <label class="control-label" for="inputDistrito">Distrito:</label>        
            <div class="controls" id="distrito">                   
                <input type="text" id="inputDistrito" name="distrito" required="required" value="${cidade.getDistrito()}"/>       
            </div>   
        </div>
        <div class="control-group">
            <label class="control-label" for="inputRegiao">Região:</label>        
            <div class="controls" id="regiao">                   
                <input type="text" id="inputRegiao" name="regiao" required="required" value="${cidade.getRegiao()}" />       
            </div>   
        </div>

        <div class="control-group">
            <label class="control-label" for="inputLatitude">Latitude:</label>        
            <div class="controls" id="myLatitude">                   
                <input type="text" id="inputLatitude" name="latitude" required="required" value="${cidade.coordenadaid.latitude}" />
            </div>   
        </div>

        <div class="control-group">
            <label class="control-label" for="inputLongitude">Longitude:</label>
            <div class="controls" id="myLongitude">
                <input type="text" id="inputLongitude" name="longitude" required="required" value="${cidade.coordenadaid.longitude}" />
            </div>   
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Registar</button>
            <button type="button" class="btn">Limpar</button>
        </div>

    </div>
   
</form>