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
	
	public ActionDone cadastrar( DoAction da){
		Cliente c = new Cliente();
		c.biuldObject(da);
		ActionDone ad = cc.save(c.getTableName(),c.getColumnName(),c.getColumnValues());
		
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
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
