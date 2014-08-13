package pedidos.cruds;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pedidos.control.CrudController;
import pedidos.model.Cliente;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;

public class CrudCliente extends CrudController{
	
	public CrudCliente(){
		super();
	};
	
	public ActionDone save   ( Cliente c){
		String table = c.getTableName();
		String columns = c.getColumnName();
		String values = c.getColumnValues();
		
		ActionDone ad = new ActionDone();
		String sql = "insert into "+ table +" ("+columns+") values ("+values+")";
		if(super.run(sql)){
			ad.setMessage("Os Dados foram adicionados com sucesso");
		}else{
			ad.setMessage("Ocorreu algum erro.");
		}
		return ad;
	};
	
	public ActionDone update ( Cliente c ){
		String table = c.getTableName();
		String columns = c.getColumnName();
		String values = c.getColumnValues();
		String pk = String.valueOf(c.getPk());
		
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
	
	public ActionDone delete ( DoAction da ){
		 String table  = "cliente"; 
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
		String sql = "select * from cliente where ";
		boolean isNumber = false;
		int value = 0;
		String search = (String) da.getData("search");
		try{
			value = Integer.parseInt(search);
			isNumber = true;
		}catch( Exception e){
		}
		
		
		if(!isNumber){
			sql += "nome =  "+ "'"+search+"'";
		}else{
			sql += "idade = " +value;
		}
		
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
		ad.setData("search",arl);
		return ad;
	}
	
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
