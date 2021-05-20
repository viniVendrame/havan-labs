package aplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Compra;
import entities.Operacao;
import entities.Venda;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		int menu;
		char op;
		String option;
		
		List<Operacao> operacao = new ArrayList<>();
	
		do {
			System.out.println("=== MEU CAMBIO ==='\n");
			System.out.println("1 - Nova Operação");
			System.out.println("2 - Relatórios");
			System.out.println("3 - Saldo");
			System.out.println("0 - Sair");
			menu = sc.nextInt();
			sc.nextLine();
			switch(menu) {
			case 1:
				do {
					System.out.println("=== OPERAÇÃO ===\n");
					System.out.print("Nome do cliente: ");
					String name = sc.nextLine();
					System.out.print("Compra ou Venda (C/V)? ");
					op = sc.next().charAt(0);
					sc.nextLine();
					if(op == 'C' || op == 'c') {
						String tipo = "Compra";
						System.out.println("\n=== COMPRA ===\n");
						System.out.print("Moeda: ");
						String moeda = sc.nextLine();
						System.out.print("Quantidade: ");
						double quantidade = sc.nextDouble();
						System.out.print("Cotação: ");
						double valorMoeda = sc.nextDouble();
						System.out.print("Data: ");
						Date data = sdf.parse(sc.next());
						operacao.add(new Compra(name, tipo, moeda, quantidade, data, valorMoeda));
						System.out.println("\nValor total da Operação: R$" + String.format("%.2f", quantidade * valorMoeda - quantidade * valorMoeda * .10));
						System.out.print("\nDeseja Realizar uma nova operação (Y/N)? ");
						op = sc.next().charAt(0);
						sc.nextLine();
						System.out.println();
					} else if (op == 'V' || op == 'v') {
						String tipo = "Venda";
						System.out.println("\n=== VENDA ===\n");
						System.out.print("Moeda: ");
						String moeda = sc.nextLine();
						System.out.print("Quantidade: ");
						double quantidade = sc.nextDouble();
						System.out.print("Cotação: ");
						double valorMoeda = sc.nextDouble();
						System.out.print("Data: ");
						Date data = sdf.parse(sc.next());
						operacao.add(new Venda(name, tipo, moeda, quantidade, data, valorMoeda));
						System.out.println("\nValor total da Operação: R$" + String.format("%.2f", quantidade * valorMoeda + quantidade * valorMoeda * .10));
						System.out.print("\nDeseja Realizar uma nova operação (Y/N)? ");
						op = sc.next().charAt(0);
						sc.nextLine();
						System.out.println();
					}
				}while(op == 'Y' || op == 'y');
				break;
				
			case 2:
				do {
					System.out.println("Filtrar por NOME ou DATA? ");
					option = sc.nextLine();
					if(option.equals("Nome") || option.equals("nome")) {
						System.out.print("Informe o nome: ");
						String name = sc.nextLine();
						for(Operacao operacoes: operacao) {
							if(operacoes.getName().equals(name)) {
								System.out.println("\nData: " + sdf.format(operacoes.getData()));
								System.out.println("Nome: " + operacoes.getName());
								System.out.println("Tipo de Operação: " + operacoes.getTipo());
								System.out.println("Moeda: " + operacoes.getMoeda());
								System.out.println("Quantidade: " + operacoes.getQuantidade());
								System.out.println("Valor total: R$" + String.format("%.2f", operacoes.totalValue()));
								System.out.println("Valor cobrado: " + String.format("%.2f", operacoes.totalValue() / operacoes.getQuantidade()));
								System.out.println();
							}
						}
					} else if (option.equals("Data") || option.equals("data")) {
						System.out.print("\nInforme a Data (DD/MM/YYYY): ");
						Date data = sdf.parse(sc.next());
						for(Operacao operacoes : operacao) {
							if(operacoes.getData().equals(data)) {
								System.out.println("\nData: " + sdf.format(operacoes.getData()));
								System.out.println("Nome: " + operacoes.getName());
								System.out.println("Tipo de Operação: " + operacoes.getTipo());
								System.out.println("Moeda: " + operacoes.getMoeda());
								System.out.println("Quantidade: " + operacoes.getQuantidade());
								System.out.println("Valor total: R$" + String.format("%.2f", operacoes.totalValue()));
								System.out.println("Valor cobrado: " + String.format("%.2f", operacoes.totalValue() / operacoes.getQuantidade()));
								System.out.println();
							}
						}
					}
					System.out.print("\nDeseja Realizar uma nova operação (Y/N)? ");
					op = sc.next().charAt(0);
					sc.nextLine();
				}while (op == 'Y' || op == 'y');
				break;
				
			case 3:
					double totalCompras = 0;
					double totalVendas = 0;
					double lucroTaxasCompra = 0;
					double lucroTaxasVenda = 0;
					System.out.println("\n=== SALDO ===\n");
					for(Operacao ope : operacao) {
						if(ope.getTipo().equals("Compra")) {
							totalCompras += ope.totalValue();
						}
					}
					System.out.println("Valor total em Compras: R$" + String.format("%.2f", totalCompras));
					
					for(Operacao ope : operacao) {
						if(ope.getTipo().equals("Venda")) {
							totalVendas += ope.totalValue();
						}
					}
					System.out.println("Valor total em Vendas: R$" + String.format("%.2f", totalVendas));
					
					for(Operacao ope : operacao) {
						if(ope.getTipo().equals("Compra")) {
							lucroTaxasCompra += ope.totalEmTaxas();
						}
					}
					for(Operacao ope : operacao) {
						if(ope.getTipo().equals("Venda")) {
							lucroTaxasVenda += ope.totalEmTaxas();
						}
					}
					
					System.out.println("Lucro nas Compras: R$" + String.format("%.2f", lucroTaxasCompra));
					System.out.println("Lucro nas Vendas: R$" + String.format("%.2f", lucroTaxasVenda)+"\n");
			
				break;
				
			
			}
		}while(menu != 0);
		System.out.println("Finalizando Sistema...");
		System.out.println("Sistema Finalizado!");
		
		sc.close();
	}
}
