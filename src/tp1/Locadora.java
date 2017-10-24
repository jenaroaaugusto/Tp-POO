package tp1;

import java.util.ArrayList;
import java.util.Scanner;

public class Locadora {
	
	private static Scanner input = new Scanner(System.in);
	
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private ArrayList<Filme> filmes = new ArrayList<Filme>();
	
	public ArrayList<Cliente> getClientes() {
	
		return clientes;
	}
	
	public ArrayList<Filme> getFilmes() {
	
		return filmes;
	}
	
	public Cliente buscaCliente(String cpf) {
		
		for(Cliente cliente : clientes) {
			
			if(cliente.getCPF().equalsIgnoreCase(cpf)) {
				
				return cliente;
			}
		}
		
		return null;
	}
	
	public Filme buscaFilme(String nome) {
		
		for(Filme filme : filmes) {
			
			if(filme.getNome().equalsIgnoreCase(nome)) {
				
				return filme;
			}
		}
		
		return null;
		
	}
	
	public void alugar() {
		
		System.out.printf("%nLocação%n%n");
		System.out.print("Digite o cpf do cliente: ");
		
		String cpf;
		
		do {
			cpf = input.nextLine();
			
			if(!cpf.matches("^\\d{11}")) {
				
				System.out.print("CPF Inválido, digite novamente: ");
			}
		}while(!cpf.matches("^\\d{11}"));
		
		Cliente cliente = buscaCliente(cpf);
		
		if(cliente != null) {
			
			System.out.print("Digite o nome do filme a ser alugado: ");
			Filme filme = buscaFilme(input.nextLine());
			
			if(filme != null) {
				
				if(!filme.isAlugado()) {
					
					cliente.getAlugados().add(filme);
					filme.setCliente(cliente);
					filme.setAlugado(true);
					
					System.out.println("Filme alugado com sucesso.");		
				}
				else {
					
					System.out.println("O filme já está alugado. Cancelando operação...");
				}
			}
			else {
				
				System.out.println("Não foi possível achar um filme com este nome. Cancelando operação...");
			}
		}
		else {
			
			System.out.println("Não existe cliente com este CPF. Cancelando operação...");
		}
		
	}
	
	public void devolucao() {
		
		System.out.println("Devolução:");
		System.out.print("Digite o cpf do cliente: ");
		Cliente cliente = buscaCliente(input.nextLine());
		
		if(cliente != null) {
			
			System.out.print("Digite o nome do filme a ser devolvido: ");
			String nome = input.nextLine();
			
			for(Filme filme : cliente.getAlugados()) {
				
				if(filme.getNome().equalsIgnoreCase(nome)) {
					
					cliente.getAlugados().remove(filme);
					filme.setAlugado(false);
					filme.setCliente(null);
					
					System.out.println("O filme foi devolvido com sucesso.");
					
					return;
				}
			}
				
			System.out.println("Cliente não está alugando este filme. Cancelando operação...");
		}
		else {
			
			System.out.println("Não existe cliente com este CPF. Cancelando operação...");
		}	
	}
	
	private boolean verificaCPF(String cpf) {
		
		for(Cliente cliente : clientes) {
			
			if(cliente.getCPF().equals(cpf)) {
				
				return false;
			}
		}
		return true;
	}
	
	public void criaCliente() {
		
		System.out.printf("%nNovo Cliente%n%n");
		System.out.print("Digite o CPF do novo cliente (Somente números): ");
		
		String cpf;
		
		do {
			cpf = input.nextLine();
			
			if(!cpf.matches("^\\d{11}")) {
				
				System.out.print("CPF Inválido, digite novamente: ");
			}
		}while(!cpf.matches("^\\d{11}"));
		
		if(verificaCPF(cpf)) {
			
			System.out.print("Digite o nome do novo cliente: ");
			
			clientes.add(new Cliente(input.nextLine(), cpf));
			
			System.out.println("Cliente cadastrado com sucesso.");
		}
		else {
			
			System.out.println("CPF em uso. Cancelando operação...");
		}
	}
	
	public void removeCliente() {
		
		System.out.print("Digite o CPF do cliente a ser removido (Somente números): ");
		
		String cpf;
		
		do {
			cpf = input.nextLine();
			
			if(!cpf.matches("^\\d{11}")) {
				
				System.out.print("CPF Inválido, digite novamente: ");
			}
			
		}while(!cpf.matches("^\\d{11}"));
		
		Cliente cliente = buscaCliente(cpf);
		
		if(cliente != null) {
			
			clientes.remove(cliente);
			
			System.out.println("Cliente removido com sucesso.");
		}
		else {
			
			System.out.println("Este cliente não existe no sistema.");
		}
	}
	
	public void insereFilme() {
		
		System.out.printf("%nNovo Filme%n%n");
		System.out.print("Digite o nome do novo filme: ");
		
		String nome = input.nextLine();
		Filme filme = buscaFilme(nome);
		
		if(filme == null) {
			
			filmes.add(new Filme(nome));
			
			System.out.println("Filme adicionado com sucesso.");
		}
		else {
			
			System.out.println("Já existe filme com este nome.");
			
		}
	}
	
	public void removeFilme() {
		
		System.out.print("Digite o filme a ser removido: ");
		
		String nome = input.nextLine();
		
		Filme filme = buscaFilme(nome);
		
		if(filme != null) {
			
			filmes.remove(filme);
			
			System.out.println("Filme removido com sucesso.");
			
		}
		else {
			
			System.out.println("Este filme não existe no sistema.");
		}
	}
	
	public void imprimeClientes() {
		
		System.out.printf("Lista de Clientes:%n%n");
		
		if(!clientes.isEmpty()) {
			
			for(Cliente cliente : clientes) {
				
				System.out.println(cliente);
			}
		}
		else {
			
			System.out.println("Não há clientes cadastrados.");
		}
	}
	
	public void imprimeFilmes() {
		
		System.out.printf("Lista de Filmes:%n%n");
		
		if(!filmes.isEmpty()) {
			
			for(Filme filme : filmes) {
				
				System.out.println(filme);	
			}	
		}
		else {
			
			System.out.println("Não há filmes cadastrados.");
		}
	}
}
