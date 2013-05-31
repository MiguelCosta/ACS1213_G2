<%-- 
    Document   : insertCoordinates
    Created on : 30/Mai/2013, 15:04:57
    Author     : JorgeMaia
--%>

<h1>Inserir Nova Coordenada</h1>

<form action="<%= request.getContextPath()%>/Coordenada/add" method=post class="form-horizontal">

    

    <div class="control-group">
        <label class="control-label" for="inputNomeCoordenada">Nome</label>
        <div class="controls">
            <input type="text" id="inputNomeCoordenada" name="nomecoordenada" placeholder="Nome" required="required">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputLatitude">Latitude:</label>        
        <div class="controls">
            <input type="text" id="inputLatitude" name="latitude" placeholder="Latitude">
        </div>   
    </div>
    
     <div class="control-group">
        <label class="control-label" for="inputLongitude">Longitude:</label>
        <div class="controls">
            <input type="text" id="inputLongitude" name="longitude" placeholder="Longitude">
        </div>   
    </div>



    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Registar</button>
        <button type="button" class="btn">Limpar</button>
    </div>

</form>
