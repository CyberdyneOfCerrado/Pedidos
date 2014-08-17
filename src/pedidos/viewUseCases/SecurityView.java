package pedidos.viewUseCases;

import biz.source_code.miniTemplator.MiniTemplator;
import pedidos.util.ActionDone;
import pedidos.view.ViewController;

public class SecurityView extends ViewController {

	public SecurityView(String sevletContext, String useCase) {
		super(sevletContext, useCase);
	}

	public String choose(ActionDone ad) {
		String resul=null;
		switch(ad.getAction()){
		case "acessBlock":
			resul = acessBlock(ad);
			break;
		}
		return resul;
	}

	private String acessBlock(ActionDone ad) {
		String resul;
		MiniTemplator temp;
		temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"erro.html");
		temp.setVariable("log","É necessário ser um administrador para executar essa ação.");
		resul = temp.generateOutput();
		return resul;
	}

}
