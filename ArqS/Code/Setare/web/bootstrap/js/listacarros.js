/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function loadList() {
    var categoria = document.getElementById('categoria').value;

    $.ajax({url: '/Setare/ServicoRent/lista', type: "POST", data: "categoria=" + categoria}).done(function(html) {
        $('#listaCarros').html(html);
    });

}

