package pedidos.view;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import pedidos.control.UserCaseController;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;
import biz.source_code.miniTemplator.MiniTemplator;
import biz.source_code.miniTemplator.MiniTemplator.TemplateSyntaxException;

//Cria o pacote geral de comunica��o da aplica��o 'DoAction' e gerencia as classes geradoreas de conte�do.
public class ServletController {
	private String servletContext;
	private UserCaseController ucc;
	private Hashtable<String,ViewController> listViews;
	String separador;
	
	public ServletController( String servletContext){
        separador = System.getProperty("file.separator");
		this.servletContext = servletContext+"templates"+separador;
		this.ucc = new UserCaseController();
		this.listViews = new Hashtable<>();
		initViews();
	};
	
	//Adicionar todas as classes de gerenciamento de conte�do aqui.
	private void initViews(){
		//listViews.put("manterUsuario", new ManterUsuarioView(servletContext,"manterUsuario"));
	};
	
	private String init() throws TemplateSyntaxException, IOException{
		MiniTemplator index = null;
		index = new MiniTemplator(servletContext+"index.html");
		index.setVariable("conteudo"," ");
		return index.generateOutput();
	};	
	
	//Processa os dados da Servlet.
	public String process(HttpServletRequest request) throws TemplateSyntaxException, IOException{
		DoAction   da = makeDoAction(request);
		ActionDone ad=null;
		if(da.getAction()==null){
			return init();
		}else{
		//Se a��o ao informada for do tipo 'redirect' os dados n�o devem ser enviados
			if( da.getHashtable().get("redirect").equals("false")){
				ad = ucc.chooseUserCase(da);
				ad.setData("redirect","false");
			}else{
				ad = new ActionDone(da.getUserCase(),da.getAction());
				ad.setData("redirect","true");
			}
		}
		return readActionDone(ad);
	};
	
	//Cria um pacote DoAction
	private DoAction makeDoAction(HttpServletRequest request){
		//1 pegando o nome do caso de uso e a respectiva a��o.
		String useCase = request.getParameter("useCase");
		String action   = request.getParameter("action");
		
		DoAction da = new DoAction(useCase,action);
		
		//Pegando todos os par�metros adicionados, exceto pelo userCase e action;
		Enumeration<String> valuesName = request.getParameterNames();
		
		while(valuesName.hasMoreElements()){
			String temp = valuesName.nextElement();
			if( !temp.equals("useCase") && !temp.equals("action")){
				da.setData(temp,request.getParameter(temp));
			}
		}
		return da;
	};
	
	//Interpreta os dados do pacote ActionDone.
	private String readActionDone(ActionDone ad){
		//Condi��o trivial caso estes dois par�metros sejam nulos. nulos v�o para a tela de login.
		String conteudo = null;
		//gerando o conte�do.
		 ViewController view = listViews.get(ad.getUseCase());
		
		 conteudo  = view.choose(ad);
		
		//Fixando conte�do na index.
		MiniTemplator index = null;
		try {
			System.out.println(servletContext+"index.html");
			index = new MiniTemplator(servletContext+"index.html");
		} catch (TemplateSyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index.setVariable("conteudo",conteudo);
		return index.generateOutput();
	};
}
