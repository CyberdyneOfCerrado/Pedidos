package pedidos.viewUseCases;

import java.util.ArrayList;

import biz.source_code.miniTemplator.MiniTemplator;
import pedidos.model.Adm;
import pedidos.model.Cliente;
import pedidos.util.ActionDone;
import pedidos.view.ViewController;


public class ManterAdmView extends ViewController {

	public ManterAdmView(String sevletContext, String useCase) {
		super(sevletContext, useCase);
		// TODO Auto-generated constructor stub
	}
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
	
	private String login( ActionDone ad){
		MiniTemplator temp = super.startMiniTemplator(super.getTemplate(ad));
		return temp.generateOutput();
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
				 ArrayList<Adm> arl = (ArrayList<Adm>) ad.getData("search");
				 for(Adm a : arl ){
					 
					 temp.setVariable("pk",a.getPk());
					 temp.setVariable("email",a.getEmail());
					 
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
	
	
	private String listar( ActionDone ad){
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
			temp.setVariable("email",(String) ad.getData("email"));
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
		case "login":
			resul = login(ad);
			break;
		case "consultar":
			resul = consultar(ad);
			break;
		case "excluir":
			resul = excluir(ad);
			break;
		case "alterar":
			resul = alterar(ad);
			break;
		case "listar":
			resul = listar(ad);
			break;
		}
		return resul;
	}
}
