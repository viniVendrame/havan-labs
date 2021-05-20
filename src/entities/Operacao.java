package entities;

import java.util.Date;

public abstract class Operacao {
	protected String name;
	protected String tipo;
	protected String moeda;
	protected Double quantidade;
	protected Date data;
	
	public Operacao() {
	}

	public Operacao(String name, String tipo, String moeda, Double quantidade, Date data) {
		super();
		this.name = name;
		this.tipo = tipo;
		this.moeda = moeda;
		this.quantidade = quantidade;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public abstract double totalValue();
	public abstract double totalEmTaxas();

	
}
