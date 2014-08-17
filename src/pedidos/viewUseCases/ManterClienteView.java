package pedidos.viewUseCases;

import java.util.ArrayList;
import java.util.Enumeration;

import biz.source_code.miniTemplator.MiniTemplator;
import pedidos.model.Cliente;
import pedidos.util.ActionDone;
import pedidos.view.ViewController;

public class ManterClienteView extends ViewController {

	public ManterClienteView(String sevletContext, String userCase) {
		super(sevletContext, userCase);
	}
	
	private String cadastrar ( ActionDone ad){
		String resul = "";
		if(!ad.isProcessed()){
			MiniTemplator temp = super.startMiniTemplator(super.getTemplate(ad));
			resul = temp.generateOutput();
		}else{
			MiniTemplator temp;
			
			if(ad.isStatus()){//Mensagem de 'tudo bem'.
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"success.html");
				 temp.setVariable("log",ad.getMessage());
				 resul = temp.generateOutput();
			}else{
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"erro.html");
				 temp.setVariable("log",ad.getMessage());
				 resul = temp.generateOutput();
			}
		}
		return resul;
	}
	
	private String consultar( ActionDone ad){
		String resul = "";
		if(!ad.isProcessed()){
			MiniTemplator temp = super.startMiniTemplator(super.getTemplate(ad));
			resul = temp.generateOutput();
		}else{
			MiniTemplator temp;
			
			if(ad.isStatus()){//Mensagem de 'tudo bem'.
				 temp = super.startMiniTemplator(super.getSevletContext()+ad.getUseCase()+super.getSeparador()+"row.html");
				 ArrayList<Cliente> arl = (ArrayList<Cliente>) ad.getData("search");
				 for(Cliente cliente : arl ){
					 
					 temp.setVariable("pk",cliente.getPk());
					 temp.setVariable("idade",cliente.getIdade());
					 temp.setVariable("sexo", cliente.getSexo());
					 temp.setVariable("nome", cliente.getNome());
					 
					 temp.addBlock("table");
				 }
				 
				 resul = temp.generateOutput();
			}else{
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"erro.html");
				 resul = temp.generateOutput();
			}
		}
		return resul;
	}
	
	private String categoria( ActionDone ad){
		String resul = "";
		if(!ad.isProcessed()){
			MiniTemplator temp = super.startMiniTemplator(super.getTemplate(ad));
			resul = temp.generateOutput();
		}else{
			MiniTemplator temp;
			
			if(ad.isStatus()){//Mensagem de 'tudo bem'.
				 temp = super.startMiniTemplator(super.getSevletContext()+ad.getUseCase()+super.getSeparador()+"categoriarow.html");
				 ArrayList<String> arl = (ArrayList<String>) ad.getData("todos");
				 temp.setVariable("valorPesquisa",(String)ad.getData("valor"));
				 temp.setVariable("search",(String)ad.getData("search"));
				 //pedido.pk,cliente.pk,nome,valor
				 for(String a : arl ){
					 String [] t = a.split(",");
					
					 temp.setVariable("nome",t[2]);
					 temp.setVariable("valor",t[3]);
					 temp.setVariable("pkPedido",t[0]);
					 temp.setVariable("pkCliente",t[1]);
					 
					 temp.addBlock("table");
				 }
				 
				 resul = temp.generateOutput();
			}else{
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"erro.html");
				 resul = temp.generateOutput();
			}
		}
		return resul;
	}
    
	private String listar( ActionDone ad){
		String resul = "";
		if(!ad.isProcessed()){
			MiniTemplator temp = super.startMiniTemplator(super.getTemplate(ad));
			resul = temp.generateOutput();
		}else{
			MiniTemplator temp;
			
			if(ad.isStatus()){//Mensagem de 'tudo bem'.
				 temp = super.startMiniTemplator(super.getSevletContext()+ad.getUseCase()+super.getSeparador()+"listar.html");
				 ArrayList<Cliente> arl = (ArrayList<Cliente>) ad.getData("todos");
				 for(Cliente cliente : arl ){
					 
					 temp.setVariable("pk",cliente.getPk());
					 temp.setVariable("idade",cliente.getIdade());
					 temp.setVariable("sexo", cliente.getSexo());
					 temp.setVariable("nome", cliente.getNome());
					 
					 temp.addBlock("table");
				 }
				 
				 resul = temp.generateOutput();
			}else{
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"erro.html");
				 resul = temp.generateOutput();
			}
		}
		return resul;
	};
	private String excluir( ActionDone ad){
		String resul = "";
		if(ad.isProcessed()){
			MiniTemplator temp;
			temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"success.html");
			
			temp.setVariable("mensagem",ad.getMessage());
				
			resul = temp.generateOutput();
		}else{
			MiniTemplator temp;
				 temp = super.startMiniTemplator(super.getSevletContext()+ad.getUseCase()+super.getSeparador()+"alerta.html");
				  
				 temp.setVariable("pk",(String) ad.getData("pk"));
				 resul = temp.generateOutput();
			
		}
		return resul;
	};
	
	private String alterar( ActionDone ad){
		String resul = "";
		if(!ad.isProcessed()){
			MiniTemplator temp = super.startMiniTemplator(super.getTemplate(ad));
			temp.setVariable("pk",(String) ad.getData("pk"));
			temp.setVariable("idade",(String) ad.getData("idade"));
			temp.setVariable("nome",(String) ad.getData("nome"));
			resul = temp.generateOutput();
		}else{
			MiniTemplator temp;
			
			if(ad.isStatus()){//Mensagem de 'tudo bem'.
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"success.html");
				 temp.setVariable("log",ad.getMessage());
				 resul = temp.generateOutput();
			}else{
				 temp = super.startMiniTemplator(super.getSevletContext()+"staff"+super.getSeparador()+"erro.html");
				 temp.setVariable("log",ad.getMessage());
				 resul = temp.generateOutput();
			}
		}
		return resul;
	};
	
	@Override
	public String choose(ActionDone ad) {
		String resul=null;
		switch(ad.getAction()){
		case "cadastrar":
			resul = cadastrar(ad);
			break;
		case "consultar":
			resul = consultar(ad);
			break;
		case "excluir":
			resul = excluir(ad);
			break;
		case "alterar":
			resul = alterar(ad);
			break;
		case "listar":
			resul = listar(ad);
			break;
		case "categoria":
			resul = categoria(ad);
			break;
		}
		return resul;
	}

}
