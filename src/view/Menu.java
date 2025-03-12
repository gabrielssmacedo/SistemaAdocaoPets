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

				System.out.println("> ");
				resposta = sc.nextInt();
				
			} while (resposta < 1 || resposta > 8);
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
	
	
}
