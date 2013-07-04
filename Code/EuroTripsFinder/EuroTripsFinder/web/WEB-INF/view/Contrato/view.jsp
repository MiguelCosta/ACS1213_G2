<%-- 
    Document   : view
    Created on : 2/Jun/2013, 2:09:08
    Author     : miltonnunes52
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

  
    <div class="row-fluid">
        <div class="span6">
            <div class="container" style="max-width: 390px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">



                <h1>Contrato ${contrato.getId()}</h1>
                <hr>
                <form action="<%= request.getContextPath()%>/Contrato/update" method=post class="form-horizontal">

                    <input type="hidden" name ="id" value="${contrato.getId()}">

                    <div class="control-group">
                        <label class="control-label" for="inputValor">Valor</label>
                        <div class="controls">
                            <input type="text" id="inputNome" name="valor" placeholder="Valor" required="required" value="${contrato.getValor()}">
                        </div>   
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputDatainicio">Data Início</label>
                        <div class="controls">
                            <input type="date" id="inputDataInicio" name="datainicio" placeholder="yyyy-MM-dd" min="1900-01-01" max="2020-01-01" value="${contrato.getDatainicioString()}">
                        </div>   
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="inputDatainicio">Data Fim</label>
                        <div class="controls">
                            <input type="date" id="inputDataFim" name="datafim" placeholder="yyyy-MM-dd" min="1900-01-01" max="2020-01-01" value="${contrato.getDatafimString()}">
                        </div>   
                    </div>
                    
                    <div class="control-group">
                        <label class="control-label" for="inputCliente">Cliente</label>
                        <div class="controls">
                        <a class="btn" href="<%= request.getContextPath()%>/Cliente/view?id=${contrato.getClienteid().getId()}">${contrato.getClienteid().getId()}</a>
                        </div>   
                    </div>


                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary">Guardar</button>
                        <a class="btn" href="<%= request.getContextPath()%>/Contrato/index">Cancelar</a>
                    </div>

                </form>        
                </div>

        </div>
                    
        <div class="span6">
           <div class="container" style="max-width: 390px;   border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px; ">
                
                    <h2>Artigos Publicitários:</h2>
                     
                    <div style="max-height: 322px; overflow: auto">
                    <table class="table table-hover"> 
                            <tr> 
                                <td>Nome:</td>
                           
                            </tr>
                           
                            <c:choose>
                            <c:when test="${artigos.size() > 0}">
                            <c:forEach var="row" items="${artigos}"> 
                            <tr>
                                <td><a href="<%= request.getContextPath()%>/Artigo/view?id=${row.id}">${row.nome}</a></td>
                            </tr>
                            </c:forEach>
                            </c:when>
                            <c:otherwise>
                            <tr>
                                <td>Contrato sem artigos</td>
                            </tr>    
                            </c:otherwise>
                            </c:choose>
                    </table>
                     </div>
        </div>
        </div>
    </div>
