package model;

import java.util.List;

public class Armazem {

	private int armazem_id;
	private String nome;
	private String localizacao;
	private int capacidade;
	private Responsavel responsavel; // Relacionamento com Responsavel
	private List<Produto> produtos; // Relacionamento com Produto

	public Armazem() {

	}

	public Armazem(int armazem_id, String nome, String localizacao, int capacidade) {
		super();
		this.armazem_id = armazem_id;
		this.nome = nome;
		this.localizacao = localizacao;
		this.capacidade = capacidade;
	}

	public int getArmazem_id() {
		return armazem_id;
	}

	public void setArmazem_id(int armazem_id) {
		this.armazem_id = armazem_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
