<%-- 
    Document   : view
    Created on : 1/Jun/2013, 22:35:22
    Author     : miltonnunes52
--%>

<h1>Perfil de ${cliente.getUtilizador().getNome()}</h1>

<form action="<%= request.getContextPath()%>/Cliente/update" method=post class="form-horizontal">

    <div class="control-group">
        <label class="control-label" for="inputEmail">Email</label>
        <div class="controls">
            <input type="email" id="inputEmail" name="email" placeholder="email" required="required" value="${cliente.getUtilizador().getEmail()}">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputUsername">Username</label>
        <div class="controls">
            <input type="text" id="inputUsername" name="username" placeholder="Username" required="required" readonly="readonly" value="${cliente.getUtilizador().getUsername()}">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputPassword">Password</label>
        <div class="controls">
            <input type="password" id="inputPassword" name="password" placeholder="Password" required="required" value="${cliente.getUtilizador().getPassword()}">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputNome">Nome</label>
        <div class="controls">
            <input type="text" id="inputNome" name="nome" placeholder="Nome" required="required" value="${cliente.getUtilizador().getNome()}">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputMorada">Morada</label>
        <div class="controls">
            <input type="text" id="inputMorada" name="morada" placeholder="Morada" value="${cliente.getUtilizador().getMorada()}">
        </div>   
    </div>
        
    <div class="control-group">
        <label class="control-label" for="inputMorada">NIF</label>
        <div class="controls">
            <input type="text" id="inputNif" name="nif" placeholder="Nif" value="${cliente.getNif()}">
        </div>   
    </div>
    
    <div class="control-group">
        <label class="control-label" for="inputMorada">Contacto</label>
        <div class="controls">
            <input type="text" id="inputContacto" name="contacto" placeholder="Contacto" value="${cliente.getContacto()}">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputDatanascimento">Data Nascimento</label>
        <div class="controls">
            <input type="date" id="inputDataNascimento" name="datanascimento" placeholder="yyyy-MM-dd" min="1900-01-01" max="2020-01-01" value="${cliente.getUtilizador().getDatanascimentoString()}">
        </div>   
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Update</button>
        <button type="button" class="btn">Limpar</button>
    </div>

</form>
