package app;

import java.util.List;
import java.util.Scanner;

import controller.BuscarDoisCriterios;
import controller.BuscarUmCriterio;
import controller.ConsultaService;
import model.Endereco;
import model.Pet;
import model.enums.Criterio;
import model.enums.SexoPet;
import model.enums.TipoPet;
import view.Menu;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final String caminhoFormulario = "C:\\Users\\gabri\\OneDrive\\Área de Trabalho\\SistemaAdocaoPets\\formulario\\formulario.txt";
		final String caminhoDirCadastro = "C:\\Users\\gabri\\OneDrive\\Área de Trabalho\\SistemaAdocaoPets\\petsCadastrados";
		Integer respostaMenuCadastro; 
		
		Menu.inicio();
		
		Menu.menuCadastro();
		do{
			System.out.print("\n > ");
			respostaMenuCadastro = sc.nextInt();		
		} while(respostaMenuCadastro < 1 || respostaMenuCadastro > 6);
		
		switch(respostaMenuCadastro) {
		case 1:
			List<String> perguntas = Menu.lerFormulario(caminhoFormulario);
			String nome = null, raca = null;
			Double idade = null, peso = null;
			TipoPet tipo = null;
			SexoPet sexo = null;
			Endereco endereco = null;
			String respostaFormulario;
			Integer indice;
			System.out.println("Para realizar o cadastro, responda as perguntas a seguir:");
			
			if(perguntas.isEmpty()) break;
			try {
				for(indice = 0; indice < perguntas.size(); indice++) {
					System.out.println(perguntas.get(indice));
					switch(indice) {
					case 0:
						do {
							sc.nextLine();
							System.out.print(" > ");
							respostaFormulario = sc.nextLine();
						} while(!Menu.validaRespostas(respostaFormulario, indice));
						nome = respostaFormulario;
						
						break;
					case 1:
						String auxTipo;
						do {
							System.out.print(" > ");
							auxTipo = sc.next();
							System.out.println(auxTipo);
						} while(!auxTipo.toUpperCase().equalsIgnoreCase("GATO") && !auxTipo.toUpperCase().equalsIgnoreCase("CACHORRO"));
						tipo = TipoPet.valueOf(auxTipo.toUpperCase());
						
						break;
					case 2:
						String auxSexo;
						do {
							sc.nextLine();
							System.out.print(" > ");
							auxSexo = sc.next().toUpperCase();
						} while(!auxSexo.toUpperCase().equalsIgnoreCase("MACHO") && !auxSexo.toUpperCase().equalsIgnoreCase("FEMEA"));
						sexo = SexoPet.valueOf(auxSexo.toUpperCase());
						
						break;
					case 3:
						String cidade, rua, numero = null;
						sc.nextLine();
						System.out.print(" > Cidade: ");
						cidade = sc.nextLine();
				
						System.out.print(" > Rua: ");
						rua = sc.nextLine();
						
						System.out.print(" > Numero da casa: ");
						numero = sc.nextLine();
						if(!numero.isEmpty()) endereco = new Endereco(Integer.parseInt(numero), cidade, rua);
						else endereco = new Endereco(null, cidade, rua);
						break;
					case 4:
						char mesesOuAno;

						do {
							System.out.print(" > Digitar em meses ou anos (m/a): ");
							mesesOuAno = sc.next().charAt(0);
						} while(mesesOuAno != 'm' && mesesOuAno != 'a');
						
						System.out.print(" > ");
						sc.nextLine();
						respostaFormulario = sc.nextLine();
						
						if(mesesOuAno == 'm' && !respostaFormulario.isEmpty()) {
							System.out.println("Passou");
							
							respostaFormulario = String.valueOf(Integer.parseInt(respostaFormulario) / 12);
						}
						
						if(!respostaFormulario.isEmpty()) {
							while(!Menu.validaRespostas(respostaFormulario, indice)) {
								System.out.print(" > ");
								respostaFormulario = sc.nextLine();
							}
						}
						
						if(!respostaFormulario.isEmpty()) idade = Double.parseDouble(respostaFormulario);
						else idade = null;
						
						break;
					case 5:
						System.out.print(" > ");
						respostaFormulario = sc.nextLine();
						
						if(!respostaFormulario.isBlank()) {
							while(!Menu.validaRespostas(respostaFormulario, indice)) {
								System.out.print(" > ");
								respostaFormulario = sc.nextLine();
							}
						}
						
						if(!respostaFormulario.isBlank()) peso = Double.parseDouble(respostaFormulario);
						else peso = null;
						
						break;
					case 6:
						System.out.print(" > ");
						raca = sc.nextLine();
						
						if(!raca.isEmpty()) {
							while(!Menu.validaRespostas(raca, indice)) {
								System.out.print(" > ");
								raca = sc.nextLine();
							}
						}
						else raca = null;
						 
						break;
					}
				}
			}
			catch(RuntimeException e) {
				System.out.println(e.getMessage());
				break;
			}
			
			Pet pet = new Pet(nome, tipo, sexo, endereco, idade, peso, raca);
			if(pet.cadastrar(caminhoDirCadastro)) System.out.println("\nPet cadastrado com sucesso.");
			break;
		case 2:
			Menu.menuBuscaPets();
			int respostaBusca;
			
			do {
				System.out.print(" > ");
				respostaBusca = sc.nextInt();
			} while(respostaBusca < 1 && respostaBusca > 11);
			
			switch(respostaBusca) {
			case 1:
				System.out.println("Nome: ");
				String nomePet = sc.nextLine();
				consultarPet(nomePet, Criterio.NOME, caminhoDirCadastro);
			case 2:
				System.out.println("Sexo: ");
				String sexoPet = sc.nextLine();
				consultarPet(sexoPet, Criterio.SEXO, caminhoDirCadastro);
			case 3:
				System.out.println("Idade: ");
				String idadePet = sc.nextLine();
				consultarPet(idadePet, Criterio.IDADE, caminhoDirCadastro);
			case 4:
				System.out.println("Peso: ");
				String pesoPet = sc.nextLine();
				consultarPet(pesoPet, Criterio.PESO, caminhoDirCadastro);
			case 5:
				System.out.println("Raca: ");
				String racaPet = sc.nextLine();
				consultarPet(racaPet, Criterio.RACA, caminhoDirCadastro);
			case 6:
				System.out.println("Endereco: ");
				String enderecoPet = sc.nextLine();
				consultarPet(enderecoPet, Criterio.ENDERECO, caminhoDirCadastro);
			case 7:
				System.out.println("Nome: ");
				nomePet = sc.nextLine();
				System.out.println("Idade: ");
				idadePet = sc.nextLine();
				consultarPet(nomePet, idadePet, Criterio.NOME_IDADE, caminhoDirCadastro);
			case 8:
				System.out.println("Idade: ");
				idadePet = sc.nextLine();
				System.out.println("Peso: ");
				pesoPet = sc.nextLine();
				consultarPet(idadePet, pesoPet, Criterio.IDADE_PESO, caminhoDirCadastro);
			case 9:
				System.out.println("Nome: ");
				nomePet = sc.nextLine();
				System.out.println("Peso: ");
				pesoPet = sc.nextLine();
				consultarPet(nomePet, pesoPet, Criterio.NOME_PESO, caminhoDirCadastro);
			case 10:
				System.out.println("Raca: ");
				racaPet = sc.nextLine();
				System.out.println("Peso: ");
				pesoPet = sc.nextLine();
				consultarPet(racaPet, pesoPet, Criterio.RACA_PESO, caminhoDirCadastro);
			case 11:	
				System.out.println("Sexo: ");
				sexoPet= sc.nextLine();
				System.out.println("Raca: ");
				racaPet = sc.nextLine();
				consultarPet(sexoPet, racaPet, Criterio.SEXO_RACA, caminhoDirCadastro);
			}
			
		case 3:
			
		case 4:
			
		case 5:
			
		default:
			break;
		}
		
		
		sc.close();
	}

	
	public static void consultarPet(String informacao, Criterio criterio, String diretorio) {
		ConsultaService consulta = new ConsultaService(diretorio, new BuscarUmCriterio(informacao, criterio));
		List<Pet> petsEncontrados = consulta.consultar();
		int count = 1;
		if(!petsEncontrados.isEmpty()) {
			for(Pet petEncontrado : petsEncontrados) {
				System.out.printf("%d pet(s) encontrado(s):\n", petsEncontrados.size());
				System.out.println(count + " -> " + petEncontrado);
				count++;						
			}
		}
		else System.out.println("Nenhum pet encontrado.");
	}
	
	public static void consultarPet(String informacao1, String informacao2, Criterio criterio, String diretorio) {
		ConsultaService consulta = new ConsultaService(diretorio, new BuscarDoisCriterios(informacao1, informacao2, criterio));
		List<Pet> petsEncontrados = consulta.consultar();
		int count = 1;
		if(!petsEncontrados.isEmpty()) {
			for(Pet petEncontrado : petsEncontrados) {
				System.out.printf("%d pet(s) encontrado(s):\n", petsEncontrados.size());
				System.out.println(count + " -> " + petEncontrado);
				count++;						
			}
		}
		else System.out.println("Nenhum pet encontrado.");
	}
}
