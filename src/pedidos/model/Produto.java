package pedidos.model;

import pedidos.interf.IReflectiveModel;
import pedidos.util.DoAction;
import pedidos.util.ReflectiveModel;

public class Produto extends ReflectiveModel implements IReflectiveModel {
	private int pk;
	private String nome;
	private int preco;
	
	public Produto(int pk , String nome , int preco){
		this.pk = pk;
		this.nome = nome;
		this.preco = preco;
	}
	
	public Produto(){
		
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
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

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}
}
