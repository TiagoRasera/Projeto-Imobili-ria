package entidades;

import enums.SituacaoImovelUsado;

public final class Usado extends Imovel {
	private SituacaoImovelUsado atualSituacao;
	
	//CONSTRUTORES
	public Usado() {
		super();
	}

	public Usado(String nomeProprietario, String endereco, Double area, double valor, SituacaoImovelUsado atualSituacao) {
		super(nomeProprietario, endereco, area, valor);
		this.atualSituacao = atualSituacao;
	}
	
	//MÉTODOS
	@Override
	public String mostrarValorVenda() {
		valor = super.atualizaValorImovel();
		return "o valor atualizado do imóvel é " + valor;
	}

	//GETTERS AND SETTERS
	public SituacaoImovelUsado getAtualSituacao() {
		return atualSituacao;
	}

	public void setAtualSituacao(SituacaoImovelUsado atualSituacao) {
		this.atualSituacao = atualSituacao;
	}

	//TO STRING
	@Override
	public String toString() {
		return "\nImovel usado: \n"
				+ super.toString()
				+ "\nAtual situacao = " + atualSituacao.getNomeSituacao();
	}
}
