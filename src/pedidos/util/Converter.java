package pedidos.util;

public class Converter {
	
 public Object convert ( Object temp, String type ){
	 Object o = null;
	 type =  type.substring(type.lastIndexOf(".")+1);
	 
	 switch( type ){
		 case "int": 
			  String number = (String) temp;
			  if(number.equals(""))return new Integer("0");
			  o = Integer.parseInt(number);
		 break;
		 case "String":
			 o = (String) temp ;
		 break;
	 }
	 return o;
 };
 
 public static String firstUpperCase(String value){
	 String temp = value.substring(0,1);
	 value = temp.toUpperCase() + value.substring(1,value.length());
	 return value; 
 }
 
}
