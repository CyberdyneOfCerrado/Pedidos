package pedidos.model;

import pedidos.interf.IReflectiveModel;
import pedidos.util.DoAction;
import pedidos.util.ReflectiveModel;

public class Cliente extends ReflectiveModel implements IReflectiveModel{
	private int pk;
	private int idade;
	private int email;
	private char sexo;
	
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

	public int getEmail() {
		return email;
	}

	public void setEmail(int email) {
		this.email = email;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
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
