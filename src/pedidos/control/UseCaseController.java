package pedidos.control;

import java.lang.reflect.Method;

import pedidos.util.ActionDone;
import pedidos.util.Converter;
import pedidos.util.DoAction;

//Esta classe procura pela classe de UseCase mais adequada para atuar na requisição
public class UseCaseController {

	public UseCaseController(){
	};
	
	@SuppressWarnings("unchecked")
	public ActionDone chooseUserCase(DoAction doAction){
		//1: procurar o caso de uso na hashtable;
		//2: procurar a ação no caso de uso;
		//3: procurar o método que executará o doAction;
		//4: invocar o método passando o doAction.
		
		//Validado se a requisição chegou nula.
		if(doAction.getUseCase() == null){
			return copy(doAction);
		}
		
		//MODIFICAÇÃO 1;
		//Pegando o nome do pacote, que é estático para todos as classes de caso de uso; usando o método firstUpperCase
		//para transformar a primeira letra em maiúsculas e o resto não é necessário implementar qualquer mudanças. 
		ModelController useCase = null;
		try {
			useCase = (ModelController) Class.forName("pedidos.useCases."+ Converter.firstUpperCase(doAction.getUseCase())).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		
		//Validando existência do caso de uso.
		if( useCase == null ){
			//Gerar um actionDone
			return copy(doAction);
		}
		
		//Fim validando caso de uso.

		
		//Validando existência da ação
		
		//MODIFICAÇÃO 2; 
		//Não é mais necessário eu resgatar os nomes dos cenários dentro da classe de leilão, já que existe um padrão entre os dados 
		//oriundos das telas e dos métodos codificados dentro das classes de caso de uso. Dessa maneira só é necessário resgatar o método
		//apartir do nome. Linha 61.
		Method m = null;
		Class classe = useCase.getClass(); 
		try {
			m = classe.getDeclaredMethod(doAction.getAction(),DoAction.class);
		} catch (NoSuchMethodException | SecurityException e1) {
			throw new RuntimeException("Error ao tentar pegar uma um método dentro de uma classe, UseCaseController", e1); 
		} 
		
		if( m == null ){
			//A ação informada não existe.
			return copy(doAction);
		}
		//Fim da validação das ações.
		
		//----Atuando com reflexão------
		@SuppressWarnings("rawtypes")
		
		ActionDone actionDone = null;
		try {
			actionDone = (ActionDone) m.invoke(useCase,doAction);//casting p/ action done de um object (resultado do invoke)
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Ocorreu um erro na ignição do método.");
		}
		return actionDone;
	};
	
	private ActionDone copy(DoAction da){
		return new ActionDone(da.getUseCase(),da.getAction());
	};
}
