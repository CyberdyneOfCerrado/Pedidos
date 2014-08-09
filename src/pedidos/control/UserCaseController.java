package pedidos.control;

import java.lang.reflect.Method;
import java.util.Hashtable;

import pedidos.util.ActionDone;
import pedidos.util.DoAction;


public class UserCaseController {
	Hashtable<String,ModelController> listUserCase;
	
	public UserCaseController(){
		listUserCase = new Hashtable<>(); 
		
		//Casos de uso novos devem ser adicionados aqui.
		//listUserCase.put("manterUsuario", new UsuarioController());
	};
	
	@SuppressWarnings("unchecked")
	public ActionDone chooseUserCase(DoAction doAction){
		//1: procurar o caso de uso na hashtable.;
		//2: procurar a ação no caso de uso;
		//3: procurar o método que executará o doAction;
		//4: invocar o método passando o doAction.
		
		//Validado se a requisição chegou nula.
		if(doAction.getUserCase() == null){
			return copy(doAction);
		}
		ModelController userCase = listUserCase.get(doAction.getUserCase());
		
		//Validando existência do caso de uso.
		if( userCase == null ){
			//Gerar um actionDone
			return copy(doAction);
		}
		//Fim validando caso de uso.
		
		String[] actions = userCase.getActions();
		
		//Validando existência da ação
		boolean temp = false;
		for(String a : actions)if( a.equals(doAction.getAction())) temp = true;
		if( !temp ){
			//A ação informada não existe.
			return copy(doAction);
		}
		//Fim validado ações.
		
		//----Atuando com reflexão------
		@SuppressWarnings("rawtypes")
		Class classe = userCase.getClass();
		ActionDone actionDone = null;
		try {
			Method m   = classe.getMethod(doAction.getAction(),DoAction.class);
			actionDone = (ActionDone) m.invoke(userCase,doAction);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Ocorreu um erro na ignição do método.");
		}
		return actionDone;
	};
	
	private ActionDone copy(DoAction da){
		return new ActionDone(da.getUserCase(),da.getAction());
	};
}
