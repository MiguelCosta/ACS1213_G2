

<form  action="<%= request.getContextPath()%>/ServicoRent/registerFinaliza" method=post">
 <div class="container" style="max-width: 600px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px;">

       
            <h1 class="form-signin-heading" style="text-align: center">Dados da Reserva:</h1>


            <div class="control-group">
                <label class="control-label" for="inputCategoria">Categoria do Veiculo:</label>
                <div class="controls">               
                    <input type="text" id="inputCategoria" name="categoria" value=" ${categoria.nome}">
                </div>   
            </div>
            <div class="control-group">
                <label class="control-label" for="inputDescricao">Descrição do Veiculo:</label>
                <div class="controls">               
                    <input type="text" id="inputDescricao" name="descricao" value=" ${carro.descricao}" style="width: 100%;">
                </div>   
            </div>

            <table style="width: 100%" >
                <tr>
                    <td style="width: 50%; text-align: center;">                  
                        <label class="control-label" for="inputLocalOrigem">Levantamento:</label>
                        <div class="controls">
                            <input id="inputLocalOrigem" type="text" value="${localLevantamento.nome}" name="localLevantamento" >
                        </div>   
                        </div>
                    </td>
                    <td style="width: 50%; text-align: center;">
                        <div class="control-group">
                            <label class="control-label" for="inputLocalEntrega">Entrega:</label>
                            <div class="controls">
                                <input type="text" id="inputLocalChegada" value="${localEntrega.nome}" name="localchegada">
                            </div>   
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>

                        <div style="float: left; clear: left; position: static;">
                            <div class="control-group">

                                <div class="controls">          
                                    <input type="date" id="inputDataEntrega" name="datachegada" placeholder="dd-MM-AAAA" style="width:150px;" value="${dataEntrega}">
                                </div>   
                            </div>


                        </div>
                        <div style="float: right; clear: right; position: static;">
                            <div class="control-group">

                                <div class="controls">
                                    <input type="time" id="inputHoraEntrega" name="horaEntrega" style="width:90px;" value="${horaEntrega}">
                                </div>   
                            </div>
                        </div>

                    </td>
                    <td>
                        <div style="float: left; clear: left; position: static;">
                            <div class="control-group">

                                <div class="controls">          
                                    <input type="date" id="inputDataLevantamento" name="datapartida" placeholder="dd-MM-AAAA" style="width:150px;" value="${dataLevantamento}">
                                </div>   
                            </div>


                        </div>
                        <div style="float: right; clear: right; position: static;">
                            <div class="control-group">

                                <div class="controls">
                                    <input type="time" id="inputHoraLevantamento" name="horalevantamento" style="width:90px;" value="${horaLevantamento}">
                                </div>   
                            </div>
                        </div>
                    </td>
                </tr>
            </table>







            <div style="width :33%;float : left; text-align: center;"> 

                <div class="control-group">
                    <label class="control-label" for="inputPrecoDia">Nº de Dias:</label>
                    <div class="controls">
                        <input type="text" id="inputPrecoDia" name="precoDia" value="${dias}">
                    </div>   
                </div>
            </div>
            <div style="width :33%;float : left;text-align: center;"> 
                <div class="control-group">
                    <label class="control-label" for="inputPrecoDia">Preco por Dia:</label>
                    <div class="controls">
                        <input type="text" id="inputPrecoDia" name="precoDia" value="${categoria.precoPorHora}&#8364">
                    </div>   
                </div>
            </div>
            <div style="width :33%;float : left;text-align: center;"> 
                <div class="control-group">
                    <label class="control-label" for="inputTotal">Total a Pagar:</label>
                    <div class="controls">
                        <input type="text" id="inputTotal" name="precoTotal" value="${total}&#8364">
                    </div>   
                </div>
            </div>

            <div style="width: 49%; text-align: center;float : left;">
                <label class="control-label" for="inputNomeUtilizador">Nome de Cliente:</label>
                <div class="controls">
                    <input type="text" id="inputNomeUtilizador" name="nomeUtilizador" value="${user.nome}" width="100%">
                </div>   
            </div>
            <div style="width: 49%; text-align: center;float : left;">
                <label class="control-label" for="inputEmailUtilizador">Email:</label>
                <div class="controls">
                    <input type="text" id="inputEmailUtilizador" name="emailUtilizador" value="${user.email}" width="100%" >
                </div> 
            </div>

            <div style="width: 100%; text-align: center;" >

                <button class="btn btn-large btn-primary" type="submit">Confirmar</button>

            </div>


    

    </div>
   
</form>

















