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
	
	public ActionDone excluir( DoAction da){
		ActionDone ad = cc.delete(da);
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setMessage("Cliente excluído com sucesso!");
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
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
	
	public ActionDone categoria( DoAction da){
		ActionDone ad = cc.categoria(da);
		//Identificando o pacote
		ad.setData("search",da.getData("search"));
		ad.setData("valor",da.getData("valor"));
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone alterar( DoAction da){
		Cliente c = new Cliente();
		c.biuldObject(da);
		ActionDone ad = cc.update(c);
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone listar( DoAction da){
		ActionDone ad = cc.selectAll(da);
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
							"consultar",
							"categoria",
							"listar"
						};
		return temp;
	};

	@Override
	public String getUserCase() {
		return "manterCliente";
	};
}
