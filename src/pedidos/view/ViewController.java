package pedidos.view;

import java.io.IOException;

import biz.source_code.miniTemplator.MiniTemplator;
import biz.source_code.miniTemplator.MiniTemplator.TemplateSyntaxException;
import pedidos.util.ActionDone;
//Classe com o comportamento gen�rico de uma classe de controle de vis�o de caso de uso.

public abstract class ViewController {
	private String sevletContext;
	private String useCase;
	private String separador;
	
	//Pega o contexto da servlet e cria um caminho de diret�rio com base no nome do caso de uso.
	public ViewController(String sevletContext,String useCase ){
		separador = System.getProperty("file.separator");
		this.sevletContext = sevletContext;
		this.useCase = useCase+separador;
	};
	
	//M�todo gen�rico para instanciar o MiniTemplator.
	protected MiniTemplator startMiniTemplator(String path){
		MiniTemplator mi = null;
		try {
			System.out.println(path);
			mi = new MiniTemplator(path);
		} catch (TemplateSyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mi;
	};
	
	//M�todo que devolve o caminho total de template.
	protected String getTemplate (ActionDone ad){
		String temp = getSevletContext()+getUseCase()+ad.getAction()+".html";
		System.out.println(temp);
		return temp;
	};
	
	public String getSevletContext() {
		return sevletContext;
	}

	public String getUseCase() {
		return useCase;
	}

	public String getSeparador() {
		return separador;
	}
	
	//Comportamento que deve ser abstra�do para as classes filhas para garantir a interatividade com
	//a classe ServletController
	public abstract String choose( ActionDone ad );
}
