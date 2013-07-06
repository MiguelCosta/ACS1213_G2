<% Utilizador user = (Utilizador) session.getAttribute("user");
    String aux = "";
    if (user.getFuncao().equals("User")) {
        aux = "/ServicoTaxi/index";

    } else if (user.getFuncao().equals("Admin")) {
        aux = "/ServicosTaxi/index";
    }
%>


<form>
    <div class="container" style="max-width: 600px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px;">


        <h1 class="form-signin-heading" style="text-align: center">Dados da Reserva:</h1>


        
        <div class="control-group">
            <label class="control-label" for="inputDescricao">Taxi:</label>
            <div class="controls">               
                <input type="text" id="inputDescricao" name="descricao" value=" ${servico.getCodigotaxi()}" style="width: 100%;" disabled>
            </div>   
        </div>

        <table style="width: 100%" >
            <tr>
                <td style="width: 50%; text-align: center;">                  
                    <label class="control-label" for="inputLocalOrigem">Levantamento:</label>
                    <div class="controls">
                        <input id="inputLocalOrigem" type="text" value="${servico.getLocalpartidaid().getNome()}" name="localLevantamento" disabled >
                    </div>   

                </td>
                <td style="width: 50%; text-align: center;">
                    <div class="control-group">
                        <label class="control-label" for="inputLocalEntrega">Entrega:</label>
                        <div class="controls">
                            <input type="text" id="inputLocalChegada" value="${servico.getLocalchegadaid().getNome()}" name="localchegada" disabled>
                        </div>   
                    </div>
                </td>
            </tr>
            <tr>
                <td style="width: 50%; text-align: center;">
                    <div style="float: center; clear: left; position: static;">
                        <div class="control-group">
                            <label class="control-label" for="inputPrecoDia">Data:</label>
                            <div class="controls">          
                                <input type="text" id="inputDataLevantamento" name="datapartida" placeholder="dd-MM-AAAA" style="width:210px;" value="${servico.getDatapartida()}" disabled>
                            </div>   
                        </div>


                    </div>

                </td>
            </tr>
        </table>
                            
               <td style="width: 50%; text-align: center;">
                    <div class="control-group">
                        <label class="control-label" for="inputLocalEntrega">Bagagem:</label>
                        <div class="controls">
                            <input type="text" id="inputLocalChegada" value="${servico.getBagagem()}" name="localchegada" disabled>
                        </div>   
                    </div>
                </td>
                
                <td style="width: 50%; text-align: center;">
                    <div class="control-group">
                        <label class="control-label" for="inputLocalEntrega">Passageiros:</label>
                        <div class="controls">
                            <input type="text" id="inputLocalChegada" value="${servico.getPassageiros()}" name="localchegada" disabled>
                        </div>   
                    </div>
                </td>








      



        <div style="width: 49%; text-align: center;float : left;">
            <label class="control-label" for="inputNomeUtilizador">Nome de Cliente:</label>
            <div class="controls">
                <input type="text" id="inputNomeUtilizador" name="nomeUtilizador" value="${servico.getUtilizadorid().getNome()}" width="100%" disabled>
            </div>   
        </div>
        <div style="width: 49%; text-align: center;float : left;">
            <label class="control-label" for="inputEmailUtilizador">Email:</label>
            <div class="controls">
                <input type="text" id="inputEmailUtilizador" name="emailUtilizador" value="${servico.getUtilizadorid().getEmail()}" width="100%" disabled >
            </div> 
        </div>

        <div style="width: 100%; text-align: center;" >

            <a class="btn btn-large btn-primary" href="/Setare<%=aux%>">Voltar</a>

        </div>




    </div>

</form>
