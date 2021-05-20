package entities;

import java.util.Date;

public class Compra extends Operacao{
	private double valorMoeda;
	
	
	public Compra() {
		super();
	}

	public Compra(String name, String tipo ,String moeda, Double quantidade, Date data, double valorMoeda) {
		super(name, tipo ,moeda, quantidade, data);
		this.valorMoeda = valorMoeda;
	}

	public double getValorMoeda() {
		return valorMoeda;
	}

	public void setValorMoeda(double valorMoeda) {
		this.valorMoeda = valorMoeda;
	}

	@Override
	public double totalValue() {
		return quantidade * valorMoeda - quantidade * valorMoeda * 0.10;
	}

	@Override
	public double totalEmTaxas() {
		return quantidade * valorMoeda - totalValue();
	}

	
}
