
package pedidos.useCases;

import pedidos.control.ModelController;
import pedidos.cruds.CrudPedido;
import pedidos.cruds.CrudProduto;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;

public class ManterPedido extends ModelController {
	private CrudPedido cp;
	private CrudProduto cpp;
	
	public ManterPedido(){
		super();
		cpp = new CrudProduto();
		cp = new CrudPedido();
	}
	
	public ActionDone listar( DoAction da){
		ActionDone ad = cp.selectAll(da);
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone pedido( DoAction da){
		ActionDone ad = cpp.selectAll(da);
		ad.setData("idPedido",cp.criarPedido(da));
		//Coletando alguns dados do DoAction
		ad.setData("pk",(String) da.getData("pk"));
		ad.setData("nome", (String) da.getData("nome"));
		
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone alterar( DoAction da){
		ActionDone ad = cp.resgataProdutos(da);
		
		//Identificando o pacote
		ad.setData("pkCliente",da.getData("pkCliente"));
		ad.setData("pkPedido",da.getData("pkPedido"));
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone cadastrar( DoAction da){
		ActionDone ad = cp.cadastrar(da);
		cp.atualizarPedido(da);
		//Coletando alguns dados do DoAction
		
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone excluir( DoAction da){
		ActionDone ad = cp.delete(da);
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setMessage("Cliente exclu�do com sucesso!");
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone removeProduto (DoAction da ){
		ActionDone ad = new ActionDone();
		cp.removeProduto(da);
		//Coletando alguns dados do DoAction
		
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	}
	@Override
	public String[] getActions() {
		String[] actions ={
							"listar",
							"cadastrar",
							"pedido",
							"excluir",
							"alterar",
							"removeProduto"
						  };
		return actions;
	};

	@Override
	public String getUserCase() {
		return "manterPedido";
	};
}
