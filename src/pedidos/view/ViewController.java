package pedidos.view;

import java.io.IOException;

import biz.source_code.miniTemplator.MiniTemplator;
import biz.source_code.miniTemplator.MiniTemplator.TemplateSyntaxException;
import pedidos.util.ActionDone;

public abstract class ViewController {
	private String sevletContext;
	private String userCase;
	private String separador;
	
	public ViewController(String sevletContext,String userCase ){
		separador = System.getProperty("file.separator");
		this.sevletContext = sevletContext;
		this.userCase = userCase+separador;
	};
	
	private MiniTemplator startMiniTemplator(String path){
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
	
	public abstract String choose( ActionDone ad );
}
