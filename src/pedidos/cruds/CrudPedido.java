package pedidos.cruds;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pedidos.control.CrudController;
import pedidos.model.Cliente;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;

public class CrudPedido extends CrudController {
	public CrudPedido(){
		super();
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
	}
	
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
	}

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
}
