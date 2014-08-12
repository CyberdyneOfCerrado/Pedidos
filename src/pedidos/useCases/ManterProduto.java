package pedidos.useCases;

import pedidos.control.ModelController;
import pedidos.cruds.CrudProduto;
import pedidos.model.Cliente;
import pedidos.model.Produto;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;

public class ManterProduto extends ModelController {
	private CrudProduto cp;
	
	public ManterProduto(){
		cp = new CrudProduto();
	}
	
	public ActionDone cadastrar( DoAction da){
		Produto p = new Produto();
		p.biuldObject(da);
		
		ActionDone ad = cp.save(p);
		
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		ad.setMessage("Você foi cadastrado com sucesso.");
		return ad;
	};
	
	public ActionDone consultar( DoAction da){
		ActionDone ad = cp.select(da);
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
							"cadastrar",
							"alterar",
							"excluir",
							"consultar"
						  };
		return actions;
	}

	@Override
	public String getUserCase() {
		return "manterProduto";
	}

}
