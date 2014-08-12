package pedidos.useCases;

import pedidos.control.ModelController;
import pedidos.cruds.CrudCliente;
import pedidos.model.Cliente;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;

public class ManterCliente extends ModelController {
	private CrudCliente cc;
	
	public ManterCliente(){
		this.cc = new CrudCliente();
	};
	
	public ActionDone cadastrar( DoAction da){
		Cliente c = new Cliente();
		c.biuldObject(da);
		
		ActionDone ad = cc.save(c);
		
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		ad.setMessage("Você foi cadastrado com sucesso.");
		return ad;
	};
	
	public ActionDone alterar( DoAction ad){
		return null;
	};
	
	public ActionDone excluir( DoAction ad){
		return null;
	};
	
	public ActionDone consultar( DoAction da){
		ActionDone ad = cc.select(da);
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	@Override
	public String[] getActions() {
		String [] temp ={
							"cadastrar",
							"alterar",
							"excluir",
							"consultar"
						};
		return temp;
	};

	@Override
	public String getUserCase() {
		return "manterCliente";
	};
}
