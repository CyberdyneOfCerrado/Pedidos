package pedidos.model;

import pedidos.interf.IReflectiveModel;
import pedidos.util.DoAction;
import pedidos.util.ReflectiveModel;

public class Adm extends ReflectiveModel implements IReflectiveModel{
	private int pk;
	private String email;
	private String senha;
	
	//Overload
	public Adm(){
		
	};
	
	//Dados da interface gr�fica, pois n tem pk
	public Adm(String email , String senha){
		this.email = email;
		this.senha = senha;
	}
	
	//Dados de dentro do BD
	public Adm( int pk , String email , String senha){
		this.pk = pk;
		this.email = email;
		this.senha = senha;
	}
	
	public int getPk() {
		return pk;
	}


	public void setPk(int pk) {
		this.pk = pk;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	@Override
	public String getTableName() {
		return super.getTableName(this);
	}

	@Override
	public String getColumnName() {
		return super.getColumnName(this);
	}

	@Override
	public String getColumnValues() {
		return super.getColumnValues(this);
	}

	@Override
	public void biuldObject(DoAction doAction) {
		super.buildObject(this, doAction);
	}

}
