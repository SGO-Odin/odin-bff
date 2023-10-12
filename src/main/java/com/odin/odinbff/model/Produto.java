package com.odin.odinbff.model;

import java.math.BigDecimal;  
import java.time.LocalDate;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")

public class Produto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "referencia", length = 100)
	private String referencia;
    
    @Column(name = "nome", length = 150)
	private String nome;
    
    @Column(name = "un", length = 50)
	private String un;
    
    @Column(name = "grifes", length = 150)
	private String grifes;
    
    @Column(name = "fornecedor", length = 150)
	private String fornecedor;
    
    @Column(name = "ativo", unique = true )
	private boolean ativo;
    
    @Column(name = "controlarEstoque", unique = true )
	private boolean controlarEstoque;
    
    @Column(name = "preco", precision = 16,scale = 2 )
	private BigDecimal preco;
    
    @Column(name = "lucroPorcentagem", precision = 16,scale = 2 )
	private BigDecimal lucroPorcentagem;
    
    @Column(name = "lucro", precision = 16,scale = 2 )
	private BigDecimal lucro;
    
    @Column(name = "venda", precision = 16,scale = 2 )
	private BigDecimal venda;
    
    @Column(name = "estAtual", length = 150)
	private String estAtual;
    
    @Column(name = "estMin", length = 150)
	private String estMin;
    
    @Column(name = "localizacao", length = 150)
	private String localizacao;
    
    @Column (name = "data_cadastro")
    private LocalDate dataCadastro;
    
    

	public Produto(String referencia, String nome, String un, String grifes, String fornecedor, boolean ativo,
			boolean controlarEstoque, BigDecimal preco, BigDecimal lucroPorcentagem, BigDecimal lucro, BigDecimal venda,
			String estAtual, String estMin, String localizacao) {
		super();
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
	}
	public Produto(Long id, String referencia, String nome, String un, String grifes, String fornecedor, boolean ativo,
			boolean controlarEstoque, BigDecimal preco, BigDecimal lucroPorcentagem, BigDecimal lucro, BigDecimal venda,
			String estAtual, String estMin, String localizacao) {
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
	}
	
	public Produto() {
		super();
	}
	
	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	@Override
	public String toString() {
		return "Produto [id=" + id + ", referencia=" + referencia + ", nome=" + nome + ", un=" + un + ", grifes="
				+ grifes + ", fornecedor=" + fornecedor + ", ativo=" + ativo + ", controlarEstoque=" + controlarEstoque
				+ ", preco=" + preco + ", lucroPorcentagem=" + lucroPorcentagem + ", lucro=" + lucro + ", venda="
				+ venda + ", estAtual=" + estAtual + ", estMin=" + estMin + ", localizacao=" + localizacao + "]";
	}

}
