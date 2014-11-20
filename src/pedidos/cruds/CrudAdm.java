package pedidos.cruds;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pedidos.control.CrudController;
import pedidos.model.Adm;
import pedidos.model.Cliente;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;
//Classe que gerencia o crud do modelo ADM
public class CrudAdm extends CrudController {

	public ActionDone update ( Adm adm ){
		String table = adm.getTableName();
		String columns = adm.getColumnName();
		String values = adm.getColumnValues();
		String pk = String.valueOf(adm.getPk());
		
		ActionDone ad = new ActionDone();
		String sql ="update "+ table+" set ";
		String[] col = columns.split(",");
		String[] val = values.split(",");
		
		for( int a = 0 ; a < col.length ; a++ )sql += col[a]+"="+val[a]+", ";
		sql = sql.substring(0,sql.length()-2);
		sql +=" where pk = "+pk;
		
		if(super.run(sql)){
			ad.setMessage("Os Dados foram adicionados com sucesso");
		}else{
			ad.setMessage("Ocorreu algum erro.");
		}
		return ad;
	};
	
	public ActionDone save   ( Adm a){
		String table = a.getTableName();
		String columns = a.getColumnName();
		String values = a.getColumnValues();
		
		ActionDone ad = new ActionDone();
		String sql = "insert into "+ table +" ("+columns+") values ("+values+")";
		if(super.run(sql)){
			ad.setMessage("Os Dados foram adicionados com sucesso");
		}else{
			ad.setMessage("Ocorreu algum erro.");
		}
		return ad;
	};
	
	public ActionDone delete ( DoAction da ){
		 String table  = "adm"; 
		 String pk     = (String )da.getData("pk");
		 
		ActionDone ad = new ActionDone();
		String sql ="delete from " +table +" where pk = "+pk;
		
		if(super.run(sql)){
			ad.setMessage("Os Dados foram adicionados com sucesso");
		}else{
			ad.setMessage("Ocorreu algum erro.");
		}
		return ad;
	};
	
	public ActionDone select ( DoAction da ){
		ActionDone ad = new ActionDone();
		String sql = "select * from adm where ";
		String search = (String) da.getData("search");
		
		sql += "email ~*  "+ "'"+search+"'";
		
		ResultSet result = super.runWithResult(sql);
		
		ArrayList<Adm> arl = new ArrayList<>();
		try {
			while( result.next()){
					arl.add(new Adm(Integer.parseInt(result.getString(1)),
														result.getString(2),
														result.getString(3)));
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
		ad.setData("search",arl);
		return ad;
	}
	
	public ActionDone verificarLogin( DoAction da ){
		ActionDone ad = new ActionDone();
		String sql = "select * from adm where ";
		sql += "email = '"+da.getData("email")+"' and ";
		sql += "senha = '"+da.getData("senha")+"'";
		
		ResultSet result = super.runWithResult(sql);
		boolean exist = false;
		
		try {
			while(result.next()) exist = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		ad.setData("exist",exist);
		return ad;
	}
}
