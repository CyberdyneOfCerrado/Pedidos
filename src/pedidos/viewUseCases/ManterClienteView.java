package pedidos.viewUseCases;

import pedidos.util.ActionDone;
import pedidos.view.ViewController;

public class ManterClienteView extends ViewController {

	public ManterClienteView(String sevletContext, String userCase) {
		super(sevletContext, userCase);
	}
	
	private String incluir ( ActionDone ad){
		
		return null;
	}

	@Override
	public String choose(ActionDone ad) {
		String resul;
		switch(ad.getAction()){
		case "incluir":
			break;
		}
		return null;
	}

}
