package com.odin.odinbff.model;

import java.math.BigDecimal;  
import java.time.LocalDate;

<<<<<<< HEAD
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

=======
>>>>>>> main
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
<<<<<<< HEAD
import jakarta.persistence.Table;

@Entity
@Table(name = "product")

=======

@Entity
>>>>>>> main
public class Product {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
<<<<<<< HEAD
    @Column(name = "reference", length = 100)
	private String reference;
    
    @Column(name = "name", length = 150)
	private String name;
    
    @Column(name = "unit", length = 50)
	private String unit;
    
    @Column(name = "brands", length = 150)
	private String brands;
    
    @Column(name = "supplier", length = 150)
	private String supplier;
    
    @Column(name = "active")
    private boolean active;

    
    @Column(name = "inventoryControl")
	private boolean inventoryControl;
    
    @Column(name = "price", precision = 16,scale = 2 )
	private BigDecimal price;
    
    @Column(name = "profitPercentage", precision = 16,scale = 2 )
	private BigDecimal profitPercentage;
    
    @Column(name = "profit", precision = 16,scale = 2 )
	private BigDecimal profit;
    
    @Column(name = "sale", precision = 16,scale = 2 )
	private BigDecimal sale;
    
    @Column(name = "currentStock", length = 150)
	private String currentStock;
    
    @Column(name = "minStock", length = 150)
	private String minStock;
    
    @Column(name = "location", length = 150)
	private String location;
    
    @Column (name = "registration_date")
    private LocalDate registrationDate;
    
    

	public Product(String reference, String name, String unit, String brands, String supplier, boolean active,
			boolean inventoryControl, BigDecimal price, BigDecimal profitPercentage, BigDecimal profit, BigDecimal sale,
			String currentStock, String minStock, String location) {
		super();
		this.reference = reference;
		this.name = name;
		this.unit = unit;
		this.brands = brands;
		this.supplier = supplier;
		this.active = active;
		this.inventoryControl = inventoryControl;
		this.price = price;
		this.profitPercentage = profitPercentage;
		this.profit = profit;
		this.sale = sale;
		this.currentStock = currentStock;
		this.minStock = minStock;
		this.location = location;
	}
	public Product(Long id, String reference, String name, String unit, String brands, String supplier, boolean active,
			boolean inventoryControl, BigDecimal price, BigDecimal profitPercentage, BigDecimal profit, BigDecimal sale,
			String currentStock, String minStock, String location) {
		super();
		this.id = id;
		this.reference = reference;
		this.name = name;
		this.unit = unit;
		this.brands = brands;
		this.supplier = supplier;
		this.active = active;
		this.inventoryControl = inventoryControl;
		this.price = price;
		this.profitPercentage = profitPercentage;
		this.profit = profit;
		this.sale = sale;
		this.currentStock = currentStock;
		this.minStock = minStock;
		this.location = location;
=======
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
    
    @Column(name = "ativo")
    private boolean ativo;

	
    
    @Column(name = "controlarEstoque")
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
    
    

	public Product(String referencia, String nome, String un, String grifes, String fornecedor, boolean ativo,
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
	public Product(Long id, String referencia, String nome, String un, String grifes, String fornecedor, boolean ativo,
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
>>>>>>> main
	}
	
	public Product() {
		super();
	}
	
	@PrePersist
	public void prePersist() {
<<<<<<< HEAD
		setRegistrationDate(LocalDate.now());
=======
		setDataCadastro(LocalDate.now());
>>>>>>> main
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

<<<<<<< HEAD
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBrands() {
		return brands;
	}

	public void setBrands(String brands) {
		this.brands = brands;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isInventoryControl() {
		return inventoryControl;
	}

	public void setInventoryControl(boolean inventoryControl) {
		this.inventoryControl = inventoryControl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getProfitPercentage() {
		return profitPercentage;
	}

	public void setProfitPercentage(BigDecimal profitPercentage) {
		this.profitPercentage = profitPercentage;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getSale() {
		return sale;
	}

	public void setSale(BigDecimal sale) {
		this.sale = sale;
	}

	public String getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(String currentStock) {
		this.currentStock = currentStock;
	}

	public String getMinStock() {
		return minStock;
	}

	public void setMinStock(String minStock) {
		this.minStock = minStock;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public LocalDate getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", reference=" + reference + ", name=" + name + ", unit=" + unit + ", brands="
				+ brands + ", supplier=" + supplier + ", active=" + active + ", inventoryControl=" + inventoryControl
				+ ", price=" + price + ", profitPercentage=" + profitPercentage + ", profit=" + profit + ", sale="
				+ sale + ", currentStock=" + currentStock + ", minStock=" + minStock + ", location=" + location + "]";
	}
	
	
=======
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
>>>>>>> main

}
