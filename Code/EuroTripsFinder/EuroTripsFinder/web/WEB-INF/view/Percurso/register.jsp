<%-- 
    Document   : create
    Created on : 3/Jun/2013, 9:39:12
    Author     : Miguel
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<h1>Percurso</h1>

<form action="<%= request.getContextPath()%>/Percurso/add" method=post class="form-horizontal">


    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Guardar</button>
        <button type="button" class="btn">Limpar</button>
    </div>

</form>
