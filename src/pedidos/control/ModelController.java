package pedidos.control;
//Comportamento padr�o de um ModelController, ou seja, qualquer classe que implemente
//um caso de uso.
public abstract class ModelController {
	public abstract String[] getActions();//Os m�todos que existem nesta classe
	public abstract String   getUserCase();//O nome do caso de uso que esta classe implementa.
}
