package entidades;

public class Imovel {
	private String nomeProprietario;
	private String endereco;
	private Double area;
	protected double valor;
	
	//CONSTRUTORES
	public Imovel() {
	}

	public Imovel(String nomeProprietario, String endereco, Double area, double valor) {
		this.nomeProprietario = nomeProprietario;
		this.endereco = endereco;
		this.area = area;
		this.valor = valor;
	}
	
	//M…TODOS
	public final double atualizaValorImovel() {
		this.valor += this.valor * 0.10;
		return valor;
	}
	
	public String mostrarValorVenda() {
		valor = this.atualizaValorImovel();
		return "\nvalor de venda: R$ " + valor + ";";
	}

	//GETTERS AND SETTERS
	public String getNomeProprietario() {
		return nomeProprietario;
	}

	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	//TO STRING
	@Override
	public String toString() {
		return "Nome do Proprietario = " + getNomeProprietario() + ";"
				+ "\nEndereco = " + getEndereco() + ";"
				+ "\n¡rea = " + getArea() + " M\u00B2" + ";"
				+ "\nValor = R$ " + Double.toString(getValor()) + ";"
				+ mostrarValorVenda() + ";";
	}
}