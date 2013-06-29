<h1>Criar Nova Viagem</h1>

<form action="<%= request.getContextPath()%>/Viagem/add" method=post class="form-horizontal">

   

    <div class="control-group">
        <label class="control-label" for="inputNomeViagem">Nome</label>
        <div class="controls">
            <input type="text" id="inputNomeViagem" name="nome" placeholder="Nome da Viagem" required="required">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputDatainicio">Data de Início</label>
        <div class="controls">
            <input type="date" 
                   id="inputDatainicio" 
                   name="datainicio" 
                   placeholder="yyyy-MM-dd" 
                   min="1900-01-01" 
                   max="2020-01-01" 
                   pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" 
                   title="yyyy-MM-dd">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputDatafinal">Data de Fim</label>
        <div class="controls">
            <input type="date" 
                   id="inputDatafinal" 
                   name="datafim" 
                   placeholder="yyyy-MM-dd" 
                   min="1900-01-01" 
                   max="2020-01-01" 
                   pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" 
                   title="yyyy-MM-dd">
        </div>   
    </div>

    <div class="control-group">
        <label class="control-label" for="inputDescricao">Descrição</label>
        <div class="controls">
           <TEXTAREA id="inputDescricao" NAME="descricao" ROWS=3 COLS=30 required="required" placeholder="Descrição"></textarea>
        </div>   
    </div>

    

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Registar</button>
        <button type="button" class="btn">Limpar</button>
    </div>

</form>


