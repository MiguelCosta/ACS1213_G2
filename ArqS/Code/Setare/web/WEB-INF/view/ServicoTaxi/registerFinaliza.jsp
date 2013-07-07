

<form  action="<%= request.getContextPath()%>/ServicoTaxi/registerFinaliza" method=post" target="_blank">
    <div class="container" style="max-width: 600px; border: 1px solid #e5e5e5;  padding: 19px 29px 29px; margin: 0 auto 20px;">


        <h1 class="form-signin-heading" style="text-align: center">Dados da Reserva:</h1>




        <table style="width: 100%" >
            
            <tr>
                <td style="width: 50%;">                  
                    <label class="control-label" for="inputLocalOrigem">Origem:</label>
                    <div class="controls">
                        <input id="inputLocalOrigem" type="text" value="${localLevantamentoId.nome}" name="localLevantamentoId" readonly style="height: 30px;">
                    </div>   

                </td>
                <td style="width: 50%;">
                    <label class="control-label" for="inputLocalEntrega">Destino:</label> 
                    <div class="control-group">

                        <input type="text" id="inputLocalChegada" value="${localEntregaId.nome}" name="localEntregaId" readonly style="height: 30px;">
                    </div>   

                </td>
            </tr>
            <tr>





                <td>
                    <div style="float: left; clear: left; position: static;">
                        <div class="control-group">
                            <label class="control-label" for="inputLocalEntrega">Data:</label>

                            <div class="controls">          
                                <input type="date" id="inputDataLevantamento" name="dataLevantamento" placeholder="dd-MM-AAAA" style="width:150px; height: 30px;" value="${dataLevantamento}" readonly>
                            </div>   
                        </div>


                    </div>
                    <div style="float: right; padding-right: 15px;clear: right; position: static;">
                        <div class="control-group">
                            <label class="control-label" for="inputLocalEntrega">Hora:</label>

                            <div class="controls">
                                <input type="time" id="inputHoraLevantamento" name="horaLevantamento" style="width:90px; height: 30px;" value="${horaLevantamento}" readonly>
                            </div>   
                        </div>
                    </div>
                </td>

                <td>
                    <div style="float: left; clear: left; position: static; ">
                        <div class="control-group">
                            <label class="control-label" for="inputLocalEntrega">Bagagem:</label>
                            <div class="controls">
                                <input type="text" id="inputLocalChegada" value="${bagagem}" name="bagagem" readonly style="height: 30px; width: 140px; ">
                            </div>   
                        </div>
                    </div>
                    <div style="float: right; clear: right; position: static; padding-right: 15px;">
                        <div class="control-group">
                            <label class="control-label" for="inputLocalEntrega">Passageiros:</label>
                            <div class="controls">
                                <input type="number" id="inputPassageiros" name="passageiros" style="width:90px; height: 30px;" value="${passageiros}" readonly>
                            </div>   
                        </div>
                    </div>



                </td>
            </tr>
        </table>


        <div style="width: 50%; float : left;">
            <label class="control-label" for="inputNomeUtilizador">Nome de Cliente:</label>
            <div class="controls">
                <input type="text" id="inputNomeUtilizador" name="nomeUtilizador" value="${user.nome}" style="height: 30px; width: 95%" readonly>
            </div>   
        </div>
        <div style="width: 50%; float : right;">
            <label class="control-label" for="inputEmailUtilizador">Email:</label>
            <div class="controls">
                <input type="text" id="inputEmailUtilizador" name="emailUtilizador" value="${user.email}" style="height: 30px; width: 95%; " readonly>
            </div> 
        </div>
            
         <div style="width: 100%; text-align: center;" >

            <button class="btn btn-large btn-primary" onclick="this.disabled = true;
                    this.form.submit();" type="submit" >Imprimir Bilhete</button>

        </div>

       




    </div>

</form>

















