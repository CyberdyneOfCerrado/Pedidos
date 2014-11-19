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
		if(validarCampos(da)){
			ad = cp.save(p);
			ad.setMessage("Cadastrado com sucesso.");
			ad.setStatus(true);
		}else{
			ad.setMessage("Dados inválidos.");
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
		if(validarCampos(da)){
			ad = cp.update(p);
			ad.setMessage("Dados alterados com sucesso.");
			ad.setStatus(true);
		}else{
			ad.setMessage("Dados inválidos.");
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
		ad.setMessage("Cliente excluído com sucesso!");
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	private boolean validarCampos( DoAction da ){
		String nome  = (String) da.getData("nome");
		String preco = (String) da.getData("preco");
		if( nome.equals("") || preco.equals("")) return false;
		return true;
	}

	@Override
	public boolean validarCustom(Produto o) {
		// TODO Auto-generated method stub
		return false;
	};
}
