<%-- 
    Document   : register
    Created on : 28/Mai/2013, 19:25:03
    Author     : Miguel
--%>


<h1>Registar Utilizador</h1>

<form action="<%= request.getContextPath()%>/Utilizador/addUser" method=post class="form-horizontal">

    <div class="control-group">
        <label class="control-label" for="inputEmail">Email</label>
        <div class="controls">
            <input type="email" id="inputEmail" name="email" placeholder="email" required="required">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputUsername">Username</label>
        <div class="controls">
            <input type="text" id="inputUsername" name="username" placeholder="Username" required="required">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputPassword">Password</label>
        <div class="controls">
            <input type="password" id="inputPassword" name="password" placeholder="Password" required="required">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputNome">Nome</label>
        <div class="controls">
            <input type="text" id="inputNome" name="nome" placeholder="Nome" required="required">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputMorada">Morada</label>
        <div class="controls">
            <input type="text" id="inputMorada" name="morada" placeholder="Morada">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputDatanascimento">Data Nascimento</label>
        <div class="controls">
            <input type="date" 
                   id="inputMorada" 
                   name="datanascimento" 
                   placeholder="yyyy-MM-dd" 
                   min="1900-01-01" 
                   max="2020-01-01" 
                   pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" 
                   title="yyyy-MM-dd">
        </div>   
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Registar</button>
        <button type="button" class="btn">Limpar</button>
    </div>

</form>

