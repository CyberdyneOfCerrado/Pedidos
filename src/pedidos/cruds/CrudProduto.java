package pedidos.cruds;

import java.sql.ResultSet;
import java.sql.SQLException;

import pedidos.control.CrudController;
import pedidos.model.Cliente;
import pedidos.model.Produto;
import pedidos.util.ActionDone;

public class CrudProduto extends CrudController {
	
	public CrudProduto(){
		super();
	};
	
	public ActionDone save   ( Produto p){
		String table = p.getTableName();
		String columns = p.getColumnName();
		String values = p.getColumnValues();
		
		ActionDone ad = new ActionDone();
		String sql = "insert into "+ table +" ("+columns+") values ("+values+")";
		if(super.run(sql)){
			ad.setMessage("Os Dados foram adicionados com sucesso");
		}else{
			ad.setMessage("Ocorreu algum erro.");
		}
		return ad;
	};
	
	public ActionDone update ( String table, String columns , String values, String pk){
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
	
	public ActionDone delete ( String table, String pk){
		ActionDone ad = new ActionDone();
		String sql ="delete from " +table +" where pk = "+pk;
		
		if(super.run(sql)){
			ad.setMessage("Os Dados foram adicionados com sucesso");
		}else{
			ad.setMessage("Ocorreu algum erro.");
		}
		return ad;
	};
	
	public ActionDone select ( String table,String select ,String columns , String values){
		ActionDone ad = new ActionDone();
		String sql = "select "+ select +" from " + table +" where ";
		String[] col = columns.split(",");
		String[] val = values.split(",");
		if( val.length == 1){
			sql += "pk = "+val[0];
		}else{
			for( int a = 0 ; a < col.length ; a++ )sql += col[a]+"="+val[a]+" and ";
			sql = sql.substring(0,sql.length()-4);
		}
		ResultSet result = super.runWithResult(sql);
		String[] selection = select.split(",");
		try {
			while( result.next()){
				for( int a = 1 ; a <= selection.length ; a++ ){
					ad.setData(result.getMetaData().getColumnName(a), result.getString(a));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ad;
	};
}
