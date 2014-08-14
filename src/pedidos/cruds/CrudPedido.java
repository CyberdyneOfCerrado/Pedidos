package pedidos.cruds;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pedidos.control.CrudController;
import pedidos.model.Cliente;
import pedidos.model.Produto;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;

public class CrudPedido extends CrudController {
	public CrudPedido(){
		super();
	};
	
	public ActionDone resgataProdutos ( DoAction da ){
		ActionDone ad = new ActionDone();
		String sql = "select produto_id from pedido_produto where pedido_id = " + da.getData("pkPedido");
		ResultSet result = super.runWithResult(sql);
		ArrayList<String> keys = new ArrayList<>();
		try {
			while( result.next()){
					keys.add(result.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<Produto> arl = new ArrayList<>();
		try {
			for(String key : keys){
				sql ="select * from produto where pk = "+ key;
				result = super.runWithResult(sql);
				while( result.next()){
						arl.add(new Produto(Integer.parseInt(result.getString(1)),
															result.getString(2),
											Integer.parseInt(result.getString(3))));
						
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ad.setData("todos",arl);
		return ad;
	};
	
	public ActionDone selectAll ( DoAction da ){
		ActionDone ad = new ActionDone();
		String sql = "select * from cliente";
		ResultSet result = super.runWithResult(sql);
		ArrayList<Cliente> arl = new ArrayList<>();
		try {
			while( result.next()){
					arl.add(new Cliente(Integer.parseInt(result.getString(1)),
														result.getString(2),
										Integer.parseInt(result.getString(3)),
														result.getString(4)));
					System.out.println(result.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ad.setData("todos",arl);
		return ad;
	};
	
	public String criarPedido(DoAction da) {

		String sql = "insert into pedido (valor,cliente_id) values (0,"+da.getData("pk")+")";
		super.run(sql);
		
		ResultSet result = super.runWithResult("select pk from pedido where pk = (select max(pk) from pedido )");
		try {
			while( result.next()) return result.getString(1);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";		
	};

	public ActionDone cadastrar(DoAction da) {
		String table  = "pedido_produto"; 
		ActionDone ad = new ActionDone();
		String sql ="insert into " +table +" (pedido_id,produto_id) values ("+da.getData("idPedido")+","+da.getData("pkProduto")+")";
		
		if(super.run(sql)){
			ad.setMessage("Os Dados foram adicionados com sucesso");
		}else{
			ad.setMessage("Ocorreu algum erro.");
		}
		return ad;
	};
	
	public ActionDone atualizarPedido(DoAction da) {
		int valor=0;
		ActionDone ad = new ActionDone();
		String sql ="select valor from pedido where pk = "+da.getData("idPedido");
		
		ResultSet result = super.runWithResult(sql);
		try {
			while( result.next()) valor = Integer.parseInt(result.getString(1));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int temp = Integer.parseInt(da.getData("preco"));
		valor += temp;
		sql = "update pedido set valor = "+ valor+" where pk = "+da.getData("idPedido");
		if(super.run(sql)){
			ad.setMessage("Os Dados foram adicionados com sucesso");
		}else{
			ad.setMessage("Ocorreu algum erro.");
		}
		return ad;
	};

	public ActionDone delete(DoAction da) {
		String pkPedido = da.getData("pkPedido");
		String sql = "select pk from pedido_produto where pedido_id = "+pkPedido;
		ArrayList<String> keys = new ArrayList<>();
		
		ResultSet result = super.runWithResult( sql );
		try {
			while( result.next()){
				 keys.add(result.getString(1));
			}
			
			for(String key : keys){
				sql = "delete from pedido_produto where pk = " + key;
				super.run(sql);
			}
			
			super.run("delete from pedido where pk = "+pkPedido);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ActionDone();
	}

	public ActionDone removeProduto(DoAction da) {
		int valor=0;
		ActionDone ad = new ActionDone();
		String sql ="select pk from pedido_produto where produto_id = "+da.getData("pkProduto")+" and pedido_id = " + da.getData("idPedido");
		String key = null;
		ResultSet result = super.runWithResult(sql);
		try {
			while( result.next()) key = result.getString(1);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		super.run("delete from pedido_produto where pk = " + key);
		
		result = super.runWithResult("select valor from pedido where pk = "+ da.getData("idPedido"));
		try {
			while( result.next()) key = result.getString(1);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		valor = Integer.parseInt(key);
		int temp = Integer.parseInt((String) da.getData("preco"));
		sql = "update pedido set valor = "+ (valor - temp) +" where pk = "+da.getData("idPedido");
		if(super.run(sql)){
			ad.setMessage("Os Dados foram adicionados com sucesso");
		}else{
			ad.setMessage("Ocorreu algum erro.");
		}
		return ad;
		
	};
}
