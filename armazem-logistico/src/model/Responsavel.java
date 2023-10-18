package model;

public class Responsavel {

	private int responsavel_id;
	private String nome;
	private String telefone;

	public Responsavel() {

	}

	public Responsavel(int responsavel_id, String nome, String telefone) {
		super();
		this.responsavel_id = responsavel_id;
		this.nome = nome;
		this.telefone = telefone;
	}

	public int getResponsavel_id() {
		return responsavel_id;
	}

	public void setResponsavel_id(int responsavel_id) {
		this.responsavel_id = responsavel_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
