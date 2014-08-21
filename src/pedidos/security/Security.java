package pedidos.security;

import java.util.Hashtable;

public class Security {
	private Hashtable<String,String[]> acess;
	
	public Security(){
		acess = new Hashtable<>();
		//Adicioar apenas as ações que será restritas a conta de Adm.
		
		String []manterAdm = {"excluir","alterar","cadastrar"};
		String []manterCliente = {"excluir","listar"};
		String []manterPedido  = {"excluir"};
		String []manterProduto = {"excluir"};
		
		acess.put("manterAdm", manterAdm);
		acess.put("manterPedido",manterPedido);
		acess.put("manterProduto",manterProduto);
		acess.put("manterCliente",manterCliente);
	}
	
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
