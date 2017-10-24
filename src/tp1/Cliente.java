package tp1;

import java.util.ArrayList;

public class Cliente {
	
	private String nome;
	private String cpf;
	private ArrayList<Filme> alugados;
	
	public Cliente() {
		
		nome = null;
		cpf = null;
		alugados = new ArrayList<Filme>();
	}
	
	public Cliente(String nome, String cpf) {
		
		this.nome = nome;
		this.cpf = cpf;
		alugados = new ArrayList<Filme>();
		
	}
	
	public String getNome() {
	
		return nome;
	
	}
	
	public void setNome(String nome) {
	
		this.nome = nome;
	
	}
	
	public String getCPF() {
		
		return cpf;
		
	}

	public void setCPF(String cpf) {
		
		this.cpf = cpf;
		
	}
	
	public ArrayList<Filme> getAlugados() {
	
		return alugados;
	
	}
	
	public void setAlugados(ArrayList<Filme> alugados) {
	
		this.alugados = alugados;
	
	}
	
	public String toString() {
		
		return "Nome: " + nome + " - CPF: " + cpf;
		
	}
	
}
