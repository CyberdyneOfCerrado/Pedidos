package pedidos.viewUseCases;

import java.util.ArrayList;

import biz.source_code.miniTemplator.MiniTemplator;
import pedidos.model.Produto;
import pedidos.util.ActionDone;
import pedidos.view.ViewController;

public class ManterProdutoView extends ViewController{

	public ManterProdutoView(String sevletContext, String useCase) {
		super(sevletContext, useCase);
	};

	private String cadastrar ( ActionDone ad){
		String resul = "";
		if(!ad.isProcessed()){
			MiniTemplator temp = super.startMiniTemplator(super.getTemplate(ad));
			resul = temp.generateOutput();
		}else{
			MiniTemplator temp;
			
			if(ad.isStatus()){//Mensagem de 'tudo bem'.
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"success.html");
				 resul = temp.generateOutput();
			}else{
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"error.html");
				 resul = temp.generateOutput();
			}
		}
		return resul;
	}
	
	private String consultar( ActionDone ad){
		String resul = "";
		if(!ad.isProcessed()){
			MiniTemplator temp = super.startMiniTemplator(super.getTemplate(ad));
			resul = temp.generateOutput();
		}else{
			MiniTemplator temp;
			
			if(ad.isStatus()){//Mensagem de 'tudo bem'.
				 temp = super.startMiniTemplator(super.getSevletContext()+ad.getUseCase()+super.getSeparador()+"row.html");
				 ArrayList<Produto> arl = (ArrayList<Produto>) ad.getData("search");
				 for(Produto produto : arl ){
					 temp.setVariable("pk",produto.getPk());
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
	};
	
	private String excluir( ActionDone ad){
		String resul = "";
		if(ad.isProcessed()){
			MiniTemplator temp;
			temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"success.html");
			temp.setVariable("mensagem",ad.getMessage());
			resul = temp.generateOutput();
		}else{
			MiniTemplator temp;
				 temp = super.startMiniTemplator(super.getSevletContext()+ad.getUseCase()+super.getSeparador()+"alerta.html");
				 temp.setVariable("pk",(String) ad.getData("pk"));
				 resul = temp.generateOutput();
		}
		return resul;
	};
	
	private String alterar( ActionDone ad){
		String resul = "";
		if(!ad.isProcessed()){
			MiniTemplator temp = super.startMiniTemplator(super.getTemplate(ad));
			temp.setVariable("pk",(String) ad.getData("pk"));
			temp.setVariable("nome",(String) ad.getData("nome"));
			temp.setVariable("preco",(String) ad.getData("preco"));
			resul = temp.generateOutput();
		}else{
			MiniTemplator temp;
			if(ad.isStatus()){//Mensagem de 'tudo bem'.
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"success.html");
				 resul = temp.generateOutput();
			}else{
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"error.html");
				 resul = temp.generateOutput();
			}
		}
		return resul;
	};
	
	@Override
	public String choose(ActionDone ad) {
		String resul=null;
		switch(ad.getAction()){
		case "cadastrar":
			resul = cadastrar(ad);
			break;
		case "consultar":
			resul = consultar(ad);
			break;
		case "alterar":
			resul = alterar(ad);
			break;
		case "excluir":
			resul = excluir(ad);
			break;
		}
		return resul;
	}

}
