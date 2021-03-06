package pedidos.useCases;

import javax.servlet.http.HttpSession;

import pedidos.control.ModelController;
import pedidos.cruds.CrudAdm;
import pedidos.model.Adm;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;
//Classe que mantem um Adm no sistema, seus m�todos s�o cen�rios.
public class ManterAdm extends ModelController<Adm> {
	private CrudAdm ca;
	
	public ManterAdm(){
		super();
		ca = new CrudAdm();
	}
	
	public ActionDone cadastrar( DoAction da){
		Adm a = new Adm();
		a.biuldObject(da);
		ActionDone ad = new ActionDone();
		String senhaConfir = (String) da.getData("senhaConfir");
		
		if(validar(a) && a.getSenha().equals(senhaConfir)){
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
		HttpSession s = (HttpSession) da.getData("Session");
		
		//Matando a sess�o do usu�rio que foi removido do bd.
		s.setAttribute("login","false");
		
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
		String senhaConfir = (String) da.getData("senhaConfir");
		
		if(validar(a) && a.getSenha().equals(senhaConfir)){
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
		ActionDone ad = ca.verificarLogin(da);
		String exist = String.valueOf(ad.getData("exist"));
		if(exist.equals("true")){
			HttpSession session = (HttpSession) da.getData("Session");
			session.setAttribute("login","true");
			ad.setMessage("Voc� est� logado.");
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
	
	public ActionDone sair( DoAction da){
		ActionDone ad = new ActionDone();
		
		HttpSession session = (HttpSession) da.getData("Session");
		String exist = (String) session.getAttribute("login");
		
		if( exist == null || exist.equals("false") ){
			ad.setMessage("Voc� n�o est� logado.");
			ad.setStatus(false);
		}else{
			session.setAttribute("login","false");
			ad.setMessage("Voc� foi deslogado com sucesso.");
			ad.setStatus(true);
		}
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setProcessed(true);
		return ad;
	};

	@Override
	public boolean validarCustom(Adm o) {
		if( o.getEmail().equals("") || o.getSenha().equals("") || o.getSenha().equals("") ||
			o.getSenha().length() >=16 || o.getEmail().length() >= 45) return false;
		return true;
	}
}
