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
}
