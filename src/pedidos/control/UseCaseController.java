package pedidos.control;

import java.lang.reflect.Method;

import pedidos.util.ActionDone;
import pedidos.util.Converter;
import pedidos.util.DoAction;

//Esta classe procura pela classe de UseCase mais adequada para atuar na requisi��o
public class UseCaseController {

	public UseCaseController(){
	};
	
	@SuppressWarnings("unchecked")
	public ActionDone chooseUserCase(DoAction doAction){
		//1: procurar o caso de uso na hashtable;
		//2: procurar a a��o no caso de uso;
		//3: procurar o m�todo que executar� o doAction;
		//4: invocar o m�todo passando o doAction.
		
		//Validado se a requisi��o chegou nula.
		if(doAction.getUseCase() == null){
			return copy(doAction);
		}
		
		//MODIFICA��O 1;
		//Pegando o nome do pacote, que � est�tico para todos as classes de caso de uso; usando o m�todo firstUpperCase
		//para transformar a primeira letra em mai�sculas e o resto n�o � necess�rio implementar qualquer mudan�as. 
		ModelController useCase = null;
		try {
			useCase = (ModelController) Class.forName("pedidos.useCases."+ Converter.firstUpperCase(doAction.getUseCase())).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		
		//Validando exist�ncia do caso de uso.
		if( useCase == null ){
			//Gerar um actionDone
			return copy(doAction);
		}
		
		//Fim validando caso de uso.

		
		//Validando exist�ncia da a��o
		
		//MODIFICA��O 2; 
		//N�o � mais necess�rio eu resgatar os nomes dos cen�rios dentro da classe de leil�o, j� que existe um padr�o entre os dados 
		//oriundos das telas e dos m�todos codificados dentro das classes de caso de uso. Dessa maneira s� � necess�rio resgatar o m�todo
		//apartir do nome. Linha 61.
		Method m = null;
		Class classe = useCase.getClass(); 
		try {
			m = classe.getDeclaredMethod(doAction.getAction(),DoAction.class);
		} catch (NoSuchMethodException | SecurityException e1) {
			throw new RuntimeException("Error ao tentar pegar uma um m�todo dentro de uma classe, UseCaseController", e1); 
		} 
		
		if( m == null ){
			//A a��o informada n�o existe.
			return copy(doAction);
		}
		//Fim da valida��o das a��es.
		
		//----Atuando com reflex�o------
		@SuppressWarnings("rawtypes")
		
		ActionDone actionDone = null;
		try {
			actionDone = (ActionDone) m.invoke(useCase,doAction);//casting p/ action done de um object (resultado do invoke)
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
