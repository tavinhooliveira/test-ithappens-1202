
//Funcao Bloquear Campo
$(document).ready(function(){
	$("a.btn_block").click(function(){
		$(".block").removeAttr('disabled');
	});
});

//Funcao buscarMultiplosCampos
$(function(){
    $("#tabela input").keyup(function(){       
        var index = $(this).parent().index();
        var nth = "#tabela td:nth-child("+(index+1).toString()+")";
        var valor = $(this).val().toUpperCase();
        $("#tabela tbody tr").show();
        $(nth).each(function(){
            if($(this).text().toUpperCase().indexOf(valor) < 0){
                $(this).parent().hide();
            }
        });
    });
 
});


//ModalExcluir
$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigo = button.data('codigo');
	var titulo = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigo);
		
	modal.find('.modal-body span').html('Tem certeza que deseja excluir: Codigo:' + codigo+' - <strong>'+ titulo + '</strong>?'+
			'</br> <p class="text-danger">OBS.: Só será possivel excluir a Venda: <b>'+codigo+'</b> se não tiver pedidos lançadas!</p>');
});


//ModalExcluir
$('#confirmacaoExclusaoOrderedItemsModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigo = button.data('codigo');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigo);
		
	modal.find('.modal-body span').html('Tem certeza que deseja excluir Codigo:<strong>'+codigo+'</strong>?');
			
});

//AddItemsPedido
$('#addOrderedItems').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);	
	var codigoTask = button.data('codigo');	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');

	form.attr('action', action);	
	document.querySelector("[name='sales']").value = codigoTask;		
	modal.find('.modal-body p').html('Task:<strong> '+codigoTask);

});



//login
$('.message a').click(function(){
	   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});


//Reload
function reload() {
    location.reload();
    
}

//tooltip
$('[rel="tooltip"]').tooltip(function(){
	 $('[rel="tooltip"]').tooltip();
});

//tooltip
$('[rel="popover"]').popover(function(){
	 $('[rel="popover"]').popover();
});

