package pedidos.viewUseCases;

import biz.source_code.miniTemplator.MiniTemplator;
import pedidos.util.ActionDone;
import pedidos.view.ViewController;

public class ManterClienteView extends ViewController {

	public ManterClienteView(String sevletContext, String userCase) {
		super(sevletContext, userCase);
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
		MiniTemplator temp = super.startMiniTemplator(super.getTemplate(ad));
		return temp.generateOutput();
	}

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
		}
		return resul;
	}

}
