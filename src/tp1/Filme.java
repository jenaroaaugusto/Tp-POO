package tp1;

public class Filme {
	
	private String nome;
	private Cliente cliente;
	private boolean alugado;
	
	public Filme(String nome) {
		
		alugado = false;
		this.nome = nome;
		
	}
	
	public String getNome() {
		
		return nome;
	
	}
	
	public void setNome(String nome) {
	
		this.nome = nome;
	
	}
	
	public Cliente getCliente() {
		
		return cliente;
		
	}

	public void setCliente(Cliente cliente) {
		
		this.cliente = cliente;
		
	}

	public boolean isAlugado() {
	
		return alugado;
	
	}
	
	public void setAlugado(boolean alugado) {
	
		this.alugado = alugado;
	
	}
	
	public String toString() {
		
		return nome + (isAlugado() ? " - Alugado por: " + cliente.getNome() : " - Disponível");
		
	}
	
	
}
