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