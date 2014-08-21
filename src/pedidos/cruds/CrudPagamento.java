package pedidos.cruds;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pedidos.control.CrudController;
import pedidos.model.Cliente;
import pedidos.model.Pagamento;
import pedidos.util.ActionDone;

public class CrudPagamento extends CrudController {
	
	public CrudPagamento(){
		super();
	}
	
	public ActionDone selectAll(){
		ActionDone ad = new ActionDone();
		String sql = "select * from pagamento";
		ResultSet result = super.runWithResult(sql);
		ArrayList<Pagamento> arl = new ArrayList<>();
		try {
			while( result.next()){
					arl.add(new Pagamento(Integer.parseInt(result.getString(1)),
														result.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ad.setData("pagamento",arl);
		return ad;
	}
}
