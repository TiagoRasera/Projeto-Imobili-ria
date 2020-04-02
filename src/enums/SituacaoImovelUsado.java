package enums;

public enum SituacaoImovelUsado {

	DESOCUPADO(1, "Desocupado"),
	OCUPADO(2, "Ocupado"),
	EMREFORMA(3, "Em reforma");
	
	private int situacao;
	private String nomeSituacao;
	
	private SituacaoImovelUsado(int situacao, String nomeSituacao) {
		this.situacao = situacao;
		this.nomeSituacao = nomeSituacao;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public String getNomeSituacao() {
		return nomeSituacao;
	}

	public void setNomeSituacao(String nomeSituacao) {
		this.nomeSituacao = nomeSituacao;
	}
}
