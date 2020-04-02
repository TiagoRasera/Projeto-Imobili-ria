package entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Novo extends Imovel{
	private Date dataDisponivel;
	
	public Novo() {
		super();
	}

	//CONSTRUTORES
	public Novo(String nomeProprietario, String endereco, Double area, double valor, Date dataDisponivel) {
		super(nomeProprietario, endereco, area, valor);
		this.dataDisponivel = dataDisponivel;
	}
	
	//MÉTODOS	
	@Override
	public String mostrarValorVenda() {
		valor = super.atualizaValorImovel();
		return "\nvalor de venda: R$ " + valor + ";";
	}

	//GETTERS AND SETTERS
	public String getDataDisponivel() {
		String dataString;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dataString = sdf.format(dataDisponivel);
		return dataString;
	}

	public void setDataDisponivel(Date dataDisponivel) {
		this.dataDisponivel = dataDisponivel;
	}

	//TO STRING
	@Override
	public String toString() {
		return "\nImóvel novo: \n"
				+ super.toString()
				+ "\nData disponível = " + getDataDisponivel();
	}
}
