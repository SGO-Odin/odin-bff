package com.odin.odinbff.controller.produtos;

import java.math.BigDecimal; 
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.odin.odinbff.model.Product;;

public class ProdutoFormRequest {
	

	private Long id;
	private String referencia;
	private String nome;
	private String un;
	private String grifes;
	private String fornecedor;
	private boolean ativo;
	private boolean controlarEstoque;
	private BigDecimal preco;
	private BigDecimal lucroPorcentagem;
	private BigDecimal lucro;
	private BigDecimal venda;
	private String estAtual;
	private String estMin;
	private String localizacao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate cadastro;
	
	public Product toModel() {
		return new Product(
				id,
				referencia,
				nome,
				un,
				grifes,
				fornecedor,
				ativo,
				controlarEstoque,
				preco,
				lucroPorcentagem,
				lucro,
				venda,
				estAtual,
				estMin,
				localizacao);
	}
	
	public ProdutoFormRequest(Long id, String referencia, String nome, String un, String grifes, String fornecedor,
			boolean ativo, boolean controlarEstoque, BigDecimal preco, BigDecimal lucroPorcentagem, BigDecimal lucro,
			BigDecimal venda, String estAtual, String estMin, String localizacao, LocalDate cadastro) {
		super();
		this.id = id;
		this.referencia = referencia;
		this.nome = nome;
		this.un = un;
		this.grifes = grifes;
		this.fornecedor = fornecedor;
		this.ativo = ativo;
		this.controlarEstoque = controlarEstoque;
		this.preco = preco;
		this.lucroPorcentagem = lucroPorcentagem;
		this.lucro = lucro;
		this.venda = venda;
		this.estAtual = estAtual;
		this.estMin = estMin;
		this.localizacao = localizacao;
		this.cadastro = cadastro;
	}
	public ProdutoFormRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static ProdutoFormRequest fromModel(Product product) {
		return new ProdutoFormRequest(
				product.getId(),
				product.getFornecedor(),
				product.getNome(),
				product.getUn(),
				product.getGrifes(),
				product.getFornecedor(),
				product.isAtivo(),
				product.isControlarEstoque(),
				product.getPreco(),
				product.getLucroPorcentagem(),
				product.getLucro(),
				product.getVenda(),
				product.getEstAtual(),
				product.getEstMin(),
				product.getLocalizacao(),
		        product.getDataCadastro());
		
	}
	
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUn() {
		return un;
	}
	public void setUn(String un) {
		this.un = un;
	}
	public String getGrifes() {
		return grifes;
	}
	public void setGrifes(String grifes) {
		this.grifes = grifes;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public boolean isControlarEstoque() {
		return controlarEstoque;
	}
	public void setControlarEstoque(boolean controlarEstoque) {
		this.controlarEstoque = controlarEstoque;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public BigDecimal getLucroPorcentagem() {
		return lucroPorcentagem;
	}
	public void setLucroPorcentagem(BigDecimal lucroPorcentagem) {
		this.lucroPorcentagem = lucroPorcentagem;
	}
	public BigDecimal getLucro() {
		return lucro;
	}
	public void setLucro(BigDecimal lucro) {
		this.lucro = lucro;
	}
	public BigDecimal getVenda() {
		return venda;
	}
	public void setVenda(BigDecimal venda) {
		this.venda = venda;
	}
	public String getEstAtual() {
		return estAtual;
	}
	public void setEstAtual(String estAtual) {
		this.estAtual = estAtual;
	}
	public String getEstMin() {
		return estMin;
	}
	public void setEstMin(String estMin) {
		this.estMin = estMin;
	}

public String getLocalizacao() {
	return localizacao;
}
public void setLocalizacao(String localizacao) {
	this.localizacao = localizacao;
}

	public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}

@Override
public String toString() {
	return "ProdutoFormRequest [id=" + id + ", referencia=" + referencia + ", nome=" + nome + ", un=" + un + ", grifes="
			+ grifes + ", fornecedor=" + fornecedor + ", ativo=" + ativo + ", controlarEstoque=" + controlarEstoque
			+ ", preco=" + preco + ", lucroPorcentagem=" + lucroPorcentagem + ", lucro=" + lucro + ", venda=" + venda
			+ ", estAtual=" + estAtual + ", estMin=" + estMin + ", localizacao=" + localizacao + "]";
}
	

}
