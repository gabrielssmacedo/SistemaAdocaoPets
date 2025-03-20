package app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.Endereco;
import model.Pet;
import model.enums.SexoPet;
import model.enums.TipoPet;
import view.Menu;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String caminhoFormulario = "C:\\Users\\gabri\\OneDrive\\Área de Trabalho\\SistemaAdocaoPets\\formulario\\formulario.txt";
		String caminhoDirCadastro = "C:\\Users\\gabri\\OneDrive\\Área de Trabalho\\SistemaAdocaoPets\\petsCadastrados";
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
			
		case 3:
			
		case 4:
			
		case 5:
			
		default:
			break;
		}
		
		
		sc.close();
	}

}
