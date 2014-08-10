package pedidos.util;

import java.util.Hashtable;

public class ActionDone {
	private boolean status;
	private Hashtable<String,Object> data;
	private String  message;
	private String  useCase;
	private String  action;
	
	public ActionDone(String userCase , String action){
		this.data = new Hashtable<>();
		this.useCase = userCase;
		this.action = action;
	};
	
	public ActionDone(DoAction da){
		this.useCase = da.getUserCase();
		this.action   = da.getAction();
		this.data     = (Hashtable<String, Object>) da.getHashtable().clone(); 
	};
	
	public ActionDone(){
		this.data = new Hashtable<>();
	};
	
	public Object getData(String key){
		return data.get(key);
	};
	
	public void setData(String key , Object o){
		data.put(key,o);
	};
	
	public boolean isStatus() {
		return status;
	};
	
	public void setStatus(boolean status) {
		this.status = status;
	};
	
	public String getMessage() {
		return message;
	};
	
	public void setMessage(String message) {
		this.message = message;
	};
	
	public String getUseCase() {
		return useCase;
	}
	public void setUseCase(String userCase) {
		this.useCase = userCase;
	};
	
	public String getAction() {
		return action;
	};
	
	public void setAction(String action) {
		this.action = action;
	};
}
