package pedidos.control;
import java.sql.ResultSet;

import pedidos.util.GenericDAO;



public class CrudController {
	private GenericDAO dao;
	private static CrudController cc = null;
	
	public CrudController(){
		dao = new GenericDAO();
	};
	
	public static CrudController getInstace(){
		if(cc == null) return cc = new CrudController();
		return cc;
	};
	
	public boolean run( String sql){
		return dao.run(sql);
	};
	
	public ResultSet runWithResult(String sql ){
		return dao.runWithResult(sql);
	};
	
}
