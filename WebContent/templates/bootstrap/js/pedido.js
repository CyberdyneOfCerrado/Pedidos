function cadastrar(pkProduto,pkCliente, preco, idPedido){	
	    	var temp = $("#valor").text();
	    	$("#valor").text(" ");
	    	$("#valor").text(parseInt(temp) + parseInt(preco));
	    	//chamando método:
	    	enviar(pkProduto,pkCliente,preco,idPedido);
	    }

function remover(pkProduto,pkCliente, preco, idPedido){
	enviarRemover(pkProduto,pkCliente,preco,idPedido);
}

function atualizar(idP){
	formaPagamento(idP);
}


function enviarRemover (pkP,pkC,preco,idP){
	console.log(pkP+" + "+pkC+" + "+preco+" + "+idP);
	$.post( "q", { useCase: 'manterPedido', 
				   action: 'removeProduto',
				   redirect: 'false',
				   pkProduto: pkP,
				   idPedido: idP,
				   pkCliente: pkC,
				   preco: preco
				  }).done(function( data ) {
				$("#"+pkP).remove();
	  });
}
function formaPagamento(idP){
	//Pegar o que está selecionado
	var temp = $('#pagamento').val();
	console.log(temp);
	
	$.post( "q", { useCase: 'manterPedido', 
		   action: 'updatePagamento',
		   redirect: 'false',
		   idPedido: idP,
		   idPagam : temp
		  }).done(function( data ) {
			  alert("Mudanca feita com sucesso!");
});
}

function enviar (pkP,pkC,preco,idP){
	console.log(pkP+" + "+pkC+" + "+preco+" + "+idP);
	$.post( "q", { useCase: 'manterPedido', 
				   action: 'cadastrar',
				   redirect: 'false',
				   pkProduto: pkP,
				   idPedido: idP,
				   pkCliente: pkC,
				   preco: preco
				  }).done(function( data ) {
				$("#"+pkP).text('OK');
	  });
}