package pedidos.cruds;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pedidos.control.CrudController;
import pedidos.model.Cliente;
import pedidos.model.Pagamento;
import pedidos.util.ActionDone;
//Classe que gerencia o crud do modelo  Pagamento
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
		}finally{
			if(result != null)
				try {
					result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		ad.setData("pagamento",arl);
		return ad;
	}
}
