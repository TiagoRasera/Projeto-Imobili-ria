package aplicacao;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Scanner;

import entidades.Novo;
import entidades.Usado;
import enums.SituacaoImovelUsado;

public class Programa {
	
	public static void main(String[] args) {
		String proprietario;
		String endereco;
		Double area;
		double valor;
		int condicaoImovel;
		int contNovo = 0, contUsado = 0;
		int resposta;
		int continuarInt;
		boolean continuar = false;

		Novo[] novo = new Novo[5];
		Usado[] usado = new Usado[5];
		
		Scanner leitura = new Scanner (System.in);		
		
		do {
			System.out.println("selecione o que você deseja fazer:");
			System.out.println("1 - cadastrar imóvel;");
			System.out.println("2 - ver lista de imóveis cadastrados;");
			System.out.println("3 - sair;");
			System.out.print("insira a sua resposta: ");
			
			do {
				resposta = leitura.nextInt();
				
				if (resposta < 1 && resposta > 3) {
					System.out.println("opção inválida, insira novamente: ");
				}
			} while (resposta < 1 && resposta > 3);
			
			switch (resposta) {
			case 1:
				do {
					System.out.println("\nInsira a condição do imóvel:");
					System.out.println("1 - novo;");
					System.out.println("2 - usado;");
					System.out.print("sua resposta: ");
					condicaoImovel = leitura.nextInt();
					
					System.out.print("\ninsira o nome do proprietario desse imovel: ");
					leitura.nextLine();
					proprietario = leitura.nextLine();
					proprietario = proprietario.trim();
					System.out.print("insira o endereço em que esse imóvel se localiza: ");
					endereco = leitura.nextLine();
					System.out.print("insira a are desse imóvel (em M\u00B2): ");
					area = leitura.nextDouble();
					System.out.print("insira o valor do imóvel: ");
					valor = leitura.nextDouble();
					leitura.nextLine();
					
					switch (condicaoImovel) {
					case 1:
						String dataString;
						String retornoString = "";
						Date data;
						
						System.out.print("insira a data em que o imóvel estará disponível (dd/MM/yyyy): ");
						
						do {
							dataString = leitura.nextLine();
							dataString = dataString.trim();
							data = validaData(dataString);
							
							if (data == null) {
								System.out.print("data inválida, insira novamente: ");
								continue;
							}
							
							retornoString = verificaDataAntes(data);
							if (retornoString.equals("antes")) {
								data = null;
								System.out.print("a data lida é anterior ao dia de hoje, insira novamente: ");
							}
						} while (data == null);
						
						novo[contNovo] = new Novo(proprietario, endereco, area, valor, data);
						
						if (novo[contNovo] != null) {
							System.out.println("\no imovel foi cadastrado com sucesso no sistema;");
							System.out.println(novo[contNovo]);
						} else {
							System.out.println("\nhouve um erro ao cadastrar o imóvel no sistema;");
						}
						
						contNovo++;
						
						break;
					case 2:
						int situacaoInt;
						SituacaoImovelUsado situacao = null;
						
						System.out.println("informe a situação do imóvel usado: ");
						System.out.println("1 - desocupado;");
						System.out.println("2 - ocupado;");
						System.out.println("3 - em reforma;");
						System.out.print("insira a sua resposta: ");
		
						do {
							situacaoInt = leitura.nextInt();
							
							if (situacaoInt < 1 && situacaoInt > 3) {
								System.out.print("resposta inválida, insira novamente: ");
							}
						} while(situacaoInt < 1 && situacaoInt > 3);
						
						switch (situacaoInt) {
						case 1: situacao = SituacaoImovelUsado.DESOCUPADO; break;
						case 2: situacao = SituacaoImovelUsado.OCUPADO;  break;
						case 3: situacao = SituacaoImovelUsado.EMREFORMA; break;
						}
						
						usado[contUsado] = new Usado(proprietario, endereco, area, valor, situacao);
						
						if (usado[contUsado] != null) {
							System.out.println("\no imóvel cadastrado com sucesso;");
							System.out.println(usado[contUsado]);
						} else {
							System.out.println("\nhouve um erro ao cadastrar o imóvel ao sistema;");
						}
						
						contUsado++;
						break;
					}
					
					System.out.println("\ndeseja cadastrar outro imóvel? ");
					System.out.println("1 - sim;");
					System.out.println("2 - não;");
					System.out.print("insira a sua resposta: ");
					do {
						continuarInt = leitura.nextInt();
						
						if (continuarInt != 1 && continuarInt != 2) {
							System.out.print("resposta inválida, insira novamente: ");
						}
					} while (continuarInt != 1 && continuarInt != 2);
					
					switch(continuarInt) {
					case 1: continuar = true;
					case 2: continuar = false;
					}
					
				} while (continuar == true);
				break;
			case 2: 
				int retornoNovo;
				int retornoUsado;
				
				retornoNovo = verificarNovo(novo);
				retornoUsado = verificarUsado(usado);
				
				if (retornoNovo == novo.length) {
					System.out.println("\nnão há nenhum imóvel novo cadastrado;");
				} else {
					System.out.println("\nlista de imóveis cadastrados;");
					mostrarNovo(novo);
				}
				
				if (retornoUsado == novo.length) {
					System.out.println("\nnão há nenhum imóvel usado cadastrado;");
				} else {
					System.out.println("\nlista de ióveis cadastrados:");
					mostrarUsado(usado);
				}
				break;
			}
		} while (resposta != 3);
		leitura.close();
	}
	
	public static Date validaData (String data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		try {
			Date minhaData = sdf.parse(data);
			return minhaData;
		} catch (Exception e) {
			System.out.println("a data precisa estar no formato correto: dd/MM/yyyy");
			return null;
		} 
	}
	
	public static String verificaDataAntes(Date data) {
		/*
		 * neste método você pode estar se perguntando por que eu converti de Date para LocalDate
		 * quando eu poderia simplesmente ter comparado uma com a outra. O motivo para isso é porque no método acima 
		 * eu converti da String que continha a data que foi lida para a classe date, como a string que eu li 
		 * era somente a data sem a hora, a variável da classe Data continha a hora toda zerada (00:00:00) e 
		 * quando eu tentava comparar neste método sempre dava que o dia era anterior mesmo o dia sendo o mesmo, 
		 * porque quando a linha  abaixo buscava a data do sistema ela buscava também a hora, e qualquer hora 
		 * que fosse buscada seria maior que a hora zerada. Deve haver métodos melhores de resolver esse
		 * problema, essa foi a forma como eu resolvi.
		 */
		
		String retorno = "";
		Date hoje = new Date();
		
		Instant instant = hoje.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());

		LocalDate dataHoje = zdt.toLocalDate();
		LocalDate dataLida = zdt.toLocalDate();
		
		if (dataLida.isBefore(dataHoje)) {
			retorno = "antes";
		} else if (dataLida.isEqual(dataHoje)){
			retorno = "depois";
		} else {
			retorno = "depois";
		}
		return retorno;
	}
	
	public static int verificarNovo(Novo novo[]) {
		int cont;
		int contNull = 0;
		for (cont=0; cont<novo.length; cont++) {
			if (novo[cont] == null) {
				contNull++;
			}
		}
		return contNull;
	}
	
	public static int verificarUsado(Usado usado[]) {
		int cont;
		int contNull = 0;
		
		for (cont=0; cont<usado.length; cont++) {
			if (usado[cont] == null) {
				contNull++;
			}
		}
		return contNull;
	}
	
	public static void mostrarNovo(Novo novo[]) {	
		for (Novo imovelNovo: novo) {
			if (imovelNovo != null) {
				System.out.println(imovelNovo);
			}
		}
	}
	
	public static void mostrarUsado(Usado usado[]) {
		for (Usado imovelUsado: usado) {
			if (imovelUsado != null) {
				System.out.println(imovelUsado);
			}
		}
	}
}