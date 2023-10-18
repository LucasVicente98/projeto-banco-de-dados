package model;

public class ResponsavelArmazemDTO {
	private int responsavelId;
	private String nomeResponsavel;
	private String telefoneResponsavel;
	private String nomesArmazens;

	public int getResponsavelId() {
		return responsavelId;
	}

	public void setResponsavelId(int responsavelId) {
		this.responsavelId = responsavelId;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getTelefoneResponsavel() {
		return telefoneResponsavel;
	}

	public void setTelefoneResponsavel(String telefoneResponsavel) {
		this.telefoneResponsavel = telefoneResponsavel;
	}

	public String getNomesArmazens() {
		return nomesArmazens;
	}

	public void setNomesArmazens(String nomesArmazens) {
		this.nomesArmazens = nomesArmazens;
	}

}
