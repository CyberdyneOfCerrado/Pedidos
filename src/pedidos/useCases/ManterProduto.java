package pedidos.useCases;

import pedidos.control.ModelController;
import pedidos.cruds.CrudProduto;
import pedidos.model.Cliente;
import pedidos.model.Produto;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;

public class ManterProduto extends ModelController<Produto> {
	private CrudProduto cp;
	
	public ManterProduto(){
		cp = new CrudProduto();
	}
	
	public ActionDone cadastrar( DoAction da){
		Produto p = new Produto();
		p.biuldObject(da);
		ActionDone ad = new ActionDone();
		if(validar(p)){
			ad = cp.save(p);
			ad.setMessage("Cadastrado com sucesso.");
			ad.setStatus(true);
		}else{
			ad.setMessage("Dados inv�lidos.");
			ad.setStatus(false);
		}
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone consultar( DoAction da){
		ActionDone ad = cp.select(da);
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone alterar( DoAction da){
		Produto p = new Produto();
		p.biuldObject(da);
		
		ActionDone ad = new ActionDone();
		if(validar(p)){
			ad = cp.update(p);
			ad.setMessage("Dados alterados com sucesso.");
			ad.setStatus(true);
		}else{
			ad.setMessage("Dados inv�lidos.");
			ad.setStatus(false);
		}
		
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
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
	
	@Override
	public boolean validarCustom(Produto o) {
		if( o.getNome().equals("") || o.getPreco() <= 0 || o.getPreco() > 100000 || o.getNome().length() > 45) return false;
		return true;
	};
}
