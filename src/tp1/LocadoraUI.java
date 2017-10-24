package tp1;

import java.io.*;
import java.util.*;

public abstract class LocadoraUI { //Locadora User Interface
	
	private static Scanner input = new Scanner(System.in);
	
	public static void MenuPrincipal(Locadora locadora) {
		
		int menu;
		
		do {
			System.out.println("           Locadora          ");
			System.out.println("                             ");
			System.out.println("                             ");
			System.out.println("1) Clientes                  ");
			System.out.println("2) Filmes                    ");
			System.out.println("3) Locação                   ");
			System.out.println("4) Devolução                 ");
			System.out.println("                             ");
			System.out.println("0) Sair                      ");
			System.out.println("                             ");
			System.out.print("Locadora > ");
			
			try {
				
				menu = input.nextInt();
			}
			catch(InputMismatchException e){
				
				input.nextLine();
				menu = -1;
			}

			switch(menu) {

				case 0:
					break;

				case 1:

					menuCliente(locadora);					
					break;

				case 2:	

					menuFilme(locadora);
					break;

				case 3:

					locadora.alugar();
					break;

				case 4:

					locadora.devolucao();
					break;

				default:

					System.out.println("Opção Inválida");
			}
		}while(menu != 0);
	}
	
	private static void menuCliente(Locadora locadora) {
		
		int menu;
		
		do {
			System.out.println("           Locadora          ");
			System.out.println("                             ");
			System.out.println("                             ");
			System.out.println("1) Adicionar Cliente         ");
			System.out.println("2) Remover Cliente           ");
			System.out.println("3) Lista de Clientes         ");
			System.out.println("                             ");
			System.out.println("0) Voltar                    ");
			System.out.println("                             ");
			System.out.print("Locadora : Clientes > ");
			
			try {

				menu = input.nextInt();
			}
			catch(InputMismatchException e){

				input.nextLine();
				menu = -1;
			}
			
			switch(menu) {
			
				case 0:
					break;
			
				case 1:
					
					locadora.criaCliente();
					break;
					
				case 2:
					
					locadora.removeCliente();
					break;
					
				case 3:
					
					locadora.imprimeClientes();
					break;
					
				default:
					
					System.out.println("Opção Inválida");
					
			}
			
		}while(menu != 0);
			
	}
	
	private static void menuFilme(Locadora locadora) {
		
		int menu;
		
		do {
			
			System.out.println("           Locadora          ");
			System.out.println("                             ");
			System.out.println("                             ");
			System.out.println("1) Adicionar Filme           ");
			System.out.println("2) Remover Filme             ");
			System.out.println("3) Lista de Filmes           ");
			System.out.println("                             ");
			System.out.println("0) Voltar                    ");
			System.out.println("                             ");
			System.out.print("Locadora : Filmes > ");
			
			try {

				menu = input.nextInt();
			}
			catch(InputMismatchException e){

				input.nextLine();
				menu = -1;
			}
			
			switch(menu) {
			
				case 0:
					break;
			
				case 1:
					
					locadora.insereFilme();
					break;
					
				case 2:
					
					locadora.removeFilme();
					break;
					
				case 3:
					
					locadora.imprimeFilmes();
					break;
					
				default:
					
					System.out.println("Opção Inválida");
			}
		}while(menu != 0);
	}
	
	public static void main(String[] args) {
		
		Locadora locadora = new Locadora();
		Scanner leitorDB;
		PrintWriter escritorDB;
		
		try {
			
			leitorDB = new Scanner(new BufferedReader(new FileReader("ClienteDB")));
			int n;
			Cliente cliente;
			
			while(leitorDB.hasNextLine()) {
				
				cliente = new Cliente();
				cliente.setNome(leitorDB.nextLine());
				cliente.setCPF(leitorDB.nextLine());
				locadora.getClientes().add(cliente);
				
				n = Integer.parseInt(leitorDB.nextLine());
				
				for(int i = 0; i < n; ++i) {
					
					cliente.getAlugados().add(new Filme(input.nextLine()));
				}
			}
			
			leitorDB.close();
		}
		catch(FileNotFoundException e){
			
			System.out.println("Não há Banco de Dados de clientes");
		}
		
		try {
			
			leitorDB = new Scanner(new BufferedReader(new FileReader("FilmeDB")));
			
			while(leitorDB.hasNextLine()) {
				
				Filme filme = new Filme(leitorDB.nextLine());
				
				filme.setAlugado(Boolean.parseBoolean(leitorDB.nextLine()));
				
				locadora.getFilmes().add(filme);
			}
			
			leitorDB.close();
		}
		catch(FileNotFoundException e){
			
			System.out.println("Não há Banco de Dados de filmes");
		}
		
		LocadoraUI.MenuPrincipal(locadora);
		
		try {
			
			escritorDB = new PrintWriter(new FileWriter("ClienteDB"));
			
			for(Cliente cliente : locadora.getClientes()) {
				
				escritorDB.println(cliente.getNome());
				escritorDB.println(cliente.getCPF());
				escritorDB.println(cliente.getAlugados().size());
				
				for(Filme filme : cliente.getAlugados()) {
					
					escritorDB.println(filme.getNome());
				}
			}
			
			escritorDB.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		try {
			
			escritorDB = new PrintWriter(new FileWriter("FilmeDB"));
			
			for(Filme filme : locadora.getFilmes()) {
				
				escritorDB.println(filme.getNome());
				escritorDB.println(filme.isAlugado());
			}
			
			escritorDB.close();
			
		} catch (IOException e) {
			
			e.getMessage();
		}
	}
}
