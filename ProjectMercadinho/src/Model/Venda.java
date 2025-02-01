package Model;

public class Venda {
	
	private String id;
	private String idCliente;
	private String idFuncionario;
	private String formaPag;
	private String desconto;
	private String precoTotal;
	
	
	public Venda() {
		super();
	}
	public Venda(String id, String idCliente, String idFuncionario, String formaPag, String desconto,
			String precoTotal) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.idFuncionario = idFuncionario;
		this.formaPag = formaPag;
		this.desconto = desconto;
		this.precoTotal = precoTotal;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public String getFormaPag() {
		return formaPag;
	}
	public void setFormaPag(String formaPag) {
		this.formaPag = formaPag;
	}
	public String getDesconto() {
		return desconto;
	}
	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}
	public String getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(String precoTotal) {
		this.precoTotal = precoTotal;
	}
	
	
	
	

}
