package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
	private String caminhoFormulario;

	public Menu(String caminhoFormulario) {
		this.caminhoFormulario = caminhoFormulario;
	}

	public Integer menuCadastro() {
		Scanner sb = new Scanner(System.in);
		Integer resposta = null;
		
		try {
			do {
				System.out.println("Deseja:");
				System.out.println("[1] Cadastrar um novo pet");
				System.out.println("[2] Alterar os dados do pet cadastrado");
				System.out.println("[3] Deletar um pet cadastrado");
				System.out.println("[4] Listar todos os pets cadastrados");
				System.out.println("[5] Listar pets por algum critério (idade, nome, raça)");
				System.out.println("[6] Sair");
				
				System.out.println("> ");
				resposta = sb.nextInt();
				
			} while(resposta < 1 || resposta > 6);
		} 
		catch(InputMismatchException e){
			System.out.println("Digite apenas numeros.");
		}
		finally {
			sb.close();
		}
		
		return resposta;
		
	}

	public Integer menuBuscaPets() {
		Scanner sc = new Scanner(System.in);
		Integer resposta = null;

		try {
			do {
				System.out.println("Buscar por:");
				System.out.println("[1] Nome");
				System.out.println("[2] Sexo");
				System.out.println("[3] Idade");
				System.out.println("[4] Peso");
				System.out.println("[5] Raca");
				System.out.println("[6] Endereco");
				System.out.println("[7] Nome e Idade");
				System.out.println("[8] Idade e Peso");
				System.out.println("[9] Nome e Peso");
				System.out.println("[10] Raca e Peso");
				System.out.println("[11] Sexo e Raca");

				System.out.println("> ");
				resposta = sc.nextInt();
				
			} while (resposta < 1 || resposta > 11);
		} catch (InputMismatchException e) {
			System.out.println("Digite apenas numeros.");
		} finally {
			sc.close();
		}

		return resposta;
	}
	
	public List<String> lerFormulario(){
		List<String> perguntas = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(caminhoFormulario))){
			String line = br.readLine();
			
			while(line != null) {
				perguntas.add(line);
			}
		}
		catch(IOException e) {
			System.out.println("Erro na leitura do Formulario.");
		}
		
		return perguntas;
	}
	
	public Map<Integer, String> validaRespostas(List<String> perguntas, Scanner sc) throws RuntimeException {
		
		Map<Integer, String> respostas = new LinkedHashMap<>();
		String resposta;
		Integer indice;
		
		for(indice = 0; indice < perguntas.size(); indice++) {
			System.out.println(perguntas.get(indice));
			
			switch (indice) {
			case 0:
				System.out.print("> ");
				resposta = sc.nextLine();
				
				if(resposta == null) throw new RuntimeException("Valor invalido! Você deve inserir um nome.");
				
				else if(temCaracteresEspeciais(resposta)) throw new RuntimeException("Valor invalido! O nome deve conter SOMENTE letras.");
				
				respostas.put(indice + 1, resposta);
				break;
				
			case 3:
				StringBuilder endereco = new StringBuilder();
				
				System.out.print(">> Cidade: ");
				resposta = sc.nextLine();
				endereco.append(resposta).append(",");
				
				System.out.print(">> Rua: ");
				resposta = sc.nextLine();
				endereco.append(resposta).append(",");
				
				System.out.print(">> Numero da casa: ");
				resposta = sc.next();
				sc.nextLine();
				
				endereco.append(resposta.isEmpty()? "ND" : resposta);
				respostas.put(indice + 1, endereco.toString());
				break;
				
			case 4:
				try {
					char mesesOuAno;
					do {
						System.out.print("> Digitar em meses ou anos (m/a): ");
					    mesesOuAno = sc.next().charAt(0);
					} while(mesesOuAno != 'm' && mesesOuAno != 'a');
					
					System.out.print(">> ");
					double idade = sc.nextDouble();
					if(mesesOuAno == 'm') idade = idade / 12;
					
					if(idade > 20) throw new RuntimeException("Valor invalido! A idade deve ser ate 20 anos.");	
					
					respostas.put(indice + 1, String.format("%.1f", idade));
					}
				catch(InputMismatchException e) {
					System.out.println("A idade deve conter somente numeros.");
					sc.next();
				}
				break;
				
			case 5:
				try {
					System.out.print("> ");
					double peso = sc.nextDouble();
					
					if(peso > 60 || peso < 0.50) throw new RuntimeException("Valor invalido! O peso deve estar entre 0.5Kg e 60Kg");	
					
					respostas.put(indice + 1, String.format("%.2f", peso));
					}
				catch(InputMismatchException e) {
					System.out.println("Valor invalido! A idade deve conter somente numeros.");
					sc.next();
				}
				break;
				
			case 6:
				System.out.print("> ");
				sc.nextLine();
				String raca = sc.nextLine();
				boolean especial = temCaracteresEspeciais(raca);
				
				while(especial) {
					System.out.println("Raca deve conter somente letras.");
					raca = sc.nextLine();
					especial = temCaracteresEspeciais(raca);
				}
				respostas.put(indice + 1, raca);
				break;
				
			default:
				System.out.print("> ");
				resposta = sc.next();
				sc.nextLine();
				respostas.put(indice + 1, resposta);
			}
		}
		
		System.out.println(respostas);
		return respostas;
	}
	
	private boolean temCaracteresEspeciais(String s) {
		String[] nomes = s.split(" ");
		
		for(String n : nomes) {
	    	int contador = 0;
	    	
	    	while(contador < n.length()) {
	    		char caracter = n.charAt(contador);
	    		if(!Character.isLetter(caracter)) return true;
	    		contador++;
	    	}
		}
		
		return false;
	}
}
