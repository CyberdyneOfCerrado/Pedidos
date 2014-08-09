package pedidos.util;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenericDAO {
	private final String conector = "jdbc:postgresql://localhost:5432/pechincha";
	private final String user = "postgres";
	private final String senha= "123456";
	Statement executor;
	
	//Carrega os drivers necessários para se criar um executor.
	public GenericDAO(){
		 try {
		      Class.forName("org.postgresql.Driver");
		      System.out.println("JDBC driver carregado.");
		    }
		    catch (ClassNotFoundException e) {
		      System.out.println(e.toString());
		    }
		 try {
			executor = DriverManager.getConnection(conector,user,senha).createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	};
	
	public boolean run( String sql ){
		try {
			return executor.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	};
	
	public ResultSet runWithResult(String sql ){
		try {
			return executor.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
