<%-- 
    Document   : updateCoordinate
    Created on : 30/Mai/2013, 15:05:16
    Author     : JorgeMaia
--%>

<div class="container" style="max-width: 425px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">

<h1>Atualizar Coordenadas</h1>
<hr>
<form action="<%= request.getContextPath()%>/Coordenada/update" method=post class="form-horizontal">

       <div class="control-group">
        <label class="control-label" for="inputNomeCoordenada">Nome:</label>
        <div class="controls">
            <input type="text" id="inputNomeCoordenada" name="nomecoordenada" placeholder="Nome" required="required" value="${coordenada.getNome()}">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputLatitude">Latitude:</label>        
        <div class="controls">
            <input type="text" id="inputLatitude" name="latitude" placeholder="Latitude" value="${coordenada.getLatitude()}">
        </div>   
    </div>
    
     <div class="control-group">
        <label class="control-label" for="inputLongitude">Longitude:</label>
        <div class="controls">
            <input type="text" id="inputLongitude" name="longitude" placeholder="Longitude" value="${coordenada.getLongitude()}">
        </div>   
    </div>

    <% if (session.getAttribute("erro") != null) {%>
    <div class="alert alert-error">
        <%= session.getAttribute("erro")%>
    </div>
    <% session.setAttribute("erro", null);
        }%>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Guardar</button>
        <a class="btn" href="<%= request.getContextPath()%>/Coordenada/index">Cancelar</a>
    </div>

</form>
        
</div>
