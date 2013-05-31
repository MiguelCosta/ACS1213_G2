<%-- 
    Document   : tiposmeiotransporte
    Created on : 30/Mai/2013, 22:55:13
    Author     : miltonnunes52
--%>

<%@page import="java.util.List"%>
<%@page import="entity.Tipomeiotransporte"%>

<h1>Meios de Transporte</h1>
<table class="table table-condensed">
 
    <% List<Tipomeiotransporte> l = (List<Tipomeiotransporte>)session.getAttribute("listmeiostransporte");
        for(int i = 0; i< l.size();i++){ %>
        <tr>
            <td>Nome:</td> 
            <td> <%= l.get(i).getNome() %> </td>
        </tr>
    <% } %> 

</table>