package pedidos.useCases;

import pedidos.control.CrudController;
import pedidos.control.ModelController;
import pedidos.model.Cliente;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;

public class ManterCliente extends ModelController {
	private CrudController cc;
	
	public ManterCliente(){
		this.cc = new CrudController();
	};
	
	public ActionDone incluir( DoAction da){
		Cliente c = new Cliente();
		c.biuldObject(da);
		ActionDone ad = cc.save(c.getColumnName(),c.getColumnName(),c.getColumnValues());
		
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUserCase());
		ad.setStatus(true);
		ad.setMessage("Você foi cadastrado com sucesso.");
		return ad;
	};
	
	public ActionDone alterar( DoAction ad){
		return null;
	};
	
	public ActionDone excluir( DoAction ad){
		return null;
	};
	
	public ActionDone consultar( DoAction ad){
		return null;
	};
	
	@Override
	public String[] getActions() {
		String [] temp ={
							"incluir",
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
