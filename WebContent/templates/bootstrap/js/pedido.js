

function cadastrar(pkProduto,pkCliente, preco, idPedido){
	    	var useCase = 'manterPedido';
	    	var action  = 'cadastrar';
	    	
	    	var temp = $("#valor").text();
	    	$("#valor").text(" ");
	    	$("#valor").text(parseInt(temp) + parseInt(preco));
	    	
	    	//chamando método:
	    	enviar(pkProduto,pkCliente,preco);
	    	
	    	console.log(pkProduto+"  "+pkCliente);	
	    }
//<!-- href="q?useCase=manterPedido&action=cadastrar&redirect=false&pkProduto=${pkProduto}&pkCliente=${pkCliente}" -->
function enviar (pkP,pkC,preco,idP){
	$.post( "q", { useCase: 'manterPedido', 
				   action: 'cadastrar',
				   redirect: 'false',
				   pkProduto: pkP,
				   idPedido: idP,
				   pkCliente: pkC,
				   preco: preco
				  }).done(function( data ) {
	    alert( "Data Loaded: " + data );
	  });
}