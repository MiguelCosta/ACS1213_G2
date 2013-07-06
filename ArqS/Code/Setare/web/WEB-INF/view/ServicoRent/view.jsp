


<form  action="<%= request.getContextPath()%>/ServicoRent/index" method=post">
    <div class="container" style="max-width: 600px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px;">


        <h1 class="form-signin-heading" style="text-align: center">Dados da Reserva:</h1>


        <div class="control-group">
            <label class="control-label" for="inputCategoria">Categoria do Veiculo:</label>
            <div class="controls">               
                <input type="text" id="inputCategoria" name="categoria" value=" ${servico.getCarroid().getCategoriaid().getNome()}" disabled>
            </div>   
        </div>
        <div class="control-group">
            <label class="control-label" for="inputDescricao">Descrição do Veiculo:</label>
            <div class="controls">               
                <input type="text" id="inputDescricao" name="descricao" value=" ${servico.getCarroid().getDescricao()}" style="width: 100%;" disabled>
            </div>   
        </div>

        <table style="width: 100%" >
            <tr>
                <td style="width: 50%; text-align: center;">                  
                    <label class="control-label" for="inputLocalOrigem">Levantamento:</label>
                    <div class="controls">
                        <input id="inputLocalOrigem" type="text" value="${servico.getLocalPartidaid().getNome()}" name="localLevantamento" disabled >
                    </div>   

                </td>
                <td style="width: 50%; text-align: center;">
                    <div class="control-group">
                        <label class="control-label" for="inputLocalEntrega">Entrega:</label>
                        <div class="controls">
                            <input type="text" id="inputLocalChegada" value="${servico.getLocalChegadaid().getNome()}" name="localchegada" disabled>
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
                                <input type="text" id="inputDataEntrega" name="datachegada" placeholder="dd-MM-AAAA" style="width:210px;" value="${servico.getDataPartida()}" disabled>
                            </div>   
                        </div>


                    </div>


                </td>
                <td style="width: 50%; text-align: center;">
                    <div style="float: center; clear: left; position: static;">
                        <div class="control-group">
                            <label class="control-label" for="inputPrecoDia">Data:</label>
                            <div class="controls">          
                                <input type="text" id="inputDataLevantamento" name="datapartida" placeholder="dd-MM-AAAA" style="width:210px;" value="${servico.getDataChegada()}" disabled>
                            </div>   
                        </div>


                    </div>

                </td>
            </tr>
        </table>








        <div style="width :50%;float : left;text-align: center;"> 
            <div class="control-group">
                <label class="control-label" for="inputPrecoDia">Preco/Hora:</label>
                <div class="controls">
                    <input type="text" id="inputPrecoDia" name="precoDia" value="${ servico.getCarroid().getCategoriaid().getPrecoPorHora()}&#8364" disabled>
                </div>   
            </div>
        </div>
        <div style="width :49%;float : left;text-align: center;"> 
            <div class="control-group">
                <label class="control-label" for="inputTotal">Total a Pagar:</label>
                <div class="controls">
                    <input type="text" id="inputTotal" name="precoTotal" value="${servico.getPreco()}&#8364" disabled>
                </div>   
            </div>
        </div>



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

            <button class="btn btn-large btn-primary" type="submit">Voltar</button>

        </div>




    </div>

</form>

















