package pedidos.cruds;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pedidos.control.CrudController;
import pedidos.model.Cliente;
import pedidos.model.Produto;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;

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
	
	public ActionDone select ( DoAction da ){
		ActionDone ad = new ActionDone();
		String sql = "select * from produto where ";
		boolean isNumber = false;
		int value = 0;
		String search = (String) da.getHashtable().get("search");
		try{
			value = Integer.parseInt(search);
			isNumber = true;
		}catch( Exception e){
		}
		
		
		if(!isNumber){
			sql += "nome =  "+ "'"+search+"'";
		}else{
			sql += "preco = " +value;
		}
		
		ResultSet result = super.runWithResult(sql);
		
		ArrayList<Produto> arl = new ArrayList<>();
		try {
			while( result.next()){
					arl.add(new Produto(Integer.parseInt(result.getString(1)),
														result.getString(2),
										Integer.parseInt(result.getString(3))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ad.setData("search",arl);
		return ad;
	};
}
