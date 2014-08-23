package pedidos.useCases;

import javax.servlet.http.HttpSession;

import pedidos.control.ModelController;
import pedidos.cruds.CrudAdm;
import pedidos.model.Adm;
import pedidos.model.Cliente;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;
//Classe que mantem um Adm no sistema, seus métodos são cenários.
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
			ad.setMessage("Dados inválidos.");
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
		HttpSession s = (HttpSession) da.getData("Session");
		
		//Matando a sessão do usuário que foi removido do bd.
		s.setAttribute("login","false");
		
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
		ActionDone ad = new ActionDone();
		
		if(validarCampos(da)){
			ad = ca.update(a);
			ad.setMessage("Dados alterados com sucesso.");
			ad.setStatus(true);
		}else{
			ad.setMessage("Dados inválidos.");
			ad.setStatus(false);
		}
		
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone login ( DoAction da){
		ActionDone ad = ca.verificarLogin(da);
		String exist = String.valueOf(ad.getData("exist"));
		if(exist.equals("true")){
			HttpSession session = (HttpSession) da.getData("Session");
			session.setAttribute("login","true");
			ad.setMessage("Você está logado.");
			ad.setStatus(true);
		}else{
			ad.setMessage("Dados inválidos.");
			ad.setStatus(false);
		}
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone sair( DoAction da){
		ActionDone ad = new ActionDone();
		
		HttpSession session = (HttpSession) da.getData("Session");
		String exist = (String) session.getAttribute("login");
		
		if( exist == null || exist.equals("false") ){
			ad.setMessage("Você não está logado.");
			ad.setStatus(false);
		}else{
			session.setAttribute("login","false");
			ad.setMessage("Você foi deslogado com sucesso.");
			ad.setStatus(true);
		}
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
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
		String email = (String) da.getData("email");
		String senha = (String) da.getData("senha");
		String senhaConfir = (String) da.getData("senhaConfir");
		if( email.equals("") || senha.equals("") || senhaConfir.equals(""))return false;
		if( !senha.equals(senhaConfir))return false;
		return true;
	}
}
