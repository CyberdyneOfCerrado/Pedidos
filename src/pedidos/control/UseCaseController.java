package pedidos.control;

import java.lang.reflect.Method;
import java.util.Hashtable;

import pedidos.useCases.ManterAdm;
import pedidos.useCases.ManterCliente;
import pedidos.useCases.ManterPedido;
import pedidos.useCases.ManterProduto;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;

//Esta classe procura pela classe de UseCase mais adequada para atuar na requisi��o
public class UseCaseController {
	Hashtable<String,ModelController> listUserCase;
	
	public UseCaseController(){
		listUserCase = new Hashtable<>(); 
		
		//Casos de uso novos devem ser adicionados aqui.
		 listUserCase.put("manterCliente", new ManterCliente());
		 listUserCase.put("manterProduto", new ManterProduto());
		 listUserCase.put("manterPedido", new ManterPedido());
		 listUserCase.put("manterAdm", new ManterAdm());
	};
	
	@SuppressWarnings("unchecked")
	public ActionDone chooseUserCase(DoAction doAction){
		//1: procurar o caso de uso na hashtable.;
		//2: procurar a a��o no caso de uso;
		//3: procurar o m�todo que executar� o doAction;
		//4: invocar o m�todo passando o doAction.
		
		//Validado se a requisi��o chegou nula.
		if(doAction.getUseCase() == null){
			return copy(doAction);
		}
		ModelController useCase = listUserCase.get(doAction.getUseCase());
		
		//Validando exist�ncia do caso de uso.
		if( useCase == null ){
			//Gerar um actionDone
			return copy(doAction);
		}
		//Fim validando caso de uso.
		
		String[] actions = useCase.getActions();
		
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
		Class classe = useCase.getClass();
		ActionDone actionDone = null;
		try {
			Method m   = classe.getMethod(doAction.getAction(),DoAction.class);
			actionDone = (ActionDone) m.invoke(useCase,doAction);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Ocorreu um erro na igni��o do m�todo.");
		}
		return actionDone;
	};
	
	private ActionDone copy(DoAction da){
		return new ActionDone(da.getUseCase(),da.getAction());
	};
}
