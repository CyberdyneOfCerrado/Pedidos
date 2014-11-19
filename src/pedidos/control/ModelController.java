package pedidos.control;

import pedidos.util.ReflectiveModel;

public abstract class ModelController<T> {
	public abstract boolean validarCustom(T o); 
	
	public boolean validar( T o ){
		boolean temp =  this.validarCustom( o ); 
		
		if( ( o instanceof ReflectiveModel  ) && temp )
			return true;
		
		return false; 
	}; 
}
