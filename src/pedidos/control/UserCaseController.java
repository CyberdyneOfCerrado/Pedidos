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
		//2: procurar a a��o no caso de uso;
		//3: procurar o m�todo que executar� o doAction;
		//4: invocar o m�todo passando o doAction.
		
		//Validado se a requisi��o chegou nula.
		if(doAction.getUserCase() == null){
			return copy(doAction);
		}
		ModelController userCase = listUserCase.get(doAction.getUserCase());
		
		//Validando exist�ncia do caso de uso.
		if( userCase == null ){
			//Gerar um actionDone
			return copy(doAction);
		}
		//Fim validando caso de uso.
		
		String[] actions = userCase.getActions();
		
		//Validando exist�ncia da a��o
		boolean temp = false;
		for(String a : actions)if( a.equals(doAction.getAction())) temp = true;
		if( !temp ){
			//A a��o informada n�o existe.
			return copy(doAction);
		}
		//Fim validado a��es.
		
		//----Atuando com reflex�o------
		@SuppressWarnings("rawtypes")
		Class classe = userCase.getClass();
		ActionDone actionDone = null;
		try {
			Method m   = classe.getMethod(doAction.getAction(),DoAction.class);
			actionDone = (ActionDone) m.invoke(userCase,doAction);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Ocorreu um erro na igni��o do m�todo.");
		}
		return actionDone;
	};
	
	private ActionDone copy(DoAction da){
		return new ActionDone(da.getUserCase(),da.getAction());
	};
}
