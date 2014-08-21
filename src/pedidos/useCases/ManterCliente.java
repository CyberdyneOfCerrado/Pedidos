package pedidos.useCases;

import java.util.ArrayList;

import pedidos.control.ModelController;
import pedidos.cruds.CrudCliente;
import pedidos.cruds.CrudPagamento;
import pedidos.model.Cliente;
import pedidos.model.Pagamento;
import pedidos.util.ActionDone;
import pedidos.util.DoAction;

public class ManterCliente extends ModelController {
	private CrudCliente cc;
	private CrudPagamento cPag;
	
	public ManterCliente(){
		this.cc = new CrudCliente();
		this.cPag = new CrudPagamento();
	};
	
	public ActionDone cadastrar( DoAction da){
		Cliente c = new Cliente();
		c.biuldObject(da);
		ActionDone ad = new ActionDone();
		
		if(validarCampos(da)){
			ad = cc.save(c);
			ad.setMessage("Cadastrado com sucesso.");
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
		ActionDone ad = cc.delete(da);
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setMessage("Cliente excluído com sucesso!");
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone consultar( DoAction da){
		ActionDone ad = cc.select(da);
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	public ActionDone categoria( DoAction da){
		ActionDone ad = new ActionDone();
		if( da.getData("getPagam") != null){
			ActionDone temp = cPag.selectAll();
			ArrayList<Pagamento> arl = (ArrayList<Pagamento>) temp.getData("pagamento");
			ad.setData("pagamento", arl);
			ad.setProcessed(false);
		}else{
			ad = cc.categoria(da);
			ActionDone temp = cPag.selectAll();
			ArrayList<Pagamento> arl = (ArrayList<Pagamento>) temp.getData("pagamento");
			ad.setData("pagamento", arl);
			ad.setData("search",da.getData("search"));
			ad.setData("valor",da.getData("valor"));
			ad.setProcessed(true);
		}
		 
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		
		return ad;
	};
	
	public ActionDone alterar( DoAction da){
		Cliente c = new Cliente();
		c.biuldObject(da);
		ActionDone ad = new ActionDone();
		if(validarCampos(da)){
			ad = cc.update(c);
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
	
	public ActionDone listar( DoAction da){
		ActionDone ad = cc.selectAll(da);
		//Identificando o pacote
		ad.setAction(da.getAction());
		ad.setUseCase(da.getUseCase());
		ad.setStatus(true);
		ad.setProcessed(true);
		return ad;
	};
	
	private boolean validarCampos( DoAction da ){
		String nome = (String) da.getData("nome");
		String idade = (String) da.getData("idade");
		String sexo = (String) da.getData("sexo");
		if( nome.equals("") || idade.equals("") || sexo.equals("") )return false;
		return true;
	}
	
	@Override
	public String[] getActions() {
		String [] temp ={
							"cadastrar",
							"alterar",
							"excluir",
							"consultar",
							"categoria",
							"listar"
						};
		return temp;
	};

	@Override
	public String getUserCase() {
		return "manterCliente";
	};
}
