package app;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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
			System.out.println("\nPara realizar o cadastro, responda as perguntas a seguir:");
			
			if(perguntas.isEmpty()) break;
			try {
				for(indice = 0; indice < perguntas.size(); indice++) {
					System.out.println("\n\033[1m" + perguntas.get(indice) + "\033[0m");
					switch(indice) {
					case 0:
						do {
							sc.nextLine();
							System.out.print(" >> ");
							respostaFormulario = sc.nextLine();
						} while(!Menu.validaRespostas(respostaFormulario, indice));
						nome = respostaFormulario;
						
						break;
					case 1:
						String auxTipo;
						do {
							System.out.print(" >> ");
							auxTipo = sc.next();
						} while(!auxTipo.toUpperCase().equalsIgnoreCase("GATO") && !auxTipo.toUpperCase().equalsIgnoreCase("CACHORRO"));
						tipo = TipoPet.valueOf(auxTipo.toUpperCase());
						
						break;
					case 2:
						String auxSexo;
						do {
							sc.nextLine();
							System.out.print(" >> ");
							auxSexo = sc.next().toUpperCase();
						} while(!auxSexo.toUpperCase().equalsIgnoreCase("MACHO") && !auxSexo.toUpperCase().equalsIgnoreCase("FEMEA"));
						sexo = SexoPet.valueOf(auxSexo.toUpperCase());
						
						break;
					case 3:
						String cidade, rua, numero = null;
						sc.nextLine();
						do {
							System.out.print(" > Cidade: ");
							cidade = sc.nextLine();							
						} while(cidade.isEmpty());
						
						do {
							System.out.print(" > Rua: ");
							rua = sc.nextLine();							
						} while(rua.isEmpty());
						
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
						
						System.out.print(" >> ");
						sc.nextLine();
						respostaFormulario = sc.nextLine();
						
						if(mesesOuAno == 'm' && !respostaFormulario.isEmpty()) {
							respostaFormulario = String.valueOf(Integer.parseInt(respostaFormulario) / 12);
						}
						
						if(!respostaFormulario.isEmpty()) {
							while(!Menu.validaRespostas(respostaFormulario, indice)) {
								System.out.print(" >> ");
								respostaFormulario = sc.nextLine();
							}
						}
						
						if(!respostaFormulario.isEmpty()) idade = Double.parseDouble(respostaFormulario);
						else idade = null;
						
						break;
					case 5:
						System.out.print(" >> ");
						respostaFormulario = sc.nextLine();
						
						if(!respostaFormulario.isBlank()) {
							while(!Menu.validaRespostas(respostaFormulario, indice)) {
								System.out.print(" >> ");
								respostaFormulario = sc.nextLine();
							}
						}
						
						if(!respostaFormulario.isBlank()) peso = Double.parseDouble(respostaFormulario);
						else peso = null;
						
						break;
					case 6:
						System.out.print(" >> ");
						raca = sc.nextLine();
						
						if(!raca.isEmpty()) {
							while(!Menu.validaRespostas(raca, indice)) {
								System.out.print(" >> ");
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
			String tipoPetBusca; 
			do {
				System.out.println("\nTipo do Pet: ");
				tipoPetBusca = sc.next();
			} while(!tipoPetBusca.toUpperCase().equalsIgnoreCase("CACHORRO") && !tipoPetBusca.toUpperCase().equalsIgnoreCase("GATO"));
			
			Menu.menuBuscaPets();
			int respostaBusca;
			List<Pet> petsEncontrados;
			do {
				System.out.print("\n >> ");
				respostaBusca = sc.nextInt();
			} while(respostaBusca < 1 || respostaBusca > 11);
			
			sc.nextLine();
			
			petsEncontrados = consultarPet(respostaBusca, sc, caminhoDirCadastro, tipoPetBusca);
			
			
			if(!petsEncontrados.isEmpty()) { 
				System.out.println("Deseja alterar cadastro de:");
				for(int i = 0; i < petsEncontrados.size(); i++) {
					System.out.println("[" + (i+1) + "] " + petsEncontrados.get(i));
				}
				System.out.print(" >> ");
				int respostaEscolhaCadastro = sc.nextInt();
				
				sc.nextLine();
				
				System.out.println("Nome: ");
				nome = sc.nextLine();
				
				System.out.println("Cidade: ");
				String cidade = sc.nextLine();
				
				System.out.println("Rua: ");
				String rua = sc.nextLine();
				
				System.out.println("Numero: ");
				int numero = sc.nextInt();
				
				System.out.println("Idade: ");
				idade = sc.nextDouble();
				
				System.out.println("Peso: ");
				peso = sc.nextDouble();
				
				System.out.println("Raca: ");
				sc.nextLine();
				raca = sc.nextLine();
				
				Pet novoPet = petsEncontrados.get(respostaEscolhaCadastro-1);
				novoPet.setNome(nome);
				novoPet.setEndereco(new Endereco(numero, cidade, rua));
				novoPet.setIdade(idade);
				novoPet.setPeso(peso);
				novoPet.setRaca(raca);
				
				novoPet.alterarCadastro(caminhoDirCadastro);
				
			}
			else System.out.println("Nenhum pet encontrado.");
			
			break;
		case 3:
			int respostaDelecao;
			String respostaConfirmacao;
			
			do {
				System.out.println("\nTipo do Pet: ");
				tipoPetBusca = sc.next();
			} while(!tipoPetBusca.toUpperCase().equalsIgnoreCase("CACHORRO") && !tipoPetBusca.toUpperCase().equalsIgnoreCase("GATO"));
			
			Menu.menuBuscaPets();
			ConsultaService buscarTodosPets = new ConsultaService(caminhoDirCadastro);
			do {
				System.out.print("\n > ");
				respostaBusca = sc.nextInt();
			} while(respostaBusca < 1 || respostaBusca > 11);
			
			sc.nextLine();
			
			List<Pet> petsEncontradosCase3 = consultarPet(respostaBusca, sc, caminhoDirCadastro, tipoPetBusca);
			
			if(!petsEncontradosCase3.isEmpty()) { 
				System.out.printf("%d pet(s) encontrado(s):\n", petsEncontradosCase3.size());
				for(int i = 0; i < petsEncontradosCase3.size(); i++) {
					System.out.println("[" + (i+1) + "] " + petsEncontradosCase3.get(i));
				}
			}
			
			System.out.print("\nDeseja deletar qual cadastro: ");
			respostaDelecao = sc.nextInt();
			
			System.out.print("Tem certeza que deseja deletar esse pet (sim/nao)?  ");
			respostaConfirmacao = sc.next();

			
			if(respostaConfirmacao.toUpperCase().equalsIgnoreCase("SIM")) {
				Map<Pet, File> todosPetsMap = buscarTodosPets.listarTodosPets();
				Set<Pet> todosPetsSet = todosPetsMap.keySet();
				
				for(Pet petCadastrado : todosPetsSet) {
					if(petsEncontradosCase3.get(respostaDelecao-1).equals(petCadastrado)) {
						File arqPet = todosPetsMap.get(petCadastrado);
						petCadastrado.deletarCadastro(String.valueOf(arqPet));
					}
				}	
			}
			
			break;
		case 4:
			ConsultaService listaPets = new ConsultaService(caminhoDirCadastro);
			
			Map<Pet, File> petsMap = listaPets.listarTodosPets();
			
			Set<Pet> petsSet = petsMap.keySet();
			
			if(!petsSet.isEmpty()) {
				System.out.printf("\n\033[1m%d pet(s) cadastrado(s):\033[0m\n\n", petsSet.size());
				petsSet.forEach(System.out::println);
			}
			else System.out.println("Nenhum pet cadastrado.");
			
			break;
		case 5:
			do {
				System.out.println("\nTipo do Pet: ");
				tipoPetBusca = sc.next();
			} while(!tipoPetBusca.toUpperCase().equalsIgnoreCase("CACHORRO") && !tipoPetBusca.toUpperCase().equalsIgnoreCase("GATO"));
			
			Menu.menuBuscaPets();
			
			do {
				System.out.print("\n > ");
				respostaBusca = sc.nextInt();
			} while(respostaBusca < 1 || respostaBusca > 11);
			
			sc.nextLine();
			
			List<Pet> petsEncontradosCase5 = consultarPet(respostaBusca, sc, caminhoDirCadastro, tipoPetBusca.toUpperCase());
			
			if(!petsEncontradosCase5.isEmpty()) { 
				System.out.printf("%d pet(s) encontrado(s):\n", petsEncontradosCase5.size());
				petsEncontradosCase5.forEach(System.out::println);
			}
			else System.out.println("Nenhum pet encontrado.");
			
		default:
			break;
		}
		
		
		sc.close();
	}
	
	public static List<Pet> consultarPet(int tipoBusca, Scanner sc, String diretorioCadastrados, String tipoPetBusca){
		List<Pet> pets = null;
		switch(tipoBusca) {
		case 1:
			System.out.println("Nome: ");
			String nomePet = sc.nextLine();
			pets = buscarPet(nomePet, Criterio.NOME, diretorioCadastrados, tipoPetBusca);
			
			break;
		case 2:
			String sexoPet;
			do {
				System.out.println("Sexo: ");
				sexoPet = sc.nextLine();				
			} while(!sexoPet.toUpperCase().equalsIgnoreCase("MACHO") && !sexoPet.toUpperCase().equalsIgnoreCase("FEMEA"));
			
			pets = buscarPet(sexoPet, Criterio.SEXO, diretorioCadastrados, tipoPetBusca);
			
			break;
		case 3:
			System.out.println("Idade: ");
			String idadePet = sc.nextLine();
			pets = buscarPet(idadePet, Criterio.IDADE, diretorioCadastrados, tipoPetBusca);
			
			break;
		case 4:
			System.out.println("Peso: ");
			String pesoPet = sc.nextLine();
			pets = buscarPet(pesoPet, Criterio.PESO, diretorioCadastrados, tipoPetBusca);
			
			break;
		case 5:
			System.out.println("Raca: ");
			String racaPet = sc.nextLine();
			pets = buscarPet(racaPet, Criterio.RACA, diretorioCadastrados, tipoPetBusca);

			break;
		case 6:
			System.out.println("Endereco: ");
			String enderecoPet = sc.nextLine();
			pets = buscarPet(enderecoPet, Criterio.ENDERECO, diretorioCadastrados, tipoPetBusca);
			
			break;
		case 7:
			System.out.println("Nome: ");
			nomePet = sc.nextLine();
			System.out.println("Idade: ");
			idadePet = sc.nextLine();
			pets = buscarPet(nomePet, idadePet, Criterio.NOME_IDADE, diretorioCadastrados,tipoPetBusca);
			
			break;
		case 8:
			System.out.println("Idade: ");
			idadePet = sc.nextLine();
			System.out.println("Peso: ");
			pesoPet = sc.nextLine();
			pets = buscarPet(idadePet, pesoPet, Criterio.IDADE_PESO, diretorioCadastrados, tipoPetBusca);
			
			break;
		case 9:
			System.out.println("Nome: ");
			nomePet = sc.nextLine();
			System.out.println("Peso: ");
			pesoPet = sc.nextLine();
			pets = buscarPet(nomePet, pesoPet, Criterio.NOME_PESO, diretorioCadastrados, tipoPetBusca);
			
			break;
		case 10:
			System.out.println("Raca: ");
			racaPet = sc.nextLine();
			System.out.println("Peso: ");
			pesoPet = sc.nextLine();
			pets = buscarPet(racaPet, pesoPet, Criterio.RACA_PESO, diretorioCadastrados, tipoPetBusca);
			
			break;
		case 11:	
			do {
				System.out.println("Sexo: ");
				sexoPet = sc.nextLine();				
			} while(!sexoPet.toUpperCase().equalsIgnoreCase("MACHO") && !sexoPet.toUpperCase().equalsIgnoreCase("FEMEA"));
			
			System.out.println("Raca: ");
			racaPet = sc.nextLine();
			pets = buscarPet(sexoPet, racaPet, Criterio.SEXO_RACA, diretorioCadastrados, tipoPetBusca);
			
			break;
		}
		
		return pets;
	}
	
	public static List<Pet> buscarPet(String informacao, Criterio criterio, String diretorio, String tipoPet) {
		ConsultaService consulta = new ConsultaService(diretorio, new BuscarUmCriterio(informacao, criterio));
		List<Pet> petsEncontrados = consulta.consultar(tipoPet);
		return petsEncontrados;
	}
	
	public static List<Pet> buscarPet(String informacao1, String informacao2, Criterio criterio, String diretorio, String tipoPet) {
		ConsultaService consulta = new ConsultaService(diretorio, new BuscarDoisCriterios(informacao1, informacao2, criterio));
		List<Pet> petsEncontrados = consulta.consultar(tipoPet);
		return petsEncontrados;
	}
}
