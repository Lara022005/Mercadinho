package Model;

public class ProdutoVenda {
	
	private String id;
	private String idVenda;
	private String idProduto;
	private String quantidade;
	
	
	public ProdutoVenda() {
		super();
	}
	public ProdutoVenda(String id, String idVenda, String idProduto, String quantidade) {
		super();
		this.id = id;
		this.idVenda = idVenda;
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(String idVenda) {
		this.idVenda = idVenda;
	}
	public String getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	

}
