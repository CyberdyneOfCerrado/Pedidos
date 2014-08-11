package pedidos.view;

import java.io.IOException;

import biz.source_code.miniTemplator.MiniTemplator;
import biz.source_code.miniTemplator.MiniTemplator.TemplateSyntaxException;
import pedidos.util.ActionDone;

public abstract class ViewController {
	private String sevletContext;
	private String useCase;
	private String separador;
	
	public ViewController(String sevletContext,String useCase ){
		separador = System.getProperty("file.separator");
		this.sevletContext = sevletContext;
		this.useCase = useCase+separador;
	};
	
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

	public abstract String choose( ActionDone ad );
}
