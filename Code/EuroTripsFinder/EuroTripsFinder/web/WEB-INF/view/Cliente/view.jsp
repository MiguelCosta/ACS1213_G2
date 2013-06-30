<%-- 
    Document   : view
    Created on : 1/Jun/2013, 22:35:22
    Author     : miltonnunes52
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<div class="container" style="max-width: 390px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">


<h1>Perfil de ${cliente.getUtilizador().getNome()}</h1>

<form action="<%= request.getContextPath()%>/Cliente/update" method=post class="form-horizontal">

    <input type="hidden" name ="id" value="${cliente.getId()}">


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
        <button type="submit" class="btn btn-primary">Guardar</button>
        <a class="btn" href="<%= request.getContextPath()%>/Cliente/index">Cancelar</a>
    </div>

</form>    

<c:choose>
    <c:when test="${contratos.size() > 0}">
        <h1>Contratos</h1>
        <table class="table table-hover"> 
            <tr> 
                <td>ID:</td>
            </tr>
            <c:forEach var="row" items="${contratos}"> 
                <tr>
                    <td><a href="<%= request.getContextPath()%>/Contrato/view?id=${row.id}">${row.id}</a></td>
                </tr>
            </c:forEach> 
        </table>
    </c:when>
</c:choose>
        
</div>