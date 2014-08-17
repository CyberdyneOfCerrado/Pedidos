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
		ActionDone ad = new ActionDone();
		
		if(validarCampos(da)){
			ad = ca.save(a);
			ad.setMessage("Cadastrado com sucesso.");
			ad.setStatus(true);
		}else{
			ad.setMessage("Dados inv�lidos.");
			ad.setStatus(false);
		}
		
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setProcessed(true);
		ad.setUseCase(da.getUseCase());
		return ad;
	};
	
	public ActionDone excluir( DoAction da){
		ActionDone ad = ca.delete(da);
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setMessage("Cliente exclu�do com sucesso!");
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
		ActionDone ad = new ActionDone();
		
		if(validarCampos(da)){
			ad = ca.update(a);
			ad.setMessage("Dados alterados com sucesso.");
			ad.setStatus(true);
		}else{
			ad.setMessage("Dados inv�lidos.");
			ad.setStatus(false);
		}
		
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone login ( DoAction da){
		
		
		ActionDone ad = ca.update(da);
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

	public boolean validarCampos( DoAction da ){
		String email = da.getData("email");
		String senha = da.getData("senha");
		String senhaConfir = da.getData("senhaConfir");
		
		if( email.equals("") || senha.equals("") || senhaConfir.equals("")){
			return false;
		}
		
		if( !senha.equals(senhaConfir)){
			return false;
		}
		
		return true;
	}
}
