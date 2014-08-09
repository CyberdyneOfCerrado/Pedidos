package pedidos.util;

import java.util.Hashtable;

public class DoAction {
	private String userCase;
	private String action;
	private Hashtable<String,Object> data;
	
	public DoAction(String userCase, String action){
		this.userCase = userCase;
		this.action = action;
		data = new Hashtable<>();
	}

	public String getUserCase() {
		return userCase;
	}

	public String getAction() {
		return action;
	}
	
	public void setData(String key , Object o ){
		data.put(key, o);
	}
	
	public Hashtable getHashtable(){
		return this.data;
	}
}
