package model;

public class Produto {

	private int produto_id;
	private String nome;
	private int quantidade;
	private double valorUnitario;
	private Armazem armazem; // Relacionamento com Armazem

	public Produto() {

	}

	public Produto(int produto_id, String nome, int quantidade, double valorUnitario) {
		super();
		this.produto_id = produto_id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
	}

	public int getProduto_id() {
		return produto_id;
	}

	public void setProduto_id(int produto_id) {
		this.produto_id = produto_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Armazem getArmazem() {
		return armazem;
	}

	public void setArmazem(Armazem armazem) {
		this.armazem = armazem;
	}

}
