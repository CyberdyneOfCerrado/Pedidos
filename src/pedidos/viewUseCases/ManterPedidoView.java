package pedidos.viewUseCases;

import java.util.ArrayList;

import biz.source_code.miniTemplator.MiniTemplator;
import pedidos.model.Cliente;
import pedidos.util.ActionDone;
import pedidos.view.ViewController;

public class ManterPedidoView extends ViewController {

	public ManterPedidoView(String sevletContext, String useCase) {
		super(sevletContext, useCase);
		// TODO Auto-generated constructor stub
	};

	private String cadastrar ( ActionDone ad){
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
					 temp.setVariable("idade",cliente.getIdade());
					 temp.setVariable("sexo", cliente.getSexo());
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
	
	@Override
	public String choose(ActionDone ad) {
		String resul=null;
		switch(ad.getAction()){
		case "cadastrar":
			resul = cadastrar(ad);
			break;
		}
		return resul;
	}

}
