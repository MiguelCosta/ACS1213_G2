<%-- 
    Document   : tiposmeiotransporte
    Created on : 30/Mai/2013, 22:55:13
    Author     : miltonnunes52
--%>
 
<%@page import="java.util.List"%>
<%@page import="entity.Tipomeiotransporte"%>

<h1>Meios de Transporte</h1>
<table class="table table-condensed">
    <thead>
        <tr>
            <td>Nome</td>
        </tr>
    </thead>
    <tbody>
    <% List<Tipomeiotransporte> l = (List<Tipomeiotransporte>)getServletContext().getAttribute("listmeiostransporte");
        for(int i = 0; i< l.size();i++){ %>
        <tr>
            <td><a href="/EuroTripsFinder/TipoMeioTransporte/view" onclick="<% getServletContext().setAttribute("tipo", l.get(1)); %>"> <%= l.get(i).getNome() %> </a></td>
        </tr>
    <% } %> 
    </tbody>
</table>