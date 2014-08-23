package pedidos.viewUseCases;

import java.util.ArrayList;

import biz.source_code.miniTemplator.MiniTemplator;
import pedidos.model.Adm;
import pedidos.model.Cliente;
import pedidos.util.ActionDone;
import pedidos.view.ViewController;

//Classe que mant�m as vis�es do caso de uso adm.
//Cada m�todo dessa classe representa a vis�o de um cen�rio.
//H� duas formas de cada m�todo funcionar; com base no ActionDone processado ou n�o.
//Quando o ActionDone n�o foi processado, logo � uma a��o de redirecionamento, caso contr�rio,
//o m�todo cria a vis�o de resposta a um ActionDone processado.
public class ManterAdmView extends ViewController {

	public ManterAdmView(String sevletContext, String useCase) {
		super(sevletContext, useCase);
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
				 temp.setVariable("log",ad.getMessage());
				 resul = temp.generateOutput();
			}else{
				 temp = super.startMiniTemplator(super.getSevletContext()+ad.getUseCase()+super.getSeparador()+"alerta.html");
				 temp.setVariable("log",ad.getMessage());
				 resul = temp.generateOutput();
			}
		}
		return resul;
	}
	
	private String login( ActionDone ad){
		String resul = "";
		if(!ad.isProcessed()){
			MiniTemplator temp = super.startMiniTemplator(super.getTemplate(ad));
			resul = temp.generateOutput();
		}else{
			MiniTemplator temp;
			if(ad.isStatus()){//Mensagem de 'tudo bem'.
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"success.html");
				 temp.setVariable("log",ad.getMessage());
				 resul = temp.generateOutput();
			}else{
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"erro.html");
				 temp.setVariable("log",ad.getMessage());
				 resul = temp.generateOutput();
			}
		}
		return resul;
	}
	
	private String sair( ActionDone ad){
		String resul = "";
		if( ad.isProcessed()){
			MiniTemplator temp;
			if(ad.isStatus()){//Mensagem de 'tudo bem'.
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"success.html");
				 temp.setVariable("log",ad.getMessage());
				 resul = temp.generateOutput();
			}else{
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"erro.html");
				 temp.setVariable("log",ad.getMessage());
				 resul = temp.generateOutput();
			}
		}
		return resul;
	};
	
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
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"erro.html");
				 resul = temp.generateOutput();
			}
		}
		return resul;
	};
	
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
					 temp.setVariable("email",cliente.getIdade()); 
					 temp.addBlock("table");
				 }
				 resul = temp.generateOutput();
			}else{
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"erro.html");
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
			temp.setVariable("log",ad.getMessage());
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
				 temp.setVariable("log",ad.getMessage());
				 resul = temp.generateOutput();
			}else{
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"erro.html");
				 temp.setVariable("log",ad.getMessage());
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
		case "sair":
			resul = sair(ad);
			break;
		}
		return resul;
	}
}
