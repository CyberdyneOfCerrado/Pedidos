package pedidos.security;

import java.util.Hashtable;

public class Security {
	private Hashtable<String,String[]> acess;
	
	public Security(){
		acess = new Hashtable<>();
		//Adicinar apenas as a��es que s�o restritas a conta de Adm.
		
		String []manterAdm = {"excluir","alterar","cadastrar"};
		String []manterCliente = {"excluir","listar"};
		String []manterPedido  = {"excluir"};
		String []manterProduto = {"excluir"};
		
		acess.put("manterAdm", manterAdm);
		acess.put("manterPedido",manterPedido);
		acess.put("manterProduto",manterProduto);
		acess.put("manterCliente",manterCliente);
	}
	
	//Com base na a��o e caso de uso e o tipo de usu�rio, esse m�todo diz se a a��o e permitida
	//ou n�o.
	public boolean permissao( String login , String useCase , String action){
		boolean blackList = false;
		if(useCase==null || action == null)return true;
		String[] actions = acess.get(useCase);
		if(actions == null ) return true;
		for(String a : actions){
			if( a.equals(action))blackList = true;
		}
		if( (login == null || login.equals("false")) && blackList){
			return false;
		}
		return true;
	};
}
