package pedidos.util;


import java.lang.reflect.*;
import java.util.Iterator;


import java.util.Set;

import org.apache.tomcat.util.bcel.classfile.Annotations;


public class ReflectiveModel {

	
	protected <T>String getTableName(T classe){
		Class<? extends Class> c = (Class<? extends Class>) classe.getClass();
		String name = c.getCanonicalName().substring(c.getCanonicalName().lastIndexOf(".")+1);
		return name;
	};
	
	protected <T>String getColumnName(T classe ){
		Class<? extends Class> c = (Class<? extends Class>) classe.getClass();
		Field[] f = c.getDeclaredFields();
		String result="";
		
		for( Field a : f){
			if(!a.getName().contains("pk"))result +=","+a.getName();
		}
		
		return result.substring(1,result.length());
	};
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	//Pega o atributo de uma classe de forma reflexiva
	protected <T>String getColumnValues(T classe ){
		Converter con = new Converter();
		Class<? extends Class> c = (Class<? extends Class>) classe.getClass();
		String[] variables = getColumnName(classe).split(","); //Pega o nome dos atributos
		Method[] m = c.getDeclaredMethods();
		String result="";
		
		for( int a = 0 ; a < variables.length ; a++ ){
			String temp = "get"+variables[a];//Junta o get + o nome do atributo
			for( int b = 0 ; b < m.length ; b++ ){
				if(temp.equalsIgnoreCase(m[b].getName())){//compara sem canse sensitive
					Type t = m[b].getGenericReturnType();//Tipo de retorno do método
					try {
						 
						 if(!m[b].getName().contains("pk")){ //Se o nome do método tiver 'pk', não é preciso pegar (pk serial)
							 Object concat = m[b].invoke(classe, null);//valor do retorno do método (saída encapsulada no objeto)
							 concat = (concat == null)? " ":concat.toString();
							 //comparação entre números e strings para o sql do bd
							 if( t == Integer.TYPE){
								 result += ","+concat;
							 }else{
								 result += ","+"'"+concat+"'";
							 }
						 }
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException  e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result.substring(1,result.length());
	};
	
	//cria/inicia um Objeto a partir dos dados de um doAction
	protected <T>void buildObject(T classe,DoAction doAction){
		Set keys = doAction.getHashtable().keySet();
		Converter con = new Converter();
		
		Class c = classe.getClass();
		Method[] m = c.getDeclaredMethods();
		
		for(int a = 0 ; a < m.length ; a++){
			Iterator<String> i = keys.iterator();
			while(i.hasNext()){
				String key = i.next();
				if(m[a].getName().equalsIgnoreCase("set"+key)){
					try {
						Type p[] = m[a].getGenericParameterTypes();
						Object o = con.convert(doAction.getData(key), p[0].toString());
						m[a].invoke(classe,o);
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						e.printStackTrace();
						e.getCause();
					}
				}
			}
		}
	};
}
