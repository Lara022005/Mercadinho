package Model;

public class Produto {
	
	private String id;
	private String idFornecedor;
	private String nome;
	private String codBarra;
	private String lote;
	private String dataFab;
	private String dataVal;
	private String marca;
	private String categoria;
	private String uniMedida;
	private String precoUni;
	private String estoque;
	private String precoTotal;
	
				
	public Produto() {
		super();
	}
	public Produto(String id, String idFornecedor, String nome, String codBarra, String lote, String dataFab,
			String dataVal, String marca, String categoria, String uniMedida, String precoUni, String estoque) {
		super();
		this.id = id;
		this.idFornecedor = idFornecedor;
		this.nome = nome;
		this.codBarra = codBarra;
		this.lote = lote;
		this.dataFab = dataFab;
		this.dataVal = dataVal;
		this.marca = marca;
		this.categoria = categoria;
		this.uniMedida = uniMedida;
		this.precoUni = precoUni;
		this.estoque = estoque;
	}
	
	public String getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(String precoTotal) {
		this.precoTotal = precoTotal;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(String idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodBarra() {
		return codBarra;
	}
	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getDataFab() {
		return dataFab;
	}
	public void setDataFab(String dataFab) {
		this.dataFab = dataFab;
	}
	public String getDataVal() {
		return dataVal;
	}
	public void setDataVal(String dataVal) {
		this.dataVal = dataVal;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getUniMedida() {
		return uniMedida;
	}
	public void setUniMedida(String uniMedida) {
		this.uniMedida = uniMedida;
	}
	public String getPrecoUni() {
		return precoUni;
	}
	public void setPrecoUni(String precoUni) {
		this.precoUni = precoUni;
	}
	public String getEstoque() {
		return estoque;
	}
	public void setEstoque(String estoque) {
		this.estoque = estoque;
	}
	
	
}
