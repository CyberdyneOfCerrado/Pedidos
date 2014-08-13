package pedidos.useCases;

import pedidos.control.ModelController;
import pedidos.cruds.CrudPedido;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;

public class ManterPedido extends ModelController {
	private CrudPedido cp;
	
	public ManterPedido(){
		super();
		cp = new CrudPedido();
	}
	
	public ActionDone listar( DoAction da){
		ActionDone ad = cp.selectAll(da);
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	@Override
	public String[] getActions() {
		String[] actions ={
							"listar",
							"cadastrar",
							"pedido"
						  };
		return actions;
	}

	@Override
	public String getUserCase() {
		return "manterPedido";
	}

}
