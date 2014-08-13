package pedidos.util;

import java.util.Hashtable;

public class DoAction {
	private String useCase;
	private String action;
	private Hashtable<String,Object> data;
	
	public DoAction(String userCase, String action){
		this.useCase = userCase;
		this.action = action;
		data = new Hashtable<>();
	}

	public String getUseCase() {
		return useCase;
	}

	public String getAction() {
		return action;
	}
	
	public void setData(String key , Object o ){
		data.put(key, o);
	}
	

	public String getData(String key){
		return (String) this.data.get(key);
	}

	public Hashtable<String,Object> getHashtable() {
		return this.data;
	}
}
