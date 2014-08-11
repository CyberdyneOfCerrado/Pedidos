package pedidos.model;

import pedidos.interf.IReflectiveModel;
import pedidos.util.DoAction;
import pedidos.util.ReflectiveModel;

public class Cliente extends ReflectiveModel implements IReflectiveModel{
	private int pk;
	private int idade;
	private String sexo;
	private String nome;
	private String email;
	
	public Cliente(){
	}
	
	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
