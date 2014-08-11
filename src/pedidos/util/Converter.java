package pedidos.util;

public class Converter {
 public Object convert ( Object temp, String type ){
	 Object o = null;
	 type =  type.substring(type.lastIndexOf(".")+1);
	 
	 switch( type ){
		 case "int": 
			  o = Integer.parseInt((String) temp );
		 break;
		 case "String":
			 o = (String) temp ;
		 break;
	 }
	 return o;
 };
 
}
