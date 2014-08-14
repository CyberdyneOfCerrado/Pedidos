package pedidos.useCases;

import pedidos.control.ModelController;
import pedidos.cruds.CrudAdm;
import pedidos.model.Adm;
import pedidos.model.Cliente;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;

public class ManterAdm extends ModelController {
	private CrudAdm ca;
	
	public ManterAdm(){
		super();
		ca = new CrudAdm();
	}
	
	public ActionDone cadastrar( DoAction da){
		Adm a = new Adm();
		a.biuldObject(da);
		
		ActionDone ad = ca.save(a);
		
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		ad.setMessage("Você foi cadastrado com sucesso.");
		return ad;
	};
	
	public ActionDone excluir( DoAction da){
		ActionDone ad = ca.delete(da);
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setMessage("Cliente excluído com sucesso!");
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone consultar( DoAction da){
		ActionDone ad = ca.select(da);
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone alterar( DoAction da){
		Adm a = new Adm();
		a.biuldObject(da);
		
		ActionDone ad = ca.update(a);
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone login ( DoAction da){
		
		
		ActionDone ad = ca.update(a);
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	@Override
	public String[] getActions() {
		String[] a ={
						"login",
						"sair",
						"alterar",
						"excluir",
						"cadastrar",
						"consultar"
					};
		return a;
	}

	@Override
	public String getUserCase() {
		return "manterAdm";
	}

}
