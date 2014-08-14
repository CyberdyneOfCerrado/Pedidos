package pedidos.control;
import java.sql.ResultSet;

import pedidos.util.GenericDAO;



public class CrudController {
	private GenericDAO dao;
	
	public CrudController(){
		dao = GenericDAO.getInstace();
	};
	
	public boolean run( String sql){
		return dao.run(sql);
	};
	
	public ResultSet runWithResult(String sql ){
		return dao.runWithResult(sql);
	};
	
}
