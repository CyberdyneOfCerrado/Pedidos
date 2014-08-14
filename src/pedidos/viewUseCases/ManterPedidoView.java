package pedidos.viewUseCases;

import java.util.ArrayList;

import biz.source_code.miniTemplator.MiniTemplator;
import pedidos.model.Cliente;
import pedidos.model.Produto;
import pedidos.util.ActionDone;
import pedidos.view.ViewController;

public class ManterPedidoView extends ViewController {

	public ManterPedidoView(String sevletContext, String useCase) {
		super(sevletContext, useCase);
		// TODO Auto-generated constructor stub
	};

	private String listar ( ActionDone ad){
		String resul = "";
		if(!ad.isProcessed()){
			MiniTemplator temp = super.startMiniTemplator(super.getTemplate(ad));
			resul = temp.generateOutput();
		}else{
			MiniTemplator temp;
			
			if(ad.isStatus()){//Mensagem de 'tudo bem'.
				 temp = super.startMiniTemplator(super.getSevletContext()+ad.getUseCase()+super.getSeparador()+"listar.html");
				 ArrayList<Cliente> arl = (ArrayList<Cliente>) ad.getData("todos");
				 for(Cliente cliente : arl ){
					 temp.setVariable("pk",cliente.getPk());
					 temp.setVariable("nome", cliente.getNome());
					 temp.addBlock("table");
				 }
				 resul = temp.generateOutput();
			}else{
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"error.html");
				 resul = temp.generateOutput();
			}
		}
		return resul;
	}
	
	private String pedido(ActionDone ad) {
		String resul = "";
		if(!ad.isProcessed()){
			MiniTemplator temp = super.startMiniTemplator(super.getTemplate(ad));
			resul = temp.generateOutput();
		}else{
			MiniTemplator temp;
			
			if(ad.isStatus()){//Mensagem de 'tudo bem'.
				 temp = super.startMiniTemplator(super.getTemplate(ad));
				 temp.setVariable("nomeCliente", (String) ad.getData("nome"));
				 ArrayList<Produto> arl = (ArrayList<Produto>) ad.getData("todos");
				 for(Produto produto : arl ){
					 temp.setVariable("pkCliente", (String) ad.getData("pk"));
					 temp.setVariable("idPedido", String.valueOf(ad.getData("idPedido")));
					 temp.setVariable("pkProduto",produto.getPk());
					 temp.setVariable("nome", produto.getNome());
					 temp.setVariable("preco", produto.getPreco());
					 temp.addBlock("table");
				 }
				 resul = temp.generateOutput();
			}else{
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"error.html");
				 resul = temp.generateOutput();
			}
		}
		return resul;
	}
	
	private String alterar (ActionDone ad) {
		String resul = "";
		if(!ad.isProcessed()){
			MiniTemplator temp = super.startMiniTemplator(super.getTemplate(ad));
			resul = temp.generateOutput();
		}else{
			MiniTemplator temp;
			
			if(ad.isStatus()){//Mensagem de 'tudo bem'.
				 temp = super.startMiniTemplator(super.getTemplate(ad));
				 ArrayList<Produto> arl = (ArrayList<Produto>) ad.getData("todos");
				 for(Produto produto : arl ){
					 temp.setVariable("pkCliente", (String) ad.getData("pkCliente"));
					 temp.setVariable("idPedido", String.valueOf(ad.getData("pkPedido")));
					 temp.setVariable("pkProduto",produto.getPk());
					 temp.setVariable("nome", produto.getNome());
					 temp.setVariable("preco", produto.getPreco());
					 temp.addBlock("table");
				 }
				 resul = temp.generateOutput();
			}else{
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"error.html");
				 resul = temp.generateOutput();
			}
		}
		return resul;
	}
	
	private String excluir( ActionDone ad ){
		String resul = "";
		if(ad.isProcessed()){
			MiniTemplator temp;
			temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"success.html");
			
			temp.setVariable("mensagem",ad.getMessage());
				
			resul = temp.generateOutput();
		}else{
			MiniTemplator temp;
				 temp = super.startMiniTemplator(super.getSevletContext()+ad.getUseCase()+super.getSeparador()+"alerta.html");
				  
				 temp.setVariable("pkPedido",(String) ad.getData("pkPedido"));
				 temp.setVariable("pkCliente",(String) ad.getData("pkCliente"));
				 resul = temp.generateOutput();
		}
		return resul;
	};
	
	@Override
	public String choose(ActionDone ad) {
		String resul=null;
		switch(ad.getAction()){
		case "listar":
			resul = listar(ad);
			break;
		case "pedido":
			resul = pedido(ad);
			break;
		case "excluir":
			resul = excluir(ad);
			break;
		case "alterar":
			resul = alterar(ad);
			break;
		}
		return resul;
	}
}
