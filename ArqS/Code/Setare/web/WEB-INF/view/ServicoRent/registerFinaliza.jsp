

<form  action="<%= request.getContextPath()%>/ServicoRent/registerFinaliza" method=post" target="_blank">
    <div class="container" style="max-width: 650px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px;">


        <h1 class="form-signin-heading" style="text-align: center">Dados da Reserva:</h1>


        <div class="control-group">
            <label class="control-label" for="inputCategoria">Categoria do Veiculo:</label>
            <div class="controls">               
                <input type="text" id="inputCategoria" name="categoria" value=" ${categoria.nome}" disabled style="height: 30px;">
            </div>   
        </div>
        <div class="control-group">
            <label class="control-label" for="inputDescricao">Descrição do Veiculo:</label>
            <div class="controls">               
                <input type="text" id="inputDescricao" name="descricao" value=" ${carro.descricao}" style="width: 97%;height: 30px;" disabled>
            </div>   
        </div>

        <table style="width: 100%" >
            <tr>
                <td style="width: 50%; ">   
                    <div class="control-group">
                    <label class="control-label" for="inputLocalOrigem">Levantamento:</label>
                    <div class="controls">
                        <input id="inputLocalOrigem" type="text" value="${localLevantamento.nome}" name="localLevantamento" disabled style="width: 95%; height: 30px;">
                    </div>   
                    </div>
                </td>
                <td style="width: 50%; ">
                    <div class="control-group">
                        <label class="control-label" for="inputLocalEntrega">Entrega:</label>
                        <div class="controls">
                            <input type="text" id="inputLocalChegada" value="${localEntrega.nome}" name="localchegada" disabled style="width: 95%; height: 30px;">
                        </div>   
                    </div>
                </td>
            </tr>
            <tr>
                <td>

                    <div style="float: left; clear: left; position: static;">
                        <div class="control-group">

                            <div class="controls">          
                                <input type="date" id="inputDataEntrega" name="datachegada" placeholder="dd-MM-AAAA" style="width:150px; height: 30px;" value="${datapartidaString}" disabled>
                            </div>   
                        </div>


                    </div>
                    <div style="float: right; clear: right; position: static;  padding-right: 15px;">
                        <div class="control-group">

                            <div class="controls">
                                <input type="time" id="inputHoraEntrega" name="horaEntrega" style="width:90px; height: 30px;" value="${horaLevantamento}" disabled>
                            </div>   
                        </div>
                    </div>

                </td>
                <td>
                    <div style="float: left; clear: left; position: static;">
                        <div class="control-group">

                            <div class="controls">          
                                <input type="date" id="inputDataLevantamento" name="datapartida" placeholder="dd-MM-AAAA" style="width:150px; height: 30px;" value="${datachegadaString}" disabled>
                            </div>   
                        </div>


                    </div>
                    <div style="float: right; clear: right; position: static;  padding-right: 15px;">
                        <div class="control-group">

                            <div class="controls">
                                <input type="time" id="inputHoraLevantamento" name="horalevantamento" style="width:90px; height: 30px;" value="${horaEntrega}" disabled>
                            </div>   
                        </div>
                    </div>
                </td>
            </tr>
        </table>







        <div style="width :33%;float : left;"> 

            <div class="control-group">
                <label class="control-label" for="inputPrecoDia">Nº de Dias:</label>
                <div class="controls">
                    <input type="text" id="inputPrecoDia" name="dias" value="${dias}" disabled style="height: 30px;">
                </div>   
            </div>
        </div>
        <div style="width :33%;float : left;"> 
            <div class="control-group">
                <label class="control-label" for="inputPrecoDia">Preco/Hora:</label>
                <div class="controls">
                    <input type="text" id="inputPrecoDia" name="precoDia" value="${categoria.precoPorHora}&#8364" disabled style="height: 30px;">
                </div>   
            </div>
        </div>
        <div style="width :33%;float : left;"> 
            <div class="control-group">
                <label class="control-label" for="inputTotal">Total a Pagar:</label>
                <div class="controls">
                    <input type="text" id="inputTotal" name="precoTotal" value="${total}&#8364" disabled style="height: 30px;">
                </div>   
            </div>
        </div>



        <div style="width :79%;float : left;"> 
            <div class="control-group">
                <label class="control-label" for="inputTotal">Extras Descricao:</label>
                <div class="controls">
                    <input type="text" id="inputTotal" name="descricaoExtras" value="${extras}" width="100%" style="width: 100%; height: 30px;" disabled>
                </div>   
            </div>
        </div>
        <div style="width :20%;float : right; "> 
            <div class="control-group">
                <label class="control-label" for="inputTotal">Extras Valor:</label>
                <div class="controls">
                    <input type="text" id="inputTotal" name="valorExtras" value="${valorExtras}&#8364" style="width: 88%; height: 30px;" disabled>
                </div>   
            </div>
        </div>






        <div style="width: 49%;float : left;">
            <label class="control-label" for="inputNomeUtilizador">Nome de Cliente:</label>
            <div class="controls">
                <input type="text" id="inputNomeUtilizador" name="nomeUtilizador" value="${user.nome}" width="100%" disabled style="height: 30px; width: 95%">
            </div>   
        </div>
        <div style="width: 49%; height: 30px;float : left;">
            <label class="control-label" for="inputEmailUtilizador">Email:</label>
            <div class="controls">
                <input type="text" id="inputEmailUtilizador" name="emailUtilizador" value="${user.email}" width="100%" disabled style="height: 30px; width: 99%">
            </div> 
        </div>

        <div style="width: 100%; text-align: center; float: left; clear: left" >
            <br>
            <button class="btn btn-large btn-primary" onclick="this.disabled = true;
                    this.form.submit();" type="submit">Imprimir bilhete</button>
            
        </div>




    </div>

</form>

















