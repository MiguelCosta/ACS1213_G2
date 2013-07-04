<%-- 
    Document   : contact
    Created on : 3/Jul/2013, 23:03:45
    Author     : miltonnunes52
--%>

<%-- 
    Document   : register
    Created on : 2/Jun/2013, 2:09:00
    Author     : miltonnunes52
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<div class="container" style="max-width: 645px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">

<h1>Contact Us</h1>
<hr>

<form action="<%= request.getContextPath()%>" method=post class="form-horizontal">

    <div class="control-group">
        <label class="control-label" for="inputNome">Nome</label>
        <div class="controls" style="width: 400px;">
            <input type="text" id="inputNome" style="width: 400px;"name="nome" placeholder="Nome" required="required">
        </div>   
    </div>
    <div class="control-group">
        <label class="control-label" for="inputEmail">Email</label>
        <div class="controls" style="width: 400px;">
            <input type="text" id="inputNome" style="width: 400px;" name="email" placeholder="Email" required="required">
        </div>   
    </div>
    
    <div class="control-group">
        <label class="control-label" for="inputConteudo">Mensagem</label>
        <div class="controls" style="width: 400px;">
            <textarea type="text" ROWS=10 COLS=250 style="width: 400px; height: 200px" id="inputConteudo" name="conteudo" placeholder="Mensagem" required="required"></textarea>
        </div>   
    </div>
    
    <div class="form-actions">
        <button type="submit" class="btn btn-primary" >Enviar</button>

    </div>
    
</form>

</div>
